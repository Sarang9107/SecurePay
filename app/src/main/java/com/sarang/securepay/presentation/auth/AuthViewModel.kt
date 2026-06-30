package com.sarang.securepay.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sarang.securepay.domain.repository.AuthRepository
import com.sarang.securepay.domain.usecase.auth.LoginUseCase
import com.sarang.securepay.domain.usecase.auth.LogoutUseCase
import com.sarang.securepay.domain.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val registerUseCase: RegisterUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val authRepository: AuthRepository // Used to observe global auth state
) : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    
    // Combine internal UI state (loading, errors) with the global authentication state
    val uiState: StateFlow<AuthUiState> = combine(
        _uiState,
        authRepository.isLoggedIn(),
        authRepository.observeCurrentUser()
    ) { state, isLoggedIn, user ->
        state.copy(
            authStatus = if (isLoggedIn) AuthStatus.Authenticated else AuthStatus.Unauthenticated,
            user = user ?: state.user
        )
    }.catch { e -> 
        emit(AuthUiState(error = e.message ?: "An unexpected error occurred"))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = AuthUiState(isLoading = true) // Initial state while combining
    )

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.update { it.copy(error = "Email and Password cannot be blank") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            loginUseCase(email, password)
                .onSuccess { user ->
                    _uiState.update { it.copy(isLoading = false, error = null) }
                }
                .onFailure { exception ->
                    _uiState.update { it.copy(isLoading = false, error = exception.message ?: "Login failed") }
                }
        }
    }

    fun register(name: String, email: String, password: String) {
        if (name.isBlank() || email.isBlank() || password.length < 6) {
            _uiState.update { it.copy(error = "Please fill all fields correctly. Password must be at least 6 characters.") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            registerUseCase(name, email, password)
                .onSuccess { user ->
                    _uiState.update { it.copy(isLoading = false, error = null) }
                }
                .onFailure { exception ->
                    _uiState.update { it.copy(isLoading = false, error = exception.message ?: "Registration failed") }
                }
        }
    }

    fun logout() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            logoutUseCase()
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false, user = null, error = null) }
                }
                .onFailure { exception ->
                    _uiState.update { it.copy(isLoading = false, error = exception.message ?: "Logout failed") }
                }
        }
    }

    fun clearError() {
        _uiState.update { it.copy(error = null) }
    }
}

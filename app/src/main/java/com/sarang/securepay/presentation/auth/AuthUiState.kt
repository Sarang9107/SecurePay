package com.sarang.securepay.presentation.auth

import com.sarang.securepay.domain.model.User

sealed interface AuthStatus {
    object Initial : AuthStatus
    object Authenticated : AuthStatus
    object Unauthenticated : AuthStatus
}

data class AuthUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val error: String? = null,
    val authStatus: AuthStatus = AuthStatus.Initial
)

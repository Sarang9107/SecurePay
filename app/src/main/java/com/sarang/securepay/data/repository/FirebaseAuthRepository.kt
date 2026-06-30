package com.sarang.securepay.data.repository

import com.sarang.securepay.core.security.SessionManager
import com.sarang.securepay.domain.model.User
import com.sarang.securepay.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class FirebaseAuthRepository @Inject constructor(
    private val sessionManager: SessionManager
) : AuthRepository {
    
    private val currentUserFlow = MutableStateFlow<User?>(null)

    override suspend fun login(email: String, password: String): Result<User> {
        // TODO: Implement Firebase login
        val dummyUser = User("1", "Test User", email, "dummy_pub_key", 0.0, false, System.currentTimeMillis())
        currentUserFlow.value = dummyUser
        sessionManager.saveSession("dummy_auth_token")
        return Result.success(dummyUser)
    }

    override suspend fun register(name: String, email: String, password: String): Result<User> {
        // TODO: Implement Firebase register
        val dummyUser = User("1", name, email, "dummy_pub_key", 0.0, false, System.currentTimeMillis())
        currentUserFlow.value = dummyUser
        sessionManager.saveSession("dummy_auth_token")
        return Result.success(dummyUser)
    }

    override suspend fun logout(): Result<Unit> {
        // TODO: Implement Firebase logout
        currentUserFlow.value = null
        sessionManager.clearSession()
        return Result.success(Unit)
    }

    override suspend fun currentUser(): Result<User?> {
        // TODO: Implement Firebase get current user
        return Result.success(currentUserFlow.value)
    }

    override fun observeCurrentUser(): Flow<User?> {
        // TODO: Implement Firebase auth state listener
        return currentUserFlow
    }

    override fun isLoggedIn(): Flow<Boolean> {
        return sessionManager.isLoggedIn
    }
}

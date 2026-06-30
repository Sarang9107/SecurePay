package com.sarang.securepay.domain.repository

import com.sarang.securepay.domain.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): Result<User>
    suspend fun register(name: String, email: String, password: String): Result<User>
    suspend fun logout(): Result<Unit>
    suspend fun currentUser(): Result<User?>
    fun observeCurrentUser(): Flow<User?>
    fun isLoggedIn(): Flow<Boolean>
}

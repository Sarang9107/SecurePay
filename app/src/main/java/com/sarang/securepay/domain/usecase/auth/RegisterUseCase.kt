package com.sarang.securepay.domain.usecase.auth

import com.sarang.securepay.domain.model.User
import com.sarang.securepay.domain.repository.AuthRepository
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(name: String, email: String, password: String): Result<User> {
        if (name.isBlank()) {
            return Result.failure(IllegalArgumentException("Name cannot be blank"))
        }
        if (email.isBlank()) {
            return Result.failure(IllegalArgumentException("Email cannot be blank"))
        }
        if (password.length < 6) {
            return Result.failure(IllegalArgumentException("Password must be at least 6 characters"))
        }
        return authRepository.register(name, email, password)
    }
}

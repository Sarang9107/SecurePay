package com.sarang.securepay.domain.usecase.auth

import com.sarang.securepay.domain.model.User
import com.sarang.securepay.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): Result<User> {
        if (email.isBlank()) {
            return Result.failure(IllegalArgumentException("Email cannot be blank"))
        }
        if (password.isBlank()) {
            return Result.failure(IllegalArgumentException("Password cannot be blank"))
        }
        return authRepository.login(email, password)
    }
}

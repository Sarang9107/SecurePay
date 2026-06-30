package com.sarang.securepay.domain.usecase.auth

import com.sarang.securepay.domain.model.User
import com.sarang.securepay.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<User?> {
        return authRepository.currentUser()
    }
}

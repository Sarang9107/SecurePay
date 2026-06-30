package com.sarang.securepay.domain.model

data class User(
    val id: String,
    val name: String,
    val email: String,
    val publicKey: String,
    val walletBalance: Double,
    val isBiometricEnabled: Boolean,
    val createdAt: Long
)

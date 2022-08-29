package net.repeatuntil.wishly.user

data class RegisterUserDTO(
    val userId: String,
    val email: String?,
    val password: String?
)
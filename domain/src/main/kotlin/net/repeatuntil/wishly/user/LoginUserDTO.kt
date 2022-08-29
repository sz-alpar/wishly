package net.repeatuntil.wishly.user

data class LoginUserDTO(
    val sessionId: String,
    val email: String?,
    val password: String?,
)

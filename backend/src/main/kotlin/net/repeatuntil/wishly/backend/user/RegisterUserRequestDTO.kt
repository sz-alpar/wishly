package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.error.*
import net.repeatuntil.wishly.user.RegisterUserDTO
import net.repeatuntil.wishly.user.User

data class RegisterUserRequestDTO(
    val email: String?,
    val password: String?
) {

    fun toRegisterUserDTO(userId: String): RegisterUserDTO {
        return RegisterUserDTO(userId, email, password)
    }
}
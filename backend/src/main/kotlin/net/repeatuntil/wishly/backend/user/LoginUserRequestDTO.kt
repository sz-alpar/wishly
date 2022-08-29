package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.error.*
import net.repeatuntil.wishly.user.GetUserSessionDTO
import net.repeatuntil.wishly.user.LoginUserDTO
import net.repeatuntil.wishly.user.RegisterUserDTO
import net.repeatuntil.wishly.user.User

data class LoginUserRequestDTO(
    val email: String?,
    val password: String?
) {

    fun toLoginUserDTO(sessionId: String): LoginUserDTO {
        return LoginUserDTO(sessionId, email, password)
    }

    fun toGetUserSessionDTO(): GetUserSessionDTO {
        return GetUserSessionDTO(email, password)
    }
}
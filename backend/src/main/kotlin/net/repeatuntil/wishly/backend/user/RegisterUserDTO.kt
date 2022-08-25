package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.error.*
import net.repeatuntil.wishly.user.UserModel

data class RegisterUserDTO(
    val email: String?,
    val password: String?
) {
    @Throws(RegisterUserException::class, UserException::class)
    fun toUserModel(): UserModel {
        if (email == null) {
            throw RegisterUserException(RegisterUserError.MissingEmail)
        }
        if (password == null) {
            throw RegisterUserException(RegisterUserError.MissingPassword)
        }
        return UserModel.newUser(email, password)
    }
}
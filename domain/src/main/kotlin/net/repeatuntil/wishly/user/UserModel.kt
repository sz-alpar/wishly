package net.repeatuntil.wishly.user

import net.repeatuntil.wishly.error.UserError
import net.repeatuntil.wishly.error.UserException
import java.util.*

data class UserModel @Throws(UserException::class) constructor(
    val email: String,
    val password: String,
    val id: String
) {

    init {
        if (password.length < 8) {
            throw UserException(UserError.InvalidPasswordFormat)
        }
    }

    companion object {
        fun newUser(email: String, password: String): UserModel =
            UserModel(email, password, UUID.randomUUID().toString())
    }
}

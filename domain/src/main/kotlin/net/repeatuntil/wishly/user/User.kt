package net.repeatuntil.wishly.user

import net.repeatuntil.wishly.error.RegisterUserError
import net.repeatuntil.wishly.error.RegisterUserException
import net.repeatuntil.wishly.error.UserError
import net.repeatuntil.wishly.error.UserException
import java.util.*

/**
 * User entity. Is also aggregate root for user related logic.
 */
data class User @Throws(UserException::class) constructor(
    val userId: String,
    val userData: UserData
) {

    init {
        if (userData.password.length < 8) {
            throw UserException(UserError.InvalidPasswordFormat)
        }
    }

    companion object {

        fun generateUserId() = UUID.randomUUID().toString()

        @Throws(UserException::class, RegisterUserException::class)
        fun registerUser(registerUserDTO: RegisterUserDTO, userRepository: UserRepository) {
            val newUser = fromRegisterUserDTO(registerUserDTO)
            userRepository.addUser(newUser)
        }

        @Throws(RegisterUserException::class)
        private fun fromRegisterUserDTO(registerUserDTO: RegisterUserDTO): User {
            if (registerUserDTO.email == null) {
                throw RegisterUserException(RegisterUserError.MissingEmail)
            }
            if (registerUserDTO.password == null) {
                throw RegisterUserException(RegisterUserError.MissingPassword)
            }
            return User(registerUserDTO.userId, UserData(registerUserDTO.email, registerUserDTO.password))
        }
    }
}

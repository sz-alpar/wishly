package net.repeatuntil.wishly.user

import net.repeatuntil.wishly.error.*
import net.repeatuntil.wishly.session.Session
import net.repeatuntil.wishly.session.SessionRepository
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
            return User(
                registerUserDTO.userId,
                UserData(registerUserDTO.email, registerUserDTO.password)
            )
        }

        @Throws(LoginUserException::class)
        fun login(
            loginUserDTO: LoginUserDTO,
            userRepository: UserRepository,
            sessionRepository: SessionRepository
        ) {
            if (loginUserDTO.email == null) {
                throw LoginUserException(LoginUserError.MissingEmail)
            }
            if (loginUserDTO.password == null) {
                throw LoginUserException(LoginUserError.MissingPassword)
            }

            val user = userRepository.getUserByEmail(loginUserDTO.email)
                ?: throw LoginUserException(LoginUserError.UserNotFound)

            if (user.userData.password != loginUserDTO.password) {
                throw LoginUserException(LoginUserError.WrongPassword)
            }

            if (Session.hasUserActiveSession(user, sessionRepository)) {
                throw LoginUserException(LoginUserError.UserAlreadyLoggedIn)
            }

            Session.createSession(user, loginUserDTO.sessionId, sessionRepository)
        }

        @Throws(LoginUserException::class)
        fun getSession(
            getUserSessionDTO: GetUserSessionDTO,
            userRepository: UserRepository,
            sessionRepository: SessionRepository
        ): Session {
            if (getUserSessionDTO.email == null) {
                throw LoginUserException(LoginUserError.MissingEmail)
            }
            if (getUserSessionDTO.password == null) {
                throw LoginUserException(LoginUserError.MissingPassword)
            }

            val user = userRepository.getUserByEmail(getUserSessionDTO.email)
                ?: throw LoginUserException(LoginUserError.UserNotFound)

            if (user.userData.password != getUserSessionDTO.password) {
                throw LoginUserException(LoginUserError.WrongPassword)
            }

            if (Session.hasUserActiveSession(user, sessionRepository)) {
                return sessionRepository.getSessionByUser(user)
                    ?: throw LoginUserException(LoginUserError.SessionNotFound)
            } else {
                throw LoginUserException(LoginUserError.SessionNotFound)
            }
        }

    }
}



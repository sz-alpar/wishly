package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.error.LoginUserException
import net.repeatuntil.wishly.session.Session
import net.repeatuntil.wishly.session.SessionRepository
import net.repeatuntil.wishly.user.User
import net.repeatuntil.wishly.user.UserRepository

class GetSessionForLoggedInUserUseCase(
    val userRepository: UserRepository,
    val sessionRepository: SessionRepository,
) {

    @Throws(LoginUserException::class)
    operator fun invoke(loginUserRequestDTO: LoginUserRequestDTO): Session {
        return User.getSession(
            loginUserRequestDTO.toGetUserSessionDTO(),
            userRepository,
            sessionRepository
        )
    }
}
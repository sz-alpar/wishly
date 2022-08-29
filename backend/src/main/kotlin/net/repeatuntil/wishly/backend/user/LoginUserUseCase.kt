package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.error.LoginUserException
import net.repeatuntil.wishly.error.RegisterUserException
import net.repeatuntil.wishly.error.UserException
import net.repeatuntil.wishly.session.SessionRepository
import net.repeatuntil.wishly.user.User
import net.repeatuntil.wishly.user.UserRepository
import kotlin.jvm.Throws

class LoginUserUseCase(
    private val userRepository: UserRepository,
    private val sessionRepository: SessionRepository,
) {

    @Throws(LoginUserException::class)
    operator fun invoke(loginUserRequestDTO: LoginUserRequestDTO, sessionId: String) {
        User.login(loginUserRequestDTO.toLoginUserDTO(sessionId), userRepository, sessionRepository)
    }
}
package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.backend.session.InMemorySessionRepository
import net.repeatuntil.wishly.error.LoginUserError
import net.repeatuntil.wishly.error.LoginUserException
import net.repeatuntil.wishly.error.RegisterUserException
import net.repeatuntil.wishly.error.UserException
import net.repeatuntil.wishly.session.Session
import net.repeatuntil.wishly.user.User
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    private val userRepository = InMemoryUserRepository()
    private val sessionRepository = InMemorySessionRepository()

    private val registerUserUseCase = RegisterUserUseCase(userRepository)
    private val loginUserUseCase = LoginUserUseCase(userRepository, sessionRepository)
    private val getSessionForLoggedInUserUseCase =
        GetSessionForLoggedInUserUseCase(userRepository, sessionRepository)

    @GetMapping("/user/{userId}")
    fun getUser(@PathVariable("userId") userId: String): String {
        return userRepository.getUserById(userId)?.let { user ->
            "User: $user"
        } ?: "User not found"
    }

    @PostMapping("/user")
    fun registerUser(@RequestBody registerUserRequestDTO: RegisterUserRequestDTO): String {
        return try {
            val userId = User.generateUserId()
            registerUserUseCase(registerUserRequestDTO, userId)
            "User created with id $userId"
        } catch (userException: UserException) {
            userException.message ?: userException.toString()
        } catch (registerUserException: RegisterUserException) {
            registerUserException.message ?: registerUserException.toString()
        }
    }

    @PostMapping("/user/login")
    fun loginUser(@RequestBody loginUserRequestDTO: LoginUserRequestDTO): String {
        return try {
            val sessionId = Session.generateSessionId()
            loginUserUseCase(loginUserRequestDTO, sessionId)
            sessionRepository.getSessionById(sessionId)?.let { session ->
                "Session token ${session.token}"
            } ?: "Failed to create session"
        } catch (loginUserException: LoginUserException) {
            when (loginUserException.error) {
                LoginUserError.UserAlreadyLoggedIn -> {
                    try {
                        val session = getSessionForLoggedInUserUseCase(loginUserRequestDTO)
                        "Session token ${session.token}"
                    } catch (loginUserException: LoginUserException) {
                        loginUserException.message ?: loginUserException.toString()
                    }
                }
                else -> loginUserException.message ?: loginUserException.toString()
            }
        }
    }
}
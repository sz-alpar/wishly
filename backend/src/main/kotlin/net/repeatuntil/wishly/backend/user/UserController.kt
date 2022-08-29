package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.error.CommonException
import net.repeatuntil.wishly.error.RegisterUserException
import net.repeatuntil.wishly.error.UserException
import net.repeatuntil.wishly.user.RegisterUserDTO
import net.repeatuntil.wishly.user.User
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    private val userRepository = InMemoryUserRepository()
    private val registerUserUseCase = RegisterUserUseCase(userRepository)

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
}
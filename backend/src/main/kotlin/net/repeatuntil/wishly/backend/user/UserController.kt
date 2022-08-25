package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.error.CommonException
import net.repeatuntil.wishly.error.RegisterUserException
import net.repeatuntil.wishly.error.UserException
import net.repeatuntil.wishly.user.UserModel
import org.springframework.web.bind.annotation.*

@RestController
class UserController {

    private val userRepository = InMemoryUserRepository()
    private val registerUserUseCase = RegisterUserUseCase(userRepository)

    @GetMapping("/user/{userId}")
    fun getUser(@PathVariable("userId") userId: String): String {
        return "User with id $userId will be returned here"
    }

    @PostMapping("/user")
    fun registerUser(@RequestBody registerUserDTO: RegisterUserDTO): String {
        try {
            val userModel = registerUserDTO.toUserModel()
            registerUserUseCase(userModel)
        } catch (userException: UserException) {
            return userException.message ?: userException.toString()
        } catch (registerUserException: RegisterUserException) {
            return registerUserException.message ?: registerUserException.toString()
        }
        return registerUserDTO.toString()
    }
}
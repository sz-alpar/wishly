package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.error.RegisterUserException
import net.repeatuntil.wishly.error.UserException
import net.repeatuntil.wishly.user.User
import net.repeatuntil.wishly.user.UserRepository
import kotlin.jvm.Throws

class RegisterUserUseCase(
    private val userRepository: UserRepository
) {
    
    @Throws(UserException::class, RegisterUserException::class)
    operator fun invoke(registerUserRequestDTO: RegisterUserRequestDTO, userId: String) {
        User.registerUser(registerUserRequestDTO.toRegisterUserDTO(userId), userRepository)
    }
}
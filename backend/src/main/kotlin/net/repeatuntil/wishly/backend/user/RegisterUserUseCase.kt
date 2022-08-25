package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.user.AbstractRegisterUserUseCase
import net.repeatuntil.wishly.user.UserModel
import net.repeatuntil.wishly.user.UserRepository

class RegisterUserUseCase(userRepository: UserRepository) : AbstractRegisterUserUseCase(userRepository) {

    override fun invoke(user: UserModel) {
        userRepository.createUser(user)
    }
}
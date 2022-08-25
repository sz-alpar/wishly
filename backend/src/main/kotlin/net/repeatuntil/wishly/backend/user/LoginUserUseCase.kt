package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.user.AbstractLoginUserUseCase
import net.repeatuntil.wishly.user.UserRepository

class LoginUserUseCase(userRepository: UserRepository) : AbstractLoginUserUseCase(userRepository) {
}
package net.repeatuntil.wishly.user

abstract class AbstractRegisterUserUseCase(
    open val userRepository: UserRepository
) {
    abstract operator fun invoke(user: UserModel)
}
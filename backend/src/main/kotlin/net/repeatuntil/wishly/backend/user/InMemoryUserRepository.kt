package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.user.UserModel
import net.repeatuntil.wishly.user.UserRepository

class InMemoryUserRepository: UserRepository {

    private val users: MutableList<UserModel> = mutableListOf()

    override fun createUser(user: UserModel) {
        users.add(user)
    }

    override fun getUserByEmail(email: String): UserModel? {
        return users.firstOrNull { it.email == email }
    }
}
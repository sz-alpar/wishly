package net.repeatuntil.wishly.backend.user

import net.repeatuntil.wishly.user.User
import net.repeatuntil.wishly.user.UserRepository

class InMemoryUserRepository: UserRepository {

    private val users: MutableList<User> = mutableListOf()

    override fun addUser(user: User) {
        users.add(user)
    }

    override fun getUserById(id: String): User? {
        return users.firstOrNull { it.userId == id }
    }

    override fun getUserByEmail(email: String): User? {
        return users.firstOrNull { it.userData.email == email }
    }
}
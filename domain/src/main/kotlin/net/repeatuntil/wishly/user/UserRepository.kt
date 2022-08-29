package net.repeatuntil.wishly.user

interface UserRepository {
    fun addUser(user: User)
    fun getUserById(id: String): User?
    fun getUserByEmail(email: String): User?
}
package net.repeatuntil.wishly.user

interface UserRepository {
    fun createUser(user: UserModel)
    fun getUserByEmail(email: String): UserModel?
}
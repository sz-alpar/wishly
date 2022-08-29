package net.repeatuntil.wishly.session

import net.repeatuntil.wishly.user.User

interface SessionRepository {
    fun addSession(session: Session, user: User)
    fun getSessionByUser(user: User): Session?
    fun getSessionById(id: String): Session?
}
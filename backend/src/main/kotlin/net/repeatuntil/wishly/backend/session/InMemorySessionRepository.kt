package net.repeatuntil.wishly.backend.session

import net.repeatuntil.wishly.session.Session
import net.repeatuntil.wishly.session.SessionRepository
import net.repeatuntil.wishly.user.User
import net.repeatuntil.wishly.user.UserRepository

class InMemorySessionRepository : SessionRepository {

    private val userSessionMap: MutableMap<String, Session> = mutableMapOf()

    override fun addSession(session: Session, user: User) {
        userSessionMap[user.userId] = session
    }

    override fun getSessionByUser(user: User): Session? {
        return userSessionMap[user.userId]
    }

    override fun getSessionById(id: String): Session? {
        return userSessionMap.values.firstOrNull { it.sessionId == id }
    }


}
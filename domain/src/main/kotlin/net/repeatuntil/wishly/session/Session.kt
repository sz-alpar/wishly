package net.repeatuntil.wishly.session

import net.repeatuntil.wishly.user.User
import java.time.LocalDateTime
import java.util.*

class Session(
    val sessionId: String,
    val token: String,
    val validUntil: LocalDateTime,
) {

    companion object {

        fun generateSessionId() = UUID.randomUUID().toString()

        fun createSession(user: User, sessionId: String, sessionRepository: SessionRepository) {
            val validUntil = LocalDateTime.now().plusSeconds(SESSION_DURATION_SECONDS)
            val session = Session(sessionId, token = UUID.randomUUID().toString(), validUntil)
            sessionRepository.addSession(session, user)
        }

        fun hasUserActiveSession(user: User, sessionRepository: SessionRepository): Boolean {
            return sessionRepository.getSessionByUser(user)?.let { session ->
                LocalDateTime.now().isBefore(session.validUntil)
            } ?: false
        }

        private const val SESSION_DURATION_SECONDS: Long = 10
    }
}
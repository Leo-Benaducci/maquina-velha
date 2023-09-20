package br.com.lbenaducci.maquinavelha.repositories

import br.com.lbenaducci.maquinavelha.models.entities.Session
import br.com.lbenaducci.maquinavelha.models.enums.Result
import org.springframework.stereotype.Repository

@Repository
class SessionRepository : InMemoryRepository<Session>() {
    fun verifyAllSessionsFinished(): Boolean {
        return findByFilter { it.result == Result.NONE }.isEmpty()
    }
}
package br.com.lbenaducci.maquinavelha.repositories

import br.com.lbenaducci.maquinavelha.models.entities.Session
import br.com.lbenaducci.maquinavelha.models.enums.Result
import org.springframework.stereotype.Repository

@Repository
interface SessionRepository : AbstractRepository<Session> {
    fun findFirstByResult(result: Result): Session?
}
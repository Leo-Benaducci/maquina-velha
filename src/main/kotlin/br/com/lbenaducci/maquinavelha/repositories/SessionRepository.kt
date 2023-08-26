package br.com.lbenaducci.maquinavelha.repositories

import br.com.lbenaducci.maquinavelha.models.entities.Session
import org.springframework.stereotype.Repository

@Repository
class SessionRepository : InMemoryRepository<Session>()
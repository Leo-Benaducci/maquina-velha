package br.com.lbenaducci.maquinavelha.repositories

import br.com.lbenaducci.maquinavelha.models.entities.Player
import org.springframework.stereotype.Repository

@Repository
class PlayerRepository : InMemoryRepository<Player>()
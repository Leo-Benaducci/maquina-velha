package br.com.lbenaducci.maquinavelha.services

import br.com.lbenaducci.maquinavelha.exceptions.NotFoundException
import br.com.lbenaducci.maquinavelha.models.entities.Player
import br.com.lbenaducci.maquinavelha.repositories.PlayerRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class PlayerService(
    private val repository: PlayerRepository
) {
    fun save(player: Player): Player {
        return repository.save(player)
    }

    fun findAll(): List<Player> {
        return repository.findAll()
    }

    fun findById(id: UUID): Player {
        return repository.findById(id) ?: throw NotFoundException("Player $id not found")
    }

    fun deleteById(id: UUID) {
        repository.deleteById(id)
    }
}
package br.com.lbenaducci.maquinavelha.repositories

import br.com.lbenaducci.maquinavelha.models.entities.Move
import org.springframework.stereotype.Repository

@Repository
interface MoveRepository : AbstractRepository<Move> {
}
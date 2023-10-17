package br.com.lbenaducci.maquinavelha.repositories

import br.com.lbenaducci.maquinavelha.models.entities.Board
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : AbstractRepository<Board>
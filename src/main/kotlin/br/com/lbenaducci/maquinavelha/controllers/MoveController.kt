package br.com.lbenaducci.maquinavelha.controllers

import br.com.lbenaducci.maquinavelha.components.MoveQueue
import br.com.lbenaducci.maquinavelha.models.entities.Move
import br.com.lbenaducci.maquinavelha.models.enums.Piece
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/moves")
class MoveController(
    private val moveQueue: MoveQueue
) {
    @GetMapping
    fun getMovePiece(@RequestParam piece: Piece): Move? {
        return moveQueue.get(piece)
    }

    @DeleteMapping("/remove/{id}")
    fun removeMove(@PathVariable id: UUID) {
        moveQueue.remove(id)
    }
}
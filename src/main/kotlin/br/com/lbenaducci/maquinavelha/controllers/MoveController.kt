package br.com.lbenaducci.maquinavelha.controllers

import br.com.lbenaducci.maquinavelha.components.MoveQueue
import br.com.lbenaducci.maquinavelha.models.entities.Move
import br.com.lbenaducci.maquinavelha.models.enums.Piece
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/moves")
class MoveController(
    private val moveQueue: MoveQueue
) {
    @GetMapping
    fun getMovePiece(@RequestParam piece: Piece): ResponseEntity<Move> {
        val move = moveQueue.get(piece) ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(move)
    }

    @DeleteMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeMove(@PathVariable id: UUID) {
        moveQueue.remove(id)
    }
}
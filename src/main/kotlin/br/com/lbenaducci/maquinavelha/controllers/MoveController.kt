package br.com.lbenaducci.maquinavelha.controllers

import br.com.lbenaducci.maquinavelha.components.MoveQueue
import br.com.lbenaducci.maquinavelha.models.dtos.MoveDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/moves")
class MoveController(
    private val moveQueue: MoveQueue
) {
    @GetMapping("/session")
    fun getCurrentSessionId(): ResponseEntity<Map<String, UUID>> {
        val id = moveQueue.currentSessionId ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(mapOf(Pair("id", id)));
    }

    @DeleteMapping("/session/{sessionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun readySession(@PathVariable sessionId: UUID) {
        moveQueue.readSession(sessionId)
    }

    @GetMapping
    fun getMovePiece(): ResponseEntity<List<MoveDto>> {
        val move = moveQueue.get() ?: return ResponseEntity.notFound().build()
        return ResponseEntity.ok(listOf(move))
    }

    @DeleteMapping("/remove/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun removeMove(@PathVariable id: UUID) {
        moveQueue.remove(id)
    }
}
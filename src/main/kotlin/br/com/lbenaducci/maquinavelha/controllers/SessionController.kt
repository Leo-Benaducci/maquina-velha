package br.com.lbenaducci.maquinavelha.controllers

import br.com.lbenaducci.maquinavelha.models.entities.Move
import br.com.lbenaducci.maquinavelha.models.entities.Session
import br.com.lbenaducci.maquinavelha.models.enums.Player
import br.com.lbenaducci.maquinavelha.services.SessionService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val service: SessionService
) {
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(): Session {
        return service.create()
    }

    @PostMapping("/{sessionId}")
    @ResponseStatus(HttpStatus.OK)
    fun registryPlayer(@PathVariable sessionId: UUID, @RequestParam player: Player): Session {
        return service.registryPlayer(sessionId, player)
    }

    @GetMapping("/{sessionId}")
    @ResponseStatus(HttpStatus.OK)
    fun findById(@PathVariable sessionId: UUID): Session {
        return service.findById(sessionId)
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    fun findAll(): List<Session> {
        return service.findAll()
    }

    @PutMapping("/move/{sessionId}")
    @ResponseStatus(HttpStatus.OK)
    fun move(@PathVariable sessionId: UUID, @RequestBody move: Move): Session {
        return service.move(sessionId, move)
    }

    @DeleteMapping("/finish/{sessionId}")
    @ResponseStatus(HttpStatus.OK)
    fun finish(@PathVariable sessionId: UUID): Session {
        return service.finish(sessionId)
    }
}
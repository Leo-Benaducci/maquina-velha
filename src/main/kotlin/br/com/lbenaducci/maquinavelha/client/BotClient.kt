package br.com.lbenaducci.maquinavelha.client

import br.com.lbenaducci.maquinavelha.models.entities.Move
import org.springframework.stereotype.Component

@Component
class BotClient {
    fun move(move: Move): Boolean {
        //todo implement bot communication logic
        return true
    }

    fun finish(): Boolean {
        //todo implement bot communication logic
        return true
    }
}
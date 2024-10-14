package de.nebulit.game.internal

import de.nebulit.domain.commands.game.CloseGameCommand
import java.time.LocalDateTime
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class GamePayload(var gameName: String, var timestamp: LocalDateTime)

@RestController
class CloseGameRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/game")
  fun processDebugCommand(
      @RequestParam gameName: String,
      @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") @RequestParam timestamp: LocalDateTime
  ): CompletableFuture<Any> {
    return commandGateway.send(CloseGameCommand(gameName, timestamp))
  }

  @CrossOrigin
  @PostMapping("/game/{aggregateId}")
  fun processCommand(
      @PathVariable("aggregateId") aggregateId: String,
      @RequestBody payload: GamePayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        CloseGameCommand(gameName = payload.gameName, timestamp = payload.timestamp))
  }
}

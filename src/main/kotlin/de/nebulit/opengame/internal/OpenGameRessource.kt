package de.nebulit.opengame.internal

import de.nebulit.domain.commands.opengame.OpenGameCommand
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

data class OpenGamePayload(var gameName: String, var timestamp: LocalDateTime)

@RestController
class OpenGameRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/opengame")
  fun processDebugCommand(
      @RequestParam gameName: String,
      @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss") @RequestParam timestamp: LocalDateTime
  ): CompletableFuture<Any> {
    return commandGateway.send(OpenGameCommand(gameName, timestamp))
  }

  @CrossOrigin
  @PostMapping("/opengame/{aggregateId}")
  fun processCommand(
      @PathVariable("aggregateId") aggregateId: String,
      @RequestBody payload: OpenGamePayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        OpenGameCommand(gameName = payload.gameName, timestamp = payload.timestamp))
  }
}

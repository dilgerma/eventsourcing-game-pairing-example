package de.nebulit.pairgame.internal

import de.nebulit.domain.commands.pairgame.PairGameCommand
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

data class PairGamePayload(var gameName1: String, var gameName2: String)

@RestController
class PairGameRessource(private var commandGateway: CommandGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @PostMapping("/debug/pairgame")
  fun processDebugCommand(
      @RequestParam gameName1: String,
      @RequestParam gameName2: String
  ): CompletableFuture<Any> {
    return commandGateway.send(PairGameCommand(gameName1, gameName2))
  }

  @CrossOrigin
  @PostMapping("/pairgame/{aggregateId}")
  fun processCommand(
      @PathVariable("aggregateId") aggregateId: String,
      @RequestBody payload: PairGamePayload
  ): CompletableFuture<Any> {
    return commandGateway.send(
        PairGameCommand(gameName1 = payload.gameName1, gameName2 = payload.gameName2))
  }
}

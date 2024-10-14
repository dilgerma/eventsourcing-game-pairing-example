package de.nebulit.games.internal

import de.nebulit.games.GamesToPairReadModel
import de.nebulit.games.GamesToPairReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GamesRessource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/games")
  fun findReadModel(): CompletableFuture<GamesToPairReadModel> {
    return queryGateway.query(GamesToPairReadModelQuery(), GamesToPairReadModel::class.java)
  }
}

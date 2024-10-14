package de.nebulit.gamesopened.internal

import de.nebulit.gamesopened.OpenGamesReadModel
import de.nebulit.gamesopened.OpenGamesReadModelQuery
import java.util.concurrent.CompletableFuture
import mu.KotlinLogging
import org.axonframework.queryhandling.QueryGateway
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GamesopenedRessource(private var queryGateway: QueryGateway) {

  var logger = KotlinLogging.logger {}

  @CrossOrigin
  @GetMapping("/gamesopened")
  fun findReadModel(): CompletableFuture<OpenGamesReadModel> {
    return queryGateway.query(OpenGamesReadModelQuery(), OpenGamesReadModel::class.java)
  }
}

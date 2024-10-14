package de.nebulit.games.internal

import de.nebulit.games.GamesToPairReadModel
import de.nebulit.games.GamesToPairReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class GamesToPairReadModelQueryHandler(private val repository: GamesToPairReadModelRepository) {

  @QueryHandler
  fun handleQuery(query: GamesToPairReadModelQuery): GamesToPairReadModel? {
    return GamesToPairReadModel(repository.findAll())
  }
}

package de.nebulit.gamesopened.internal

import de.nebulit.gamesopened.OpenGamesReadModel
import de.nebulit.gamesopened.OpenGamesReadModelQuery
import org.axonframework.queryhandling.QueryHandler
import org.springframework.stereotype.Component

@Component
class OpenGamesReadModelQueryHandler(private val repository: OpenGamesReadModelRepository) {

  @QueryHandler
  fun handleQuery(query: OpenGamesReadModelQuery): OpenGamesReadModel? {
    return OpenGamesReadModel(repository.findAll())
  }
}

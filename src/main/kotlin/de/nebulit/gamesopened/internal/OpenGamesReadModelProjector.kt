package de.nebulit.gamesopened.internal

import de.nebulit.events.GameClosedEvent
import de.nebulit.events.GameOpenedEvent
import de.nebulit.gamesopened.OpenGamesReadModelEntity
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface OpenGamesReadModelRepository : JpaRepository<OpenGamesReadModelEntity, String>

@Component
class OpenGamesReadModelProjector(var repository: OpenGamesReadModelRepository) {

  @EventHandler
  fun on(event: GameOpenedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.gameName).orElse(OpenGamesReadModelEntity())
    entity
        .apply {
          timestamp = event.timestamp
          gameName = event.gameName
        }
        .also { this.repository.save(it) }
  }

  @EventHandler
  fun on(event: GameClosedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.gameName).orElse(OpenGamesReadModelEntity())
    entity
        .apply {
          timestamp = event.timestamp
          gameName = event.gameName
        }
        .also { this.repository.save(it) }
  }
}

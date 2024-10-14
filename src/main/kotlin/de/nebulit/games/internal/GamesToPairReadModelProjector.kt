package de.nebulit.games.internal

import de.nebulit.events.GameClosedEvent
import de.nebulit.events.GameOpenedEvent
import de.nebulit.games.GamesToPairReadModelEntity
import org.axonframework.eventhandling.EventHandler
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component

interface GamesToPairReadModelRepository : JpaRepository<GamesToPairReadModelEntity, String>

@Component
class GamesToPairReadModelProjector(var repository: GamesToPairReadModelRepository) {

  @EventHandler
  fun on(event: GameOpenedEvent) {
    // throws exception if not available (adjust logic)
    val entity = this.repository.findById(event.gameName).orElse(GamesToPairReadModelEntity())
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
    val entity = this.repository.findById(event.gameName).orElse(GamesToPairReadModelEntity())
    entity
        .apply {
          timestamp = event.timestamp
          gameName = event.gameName
        }
        .also { this.repository.save(it) }
  }
}

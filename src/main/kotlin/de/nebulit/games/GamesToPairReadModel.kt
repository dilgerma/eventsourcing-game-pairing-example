package de.nebulit.games

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

class GamesToPairReadModelQuery()

@Entity
class GamesToPairReadModelEntity {
  @Column(name = "timestamp") var timestamp: LocalDateTime? = null

  @Id @Column(name = "gameName") var gameName: String? = null
}

data class GamesToPairReadModel(val data: List<GamesToPairReadModelEntity>)

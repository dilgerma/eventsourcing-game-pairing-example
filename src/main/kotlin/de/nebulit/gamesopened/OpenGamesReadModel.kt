package de.nebulit.gamesopened

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.time.LocalDateTime

class OpenGamesReadModelQuery()

@Entity
class OpenGamesReadModelEntity {
  @Column(name = "timestamp") var timestamp: LocalDateTime? = null

  @Id @Column(name = "gameName") var gameName: String? = null
}

data class OpenGamesReadModel(val data: List<OpenGamesReadModelEntity>)

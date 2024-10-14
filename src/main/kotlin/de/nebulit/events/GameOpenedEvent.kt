package de.nebulit.events

import de.nebulit.common.Event
import java.time.LocalDateTime

data class GameOpenedEvent(var gameName: String, var timestamp: LocalDateTime) : Event

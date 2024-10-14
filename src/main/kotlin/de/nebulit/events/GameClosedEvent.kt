package de.nebulit.events

import de.nebulit.common.Event
import java.time.LocalDateTime

data class GameClosedEvent(var gameName: String, var timestamp: LocalDateTime) : Event

package de.nebulit.events

import de.nebulit.common.Event

data class GamesPairedEvent(var gameName1: String, var gameName2: String) : Event

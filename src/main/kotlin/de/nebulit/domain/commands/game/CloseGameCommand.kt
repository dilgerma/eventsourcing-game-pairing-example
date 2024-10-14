package de.nebulit.domain.commands.game

import de.nebulit.common.Command
import java.time.LocalDateTime
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class CloseGameCommand(
    @TargetAggregateIdentifier var gameName: String,
    var timestamp: LocalDateTime
) : Command

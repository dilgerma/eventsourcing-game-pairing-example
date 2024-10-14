package de.nebulit.domain.commands.opengame

import de.nebulit.common.Command
import java.time.LocalDateTime
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class OpenGameCommand(
    @TargetAggregateIdentifier var gameName: String,
    var timestamp: LocalDateTime
) : Command

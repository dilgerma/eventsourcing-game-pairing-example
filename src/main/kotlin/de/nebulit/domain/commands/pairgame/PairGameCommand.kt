package de.nebulit.domain.commands.pairgame

import de.nebulit.common.Command
import org.axonframework.modelling.command.TargetAggregateIdentifier

data class PairGameCommand(
    @TargetAggregateIdentifier var gameName1: String,
    var gameName2: String
) : Command

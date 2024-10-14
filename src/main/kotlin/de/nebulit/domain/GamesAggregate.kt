package de.nebulit.domain

import de.nebulit.domain.commands.game.CloseGameCommand
import de.nebulit.domain.commands.opengame.OpenGameCommand
import de.nebulit.domain.commands.pairgame.PairGameCommand
import de.nebulit.events.GameClosedEvent
import de.nebulit.events.GameOpenedEvent
import de.nebulit.events.GamesPairedEvent
import org.axonframework.commandhandling.CommandHandler
import org.axonframework.eventsourcing.EventSourcingHandler
import org.axonframework.modelling.command.AggregateCreationPolicy
import org.axonframework.modelling.command.AggregateIdentifier
import org.axonframework.modelling.command.AggregateLifecycle
import org.axonframework.modelling.command.CreationPolicy
import org.axonframework.spring.stereotype.Aggregate

@Aggregate
class GamesAggregate {

  @AggregateIdentifier lateinit var aggregateId: String

  @CreationPolicy(AggregateCreationPolicy.CREATE_IF_MISSING)
  @CommandHandler
  fun handle(command: OpenGameCommand) {
    AggregateLifecycle.apply(
        GameOpenedEvent(gameName = command.gameName, timestamp = command.timestamp))
  }

  @EventSourcingHandler
  fun on(event: GameOpenedEvent) {
    // handle event
    this.aggregateId = event.gameName
  }

  @CommandHandler
  fun handle(command: CloseGameCommand) {

    AggregateLifecycle.apply(
        GameClosedEvent(gameName = command.gameName, timestamp = command.timestamp))
  }

  @EventSourcingHandler
  fun on(event: GameClosedEvent) {
    // handle event

  }

  @CommandHandler
  fun handle(command: PairGameCommand) {

    AggregateLifecycle.apply(
        GamesPairedEvent(gameName1 = command.gameName1, gameName2 = command.gameName2))
  }

  @EventSourcingHandler
  fun on(event: GamesPairedEvent) {
    // handle event

  }
}

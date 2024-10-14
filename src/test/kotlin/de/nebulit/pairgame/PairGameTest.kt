package de.nebulit.pairgame

import de.nebulit.common.Event
import de.nebulit.common.support.RandomData
import de.nebulit.domain.GamesAggregate
import de.nebulit.domain.commands.pairgame.PairGameCommand
import de.nebulit.events.GameOpenedEvent
import de.nebulit.events.GamesPairedEvent
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.axonframework.test.aggregate.AggregateTestFixture
import org.axonframework.test.aggregate.FixtureConfiguration
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/** games opened within 5 minutes and a smiliar enough name are paired */
class PairGameTest {

  private lateinit var fixture: FixtureConfiguration<GamesAggregate>

  @BeforeEach
  fun setUp() {
    fixture = AggregateTestFixture(GamesAggregate::class.java)
  }

  @Test
  fun `Pair Game Test`() {
    // GIVEN
    val events = mutableListOf<Event>()

    events.add(
        RandomData.newInstance<GameOpenedEvent> {
          gameName = "my game"
          timestamp =
              LocalDateTime.parse(
                  "15.10.2024 15:05", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
        })
    events.add(
        RandomData.newInstance<GameOpenedEvent> {
          gameName = "my game"
          timestamp =
              LocalDateTime.parse(
                  "15.10.2024 15:06", DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"))
        })

    // WHEN
    val command = PairGameCommand(gameName1 = "my game", gameName2 = "my game")

    // THEN
    val expectedEvents = mutableListOf<Event>()

    expectedEvents.add(
        RandomData.newInstance<GamesPairedEvent> {
          this.gameName1 = "my game"
          this.gameName2 = "my game"
        })

    fixture
        .given(events)
        .`when`(command)
        .expectSuccessfulHandlerExecution()
        .expectEvents(*expectedEvents.toTypedArray())
  }
}

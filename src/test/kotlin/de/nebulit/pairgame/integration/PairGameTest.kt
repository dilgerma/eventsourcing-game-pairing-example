package de.nebulit.pairgame.integration

import de.nebulit.common.support.BaseIntegrationTest
import de.nebulit.common.support.RandomData
import de.nebulit.common.support.StreamAssertions
import de.nebulit.common.support.awaitUntilAssserted
import de.nebulit.domain.commands.opengame.OpenGameCommand
import de.nebulit.events.GamesPairedEvent
import java.util.*
import org.axonframework.commandhandling.gateway.CommandGateway
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

/** games opened within 5 minutes and a smiliar enough name are paired */
class PairGameTest : BaseIntegrationTest() {

  @Autowired private lateinit var commandGateway: CommandGateway

  @Autowired private lateinit var streamAssertions: StreamAssertions

  @Test
  fun `Pair Game Test`() {

    commandGateway.sendAndWait<Any>(
        RandomData.newInstance<OpenGameCommand> { this.gameName = "game1" })
    commandGateway.sendAndWait<Any>(
        RandomData.newInstance<OpenGameCommand> { this.gameName = "game11" })

    awaitUntilAssserted { streamAssertions.assertEvent("game1") { it is GamesPairedEvent } }
  }
}

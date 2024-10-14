package de.nebulit.pairgame.internal

import de.nebulit.common.Processor
import de.nebulit.domain.commands.pairgame.PairGameCommand
import de.nebulit.events.GameOpenedEvent
import de.nebulit.games.GamesToPairReadModel
import de.nebulit.games.GamesToPairReadModelQuery
import mu.KotlinLogging
import org.axonframework.commandhandling.gateway.CommandGateway
import org.axonframework.eventhandling.EventHandler
import org.axonframework.queryhandling.QueryGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProcessorProcessor : Processor {
  var logger = KotlinLogging.logger {}

  @Autowired lateinit var commandGateway: CommandGateway

  @Autowired lateinit var queryGateway: QueryGateway

  @EventHandler
  fun on(gameOpenedEvent: GameOpenedEvent) {
    // implement pairing
    var result =
        queryGateway.query(GamesToPairReadModelQuery(), GamesToPairReadModel::class.java).get()

    var allGamesByName = result.data.associateBy { it.gameName }
    var allNames = allGamesByName.keys.filterNotNull().toList()
    var similarNames = findSimilarNames(gameOpenedEvent.gameName, allNames, 99)
    similarNames
        .filter { it !== gameOpenedEvent.gameName }
        .forEach { commandGateway.sendAndWait(PairGameCommand(it, gameOpenedEvent.gameName)) }
  }

  fun levenshteinDistance(s1: String, s2: String): Int {
    val len1 = s1.length
    val len2 = s2.length
    val dp = Array(len1 + 1) { IntArray(len2 + 1) }

    for (i in 0..len1) dp[i][0] = i
    for (j in 0..len2) dp[0][j] = j

    for (i in 1..len1) {
      for (j in 1..len2) {
        val cost = if (s1[i - 1] == s2[j - 1]) 0 else 1
        dp[i][j] =
            minOf(
                dp[i - 1][j] + 1, // deletion
                dp[i][j - 1] + 1, // insertion
                dp[i - 1][j - 1] + cost // substitution
                )
      }
    }

    return dp[len1][len2]
  }

  fun findSimilarNames(target: String, names: List<String>, threshold: Int): List<String> {
    return names.filter { levenshteinDistance(target, it) <= threshold }
  }
}

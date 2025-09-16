package io.github.yuu0922.lrinsight.api.model.rank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LeagueRankResponse(
    @SerialName("top100")
    val top100: List<Top100Response>,
    @SerialName("playerInfo")
    val playerInfo: List<PlayerInfoResponse>
)
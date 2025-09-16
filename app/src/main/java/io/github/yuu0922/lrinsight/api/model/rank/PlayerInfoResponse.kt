package io.github.yuu0922.lrinsight.api.model.rank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerInfoResponse(
    @SerialName("mid")
    val mid: String,
    @SerialName("guild")
    val guild: GuildResponse? = null,
    @SerialName("lastFetched")
    val lastFetched: String,
    @SerialName("player")
    val player: PlayerResponse,
    @SerialName("playerUnitTeamGroupMap")
    val playerUnitTeamGroupMap: PlayerUnitTeamGroupMapResponse
)
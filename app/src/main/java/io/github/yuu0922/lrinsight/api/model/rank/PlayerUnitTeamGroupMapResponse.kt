package io.github.yuu0922.lrinsight.api.model.rank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerUnitTeamGroupMapResponse(
    @SerialName("pvpteam")
    val pvpTeam: PvpTeamResponse
)
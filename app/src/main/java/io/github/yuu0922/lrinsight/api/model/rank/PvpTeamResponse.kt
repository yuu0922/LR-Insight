package io.github.yuu0922.lrinsight.api.model.rank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PvpTeamResponse(
    @SerialName("1")
    val first: List<RangerResponse> = emptyList(),
    @SerialName("2")
    val second: List<RangerResponse> = emptyList()
)
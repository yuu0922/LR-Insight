package io.github.yuu0922.lrinsight.api.model.rank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerResponse(
    @SerialName("userName")
    val userName: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("level")
    val level: Int,
    @SerialName("usePvPTeamNo")
    val usePvPTeamNo: Int
)
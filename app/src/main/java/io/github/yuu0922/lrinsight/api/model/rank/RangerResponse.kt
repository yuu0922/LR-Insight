package io.github.yuu0922.lrinsight.api.model.rank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RangerResponse(
    @SerialName("unitCode")
    val unitCode: String,
    @SerialName("unitLevel")
    val unitLevel: Int,
    @SerialName("playerUnitMaxLevel")
    val playerUnitMaxLevel: Int
)
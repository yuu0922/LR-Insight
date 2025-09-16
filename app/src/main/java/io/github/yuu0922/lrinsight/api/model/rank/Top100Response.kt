package io.github.yuu0922.lrinsight.api.model.rank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Top100Response(
    @SerialName("rank")
    val rank: Int,
    @SerialName("mid")
    val mid: String,
    @SerialName("displayName")
    val displayName: String,
    @SerialName("imageUrl")
    val imageUrl: String,
    @SerialName("nationalFlag")
    val nationalFlag: String,
    @SerialName("score")
    val score: Int
)
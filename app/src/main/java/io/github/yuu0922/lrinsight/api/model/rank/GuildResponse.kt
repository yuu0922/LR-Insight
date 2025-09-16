package io.github.yuu0922.lrinsight.api.model.rank

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildResponse(
    @SerialName("gid")
    val gid: String,
    @SerialName("name")
    val name: String,
    @SerialName("nationalFlag")
    val nationalFlag: String,
    @SerialName("level")
    val level: Int
)
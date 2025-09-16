package io.github.yuu0922.lrinsight.domain.model.rank

data class RankPlayer(
    val mid: String,
    val rank: Int,
    val displayName: String,
    val score: Int,
    val imageUrl: String,
    val nationalFlag: String,

    val guild: RankGuild?,

    val userName: String,
    val level: Int,
    val usePvPTeamNo: Int,
    val pvpTeam: RankPvpTeam,
    val lastFetched: String
)
package io.github.yuu0922.lrinsight.util

import io.github.yuu0922.lrinsight.api.model.rank.GuildResponse
import io.github.yuu0922.lrinsight.api.model.rank.LeagueRankResponse
import io.github.yuu0922.lrinsight.api.model.rank.PvpTeamResponse
import io.github.yuu0922.lrinsight.api.model.rank.RangerResponse
import io.github.yuu0922.lrinsight.domain.model.rank.RankGuild
import io.github.yuu0922.lrinsight.domain.model.rank.RankPlayer
import io.github.yuu0922.lrinsight.domain.model.rank.RankPvpTeam
import io.github.yuu0922.lrinsight.domain.model.rank.RankRanger

fun GuildResponse.toDomain(): RankGuild {
    return RankGuild(
        gid = this.gid,
        name = this.name,
        nationalFlag = this.nationalFlag,
        level = this.level
    )
}

fun PvpTeamResponse.toDomain(): RankPvpTeam {
    return RankPvpTeam(
        first = this.first.map { it.toDomain() },
        second = this.second.map { it.toDomain() }
    )
}

fun RangerResponse.toDomain(): RankRanger {
    return RankRanger(
        unitCode = this.unitCode,
        unitLevel = this.unitLevel,
        playerUnitMaxLevel = this.playerUnitMaxLevel
    )
}

fun LeagueRankResponse.toDomain(): List<RankPlayer> {
    return this.top100.mapNotNull {
        val playerInfo = this.playerInfo.find { info ->
            info.mid == it.mid
        } ?: return@mapNotNull null
        RankPlayer(
            mid = it.mid,
            rank = it.rank,
            displayName = it.displayName,
            score = it.score,
            imageUrl = it.imageUrl,
            nationalFlag = it.nationalFlag,
            guild = playerInfo.guild?.toDomain(),
            userName = playerInfo.player.userName,
            level = playerInfo.player.level,
            usePvPTeamNo = playerInfo.player.usePvPTeamNo,
            pvpTeam = playerInfo.playerUnitTeamGroupMap.pvpTeam.toDomain(),
            lastFetched = playerInfo.lastFetched
        )
    }
}
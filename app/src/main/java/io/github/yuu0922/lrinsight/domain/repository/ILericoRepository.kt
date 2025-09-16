package io.github.yuu0922.lrinsight.domain.repository

import io.github.yuu0922.lrinsight.domain.model.rank.Rank
import io.github.yuu0922.lrinsight.domain.model.rank.RankPlayer

interface ILericoRepository {
    suspend fun getPvpRanking(rank: Rank): List<RankPlayer>
}
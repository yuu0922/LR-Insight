package io.github.yuu0922.lrinsight.data.repository

import io.github.yuu0922.lrinsight.api.LericoApi
import io.github.yuu0922.lrinsight.domain.model.rank.Rank
import io.github.yuu0922.lrinsight.domain.model.rank.RankPlayer
import io.github.yuu0922.lrinsight.domain.repository.ILericoRepository
import io.github.yuu0922.lrinsight.util.toDomain
import javax.inject.Inject

class LericoRepository @Inject constructor(
    private val lericoApi: LericoApi
) : ILericoRepository {
    override suspend fun getPvpRanking(rank: Rank): List<RankPlayer> {
        val response = lericoApi.getPvpRanking(rank.name)
        return response.toDomain()
    }
}
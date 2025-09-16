package io.github.yuu0922.lrinsight.api

import io.github.yuu0922.lrinsight.api.model.rank.LeagueRankResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface LericoApi {
    @GET("/api/v2/pvp/league/rank/{rank}")
    suspend fun getPvpRanking(
        @Path("rank") rank: String
    ): LeagueRankResponse
}
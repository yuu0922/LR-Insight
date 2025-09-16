package io.github.yuu0922.lrinsight.domain.model.rank

import io.github.yuu0922.lrinsight.R

enum class Rank(val labelRes: Int) {
    LEGEND(R.string.rank_legend),
    MASTER_1(R.string.rank_master_1),
    MASTER_2(R.string.rank_master_2),
    MASTER_3(R.string.rank_master_3),
    DIAMOND_1(R.string.rank_diamond_1),
    DIAMOND_2(R.string.rank_diamond_2),
    DIAMOND_3(R.string.rank_diamond_3),
    GOLD_1(R.string.rank_gold_1),
    GOLD_2(R.string.rank_gold_2),
    GOLD_3(R.string.rank_gold_3),
    SILVER_1(R.string.rank_silver_1),
    SILVER_2(R.string.rank_silver_2),
    SILVER_3(R.string.rank_silver_3),
    BRONZE(R.string.rank_bronze)
}
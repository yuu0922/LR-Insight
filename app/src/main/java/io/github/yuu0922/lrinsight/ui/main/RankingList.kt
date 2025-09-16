package io.github.yuu0922.lrinsight.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import io.github.yuu0922.lrinsight.R
import io.github.yuu0922.lrinsight.domain.model.rank.RankGuild
import io.github.yuu0922.lrinsight.domain.model.rank.RankPlayer
import io.github.yuu0922.lrinsight.domain.model.rank.RankPvpTeam
import io.github.yuu0922.lrinsight.domain.model.rank.RankRanger
import io.github.yuu0922.lrinsight.ui.theme.AppTheme
import io.github.yuu0922.lrinsight.util.LRUtils.formatPlayerLevel
import io.github.yuu0922.lrinsight.util.LRUtils.getRangerImageUrl
import io.github.yuu0922.lrinsight.util.LRUtils.getUserProfileUrl
import io.github.yuu0922.lrinsight.util.StringUtils.addCommas

@Composable
fun RankingList(
    rankPlayers: List<RankPlayer>,
    onRangerClicked: (unitCode: String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = WindowInsets.navigationBars.asPaddingValues()
    ) {
        items(
            items = rankPlayers,
            key = { it.mid }
        ) {
            RankingItem(it, onRangerClicked)
        }
    }
}

@Composable
fun RankingItem(
    player: RankPlayer,
    onRangerClicked: (unitCode: String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "#${player.rank}",
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(Modifier.width(12.dp))

                AsyncImage(
                    model = getUserProfileUrl(player.imageUrl),
                    contentDescription = null,
                    modifier = Modifier
                        .size(52.dp)
                        .align(Alignment.CenterVertically)
                )

                Spacer(Modifier.width(12.dp))

                Column(Modifier.weight(1f)) {
                    PlayerInfoRow(player)
                    GuildInfoRow(player.guild)
                }

                Text(
                    text = player.score.toString().addCommas(),
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            Spacer(Modifier.height(12.dp))

            Column(Modifier.fillMaxWidth()) {
                RangerTeamRow(player.pvpTeam.first, onRangerClicked)
                Spacer(Modifier.height(8.dp))
                RangerTeamRow(player.pvpTeam.second, onRangerClicked)
            }
        }
    }
}

@Composable
fun RangerTeamRow(
    team: List<RankRanger>,
    onRangerClicked: (unitCode: String) -> Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp, CenterHorizontally)
    ) {
        team.forEach { ranger ->
            RangerItem(ranger, onRangerClicked)
        }
    }
}

@Composable
fun RangerItem(
    ranger: RankRanger,
    onRangerClicked: (unitCode: String) -> Unit
) {
    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier.clickable { onRangerClicked(ranger.unitCode) }
    ) {
        AsyncImage(
            model = getRangerImageUrl(ranger.unitCode),
            contentDescription = null,
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = "Lv.${ranger.unitLevel}",
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        )
    }
}

@Composable
fun PlayerInfoRow(player: RankPlayer) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Flag(
            flag = player.nationalFlag,
            modifier = Modifier.width(24.dp)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = formatPlayerLevel(player.level),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.wrapContentHeight(Alignment.Bottom)
        )

        Spacer(Modifier.width(8.dp))

        Text(
            text = player.displayName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun GuildInfoRow(guild: RankGuild?) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (guild == null) {
            Text(
                text = stringResource(R.string.guild_not_joined)
            )
        } else {
            Flag(
                flag = guild.nationalFlag,
                modifier = Modifier.width(24.dp)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = "Lv.${guild.level}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.wrapContentHeight(Alignment.Bottom)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = guild.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun RankingItemPreview() {
    val rankPlayer = RankPlayer(
        mid = "rzzep1g9skbmorngel977dwnbdh9tfq",
        rank = 78,
        displayName = "ヒロ",
        score = 2395,
        imageUrl = "profile_laby_2507",
        nationalFlag = "JP",
        guild = RankGuild(
            gid = "1e57a6f4ee1caae1087386271ffca70d",
            name = "イージス",
            nationalFlag = "JP",
            level = 10
        ),
        userName = "ヒロ",
        level = 452,
        usePvPTeamNo = 1,
        pvpTeam = RankPvpTeam(
            first = listOf(
                RankRanger(unitCode = "u1434e-boss", unitLevel = 180, playerUnitMaxLevel = 180),
                RankRanger(unitCode = "u1174h-cony", unitLevel = 235, playerUnitMaxLevel = 235),
                RankRanger(unitCode = "u1260e-brown", unitLevel = 165, playerUnitMaxLevel = 165),
                RankRanger(unitCode = "u1489e-is", unitLevel = 160, playerUnitMaxLevel = 160),
                RankRanger(unitCode = "u1483e-brown", unitLevel = 170, playerUnitMaxLevel = 170)
            ),
            second = listOf(
                RankRanger(unitCode = "u1357e-mg", unitLevel = 180, playerUnitMaxLevel = 180),
                RankRanger(unitCode = "u1522e-po", unitLevel = 160, playerUnitMaxLevel = 160),
                RankRanger(unitCode = "u1453e-ka", unitLevel = 160, playerUnitMaxLevel = 160),
                RankRanger(unitCode = "u1472e-ma", unitLevel = 160, playerUnitMaxLevel = 160),
                RankRanger(unitCode = "u1181h-sally", unitLevel = 240, playerUnitMaxLevel = 240)
            )
        ),
        lastFetched = "2025-09-13T03:55:54.792Z"
    )
    AppTheme {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            RankingItem(player = rankPlayer, onRangerClicked = {})
        }
    }
}
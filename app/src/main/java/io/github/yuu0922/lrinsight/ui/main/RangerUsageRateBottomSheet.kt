package io.github.yuu0922.lrinsight.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.github.yuu0922.lrinsight.domain.model.rank.RankPlayer
import io.github.yuu0922.lrinsight.util.LRUtils.getRangerImageUrl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RangerUsageRateBottomSheet(
    players: List<RankPlayer>,
    onDismiss: () -> Unit
) {
    val usageRate by remember(players) {
        derivedStateOf {
            val rangerCounts = mutableMapOf<String, Int>()
            players.forEach { player ->
                val rangers = (player.pvpTeam.first + player.pvpTeam.second)
                    .distinctBy { it.unitCode }

                rangers.forEach { ranger ->
                    val count = rangerCounts[ranger.unitCode] ?: 0
                    rangerCounts[ranger.unitCode] = count + 1
                }
            }
            rangerCounts
        }
    }
    val sortedUsageRate = remember(usageRate) {
        usageRate.entries.sortedByDescending { it.value }
    }

    ModalBottomSheet(onDismissRequest = { onDismiss() }) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(sortedUsageRate) { (unitCode, count) ->
                RangerUsageRateItem(
                    unitCode = unitCode,
                    count = count,
                    total = players.size
                )
            }
        }
    }
}

@Composable
fun RangerUsageRateItem(
    unitCode: String,
    count: Int,
    total: Int
) {
    val usagePercentage = if (total > 0) count.toFloat() / total else 0f

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = getRangerImageUrl(unitCode),
            contentDescription = "Ranger Icon $unitCode",
            modifier = Modifier.size(64.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            LinearProgressIndicator(
                progress = { usagePercentage },
                modifier = Modifier.fillMaxWidth()
            )
        }


        Spacer(modifier = Modifier.width(16.dp))

        Column(horizontalAlignment = Alignment.End) {
            Text(
                text = "${(usagePercentage * 100).toInt()}%",
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = "$count/$total",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
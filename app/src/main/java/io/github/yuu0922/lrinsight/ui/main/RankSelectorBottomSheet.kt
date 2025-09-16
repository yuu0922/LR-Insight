package io.github.yuu0922.lrinsight.ui.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.yuu0922.lrinsight.domain.model.rank.Rank
import io.github.yuu0922.lrinsight.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RankSelectorBottomSheet(
    ranks: List<Rank> = Rank.entries,
    selectedRank: Rank = Rank.LEGEND,
    onRankSelected: (Rank) -> Unit,
    onDismiss: () -> Unit
) {
    ModalBottomSheet(onDismissRequest = { onDismiss() }) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            items(ranks) { rank ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onRankSelected(rank)
                            onDismiss()
                        }
                        .padding(16.dp)
                ) {
                    Text(
                        text = stringResource(rank.labelRes),
                        modifier = Modifier.weight(1f)
                    )
                    if (rank == selectedRank) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun RankSelectorPreview() {
    AppTheme {
        var selectedRank by remember { mutableStateOf(Rank.LEGEND) }
        RankSelectorBottomSheet(
            selectedRank = selectedRank,
            onRankSelected = {
                selectedRank = it
            },
            onDismiss = {}
        )
    }
}
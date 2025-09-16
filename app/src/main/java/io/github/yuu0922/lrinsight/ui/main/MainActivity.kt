package io.github.yuu0922.lrinsight.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import io.github.yuu0922.lrinsight.R
import io.github.yuu0922.lrinsight.prefs.AppPrefs
import io.github.yuu0922.lrinsight.ui.components.RetryableErrorDialog
import io.github.yuu0922.lrinsight.ui.settings.SettingsDialog
import io.github.yuu0922.lrinsight.ui.theme.AppTheme
import io.github.yuu0922.lrinsight.util.AppUtils.openUrl
import io.github.yuu0922.lrinsight.util.LRUtils.getRangerPageUrl
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var appPrefs: AppPrefs

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                val useCustomTabs by appPrefs.useCustomTabsFlow.collectAsState(initial = true)

                var showRankSelector by remember { mutableStateOf(false) }
                var showRangerUsageRate by remember { mutableStateOf(false) }
                var showSettings by remember { mutableStateOf(false) }
                val selectedRank by viewModel.selectedRank.collectAsState()
                val rankPlayers by viewModel.rankPlayers.collectAsState()
                val isRefreshing by viewModel.isRefreshing.collectAsState()
                val error by viewModel.error.collectAsState()

                val context = LocalContext.current

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.clickable { showRankSelector = true }
                                    ) {
                                        Text(text = stringResource(selectedRank.labelRes))
                                        Icon(
                                            imageVector = Icons.Default.ArrowDropDown,
                                            contentDescription = "Select Rank"
                                        )
                                    }

                                    Spacer(Modifier.weight(1f))

                                    Button(
                                        onClick = {
                                            showRangerUsageRate = true
                                        },
                                    ) {
                                        Text(
                                            text = stringResource(R.string.ranger_usage_rate)
                                        )
                                    }

                                    Spacer(Modifier.weight(1f))

                                    IconButton(
                                        onClick = { showSettings = true }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "Open Settings"
                                        )
                                    }
                                }
                            }
                        )
                    },
                    contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(
                        WindowInsets.navigationBars
                    )
                ) { innerPadding ->
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        if (showRankSelector) {
                            RankSelectorBottomSheet(
                                selectedRank = selectedRank,
                                onRankSelected = {
                                    viewModel.setSelectedRank(it)
                                },
                                onDismiss = {
                                    showRankSelector = false
                                }
                            )
                        }

                        if (showRangerUsageRate) {
                            RangerUsageRateBottomSheet(
                                players = rankPlayers,
                                onDismiss = {
                                    showRangerUsageRate = false
                                }
                            )
                        }

                        if (showSettings) {
                            SettingsDialog(
                                onDismiss = {
                                    showSettings = false
                                }
                            )
                        }

                        Column(Modifier.fillMaxSize()) {
                            AnimatedVisibility(isRefreshing) {
                                LinearProgressIndicator(
                                    modifier = Modifier
                                        .padding(horizontal = 6.dp)
                                        .fillMaxWidth()
                                )
                            }

                            if (error != null) {
                                RetryableErrorDialog(
                                    error = error ?: "Unknown error",
                                    retry = { viewModel.tryRefresh() }
                                )
                            }

                            RankingList(
                                rankPlayers,
                                onRangerClicked = { unitCode ->
                                    openUrl(context, getRangerPageUrl(unitCode), useCustomTabs)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}
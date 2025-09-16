package io.github.yuu0922.lrinsight.ui.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import io.github.yuu0922.lrinsight.R
import io.github.yuu0922.lrinsight.util.AppUtils.openUrl

@Composable
fun SettingsDialog(
    settingsViewModel: SettingsViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val useCustomTabs by settingsViewModel.useCustomTabs.collectAsState()

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 4.dp,
            modifier = Modifier.fillMaxWidth().wrapContentHeight()
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                val tabs = listOf(R.string.settings, R.string.credit)
                var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

                TabRow(
                    selectedTabIndex = selectedTabIndex,
                    containerColor = Color.Transparent
                ) {
                    tabs.forEachIndexed { index, resId ->
                        Tab(
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            text = {
                                Text(
                                    text = stringResource(resId),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )
                            }
                        )
                    }
                }

                when (selectedTabIndex) {
                    0 -> SettingsTab(settingsViewModel)
                    1 -> CreditTab(useCustomTabs)
                }
            }
        }
    }
}

@Composable
fun SettingsTab(settingsViewModel: SettingsViewModel) {
    val useCustomTabs by settingsViewModel.useCustomTabs.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        SettingItem(
            label = stringResource(R.string.use_custom_tabs),
            isChecked = useCustomTabs,
            onCheckedChange = { settingsViewModel.setUseCustomTabs(it) }
        )
    }
}

@Composable
fun CreditTab(useCustomTabs: Boolean) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "API",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text("LINE Rangers Handbook")
        SelectionContainer {
            Text(
                text = "https://rangers.lerico.net",
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    openUrl(context, "https://rangers.lerico.net", useCustomTabs)
                }
            )
        }
    }
}

@Composable
fun SettingItem(
    label: String,
    isChecked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit = {}
) {
    Column {
        Text(
            text = label,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Switch(
            checked = isChecked,
            onCheckedChange = { onCheckedChange(it) }
        )
    }
}
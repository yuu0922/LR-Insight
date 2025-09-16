package io.github.yuu0922.lrinsight.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.yuu0922.lrinsight.prefs.AppPrefs
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appPrefs: AppPrefs
) : ViewModel() {
    val useCustomTabs = appPrefs.useCustomTabsFlow.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = true
    )

    fun setUseCustomTabs(value: Boolean) {
        viewModelScope.launch {
            appPrefs.setUseCustomTabs(value)
        }
    }
}
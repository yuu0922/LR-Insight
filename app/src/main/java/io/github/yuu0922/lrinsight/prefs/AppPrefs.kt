package io.github.yuu0922.lrinsight.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "app_settings")

class AppPrefs @Inject constructor(
    @ApplicationContext appContext: Context
) {
    private val dataStore = appContext.dataStore

    val useCustomTabsFlow = dataStore.data.map { it[USE_CUSTOM_TABS] ?: true }
    suspend fun setUseCustomTabs(value: Boolean) {
        dataStore.edit { it[USE_CUSTOM_TABS] = value }
    }

    companion object {
        private val USE_CUSTOM_TABS = booleanPreferencesKey("use_custom_tabs")
    }
}
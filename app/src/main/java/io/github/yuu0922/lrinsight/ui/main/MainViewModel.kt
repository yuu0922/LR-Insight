package io.github.yuu0922.lrinsight.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.yuu0922.lrinsight.data.repository.LericoRepository
import io.github.yuu0922.lrinsight.domain.model.rank.Rank
import io.github.yuu0922.lrinsight.domain.model.rank.RankPlayer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val lericoRepository: LericoRepository
) : ViewModel() {
    private val _selectedRank = MutableStateFlow(Rank.LEGEND)
    val selectedRank = _selectedRank.asStateFlow()

    private val _rankPlayers = MutableStateFlow<List<RankPlayer>>(emptyList())
    val rankPlayers = _rankPlayers.asStateFlow()

    private val _isRefreshing = MutableStateFlow(false)
    val isRefreshing = _isRefreshing.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    init {
        _selectedRank.onEach { rank ->
            refresh(rank)
        }.launchIn(viewModelScope)
    }

    private suspend fun refresh(rank: Rank) {
        _isRefreshing.value = true
        _error.value = null
        try {
            val players = withContext(Dispatchers.IO) {
                lericoRepository.getPvpRanking(rank)
            }
            _rankPlayers.value = players
        } catch (e: Exception) {
            e.printStackTrace()
            _error.value = e.message
            _rankPlayers.value = emptyList()
        } finally {
            _isRefreshing.value = false
        }
    }

    fun tryRefresh() {
        viewModelScope.launch {
            refresh(selectedRank.value)
        }
    }

    fun setSelectedRank(rank: Rank) {
        _selectedRank.value = rank
    }
}
package com.andrew.cryptocurrency_app.presintation.coin_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andrew.cryptocurrency_app.common.Constants.PARAM_COIN_ID
import com.andrew.cryptocurrency_app.common.Resource
import com.andrew.cryptocurrency_app.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailsViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _state = mutableStateOf(CoinDetailsState())
    val state: State<CoinDetailsState> = _state


    init {
        savedStateHandle.get<String>(PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }

    }

    private fun getCoin(coinId: String) {
        getCoinsUseCase(coinId).onEach {
            when (it) {
                is Resource.Success -> {
                    _state.value = CoinDetailsState(coin = it.data)
                }
                is Resource.Error -> {
                    _state.value = CoinDetailsState(error = it.message ?: "An unexpected error")
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailsState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
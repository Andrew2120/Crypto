package com.andrew.cryptocurrency_app.presintation.coin_list

import com.andrew.cryptocurrency_app.domain.model.Coin

data class CoinListState(

    val isLoading: Boolean = false,
    val coins:List<Coin> = emptyList(),
    val error :String=""
    )

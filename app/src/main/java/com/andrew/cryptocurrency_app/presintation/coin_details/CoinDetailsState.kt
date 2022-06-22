package com.andrew.cryptocurrency_app.presintation.coin_details

import com.andrew.cryptocurrency_app.domain.model.CoinDetails

data class CoinDetailsState(

    val isLoading: Boolean = false,
    val coin: CoinDetails? = null,
    val error: String = ""
)

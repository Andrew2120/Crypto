package com.andrew.cryptocurrency_app.domain.repository

import com.andrew.cryptocurrency_app.data.remote.dto.CoinDetailsDto
import com.andrew.cryptocurrency_app.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailsDto
}
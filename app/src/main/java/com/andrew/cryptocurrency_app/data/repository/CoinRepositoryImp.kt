package com.andrew.cryptocurrency_app.data.repository

import com.andrew.cryptocurrency_app.data.remote.CoinPaprikaApi
import com.andrew.cryptocurrency_app.data.remote.dto.CoinDetailsDto
import com.andrew.cryptocurrency_app.data.remote.dto.CoinDto
import com.andrew.cryptocurrency_app.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImp @Inject constructor(
    private val api: CoinPaprikaApi) : CoinRepository {

    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailsDto {
        return api.getCoinById(coinId)

    }
}
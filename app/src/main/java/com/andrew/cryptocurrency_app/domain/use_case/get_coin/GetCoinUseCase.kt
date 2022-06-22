package com.andrew.cryptocurrency_app.domain.use_case.get_coin

import com.andrew.cryptocurrency_app.common.Resource
import com.andrew.cryptocurrency_app.data.remote.dto.toCoinDetail
import com.andrew.cryptocurrency_app.domain.model.CoinDetails
import com.andrew.cryptocurrency_app.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(coinId: String): Flow<Resource<CoinDetails>> = flow{
        try {
            emit(Resource.Loading<CoinDetails>())
            val coins = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success<CoinDetails>(data = coins))

        } catch (e: HttpException) {
            emit(Resource.Error<CoinDetails>(e.localizedMessage ?: "An Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<CoinDetails>("No internet connection"))

        }
    }

}
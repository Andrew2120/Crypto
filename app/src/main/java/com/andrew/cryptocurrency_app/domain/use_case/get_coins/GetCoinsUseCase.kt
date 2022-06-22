package com.andrew.cryptocurrency_app.domain.use_case.get_coins

import com.andrew.cryptocurrency_app.common.Resource
import com.andrew.cryptocurrency_app.data.remote.dto.toCoin
import com.andrew.cryptocurrency_app.domain.model.Coin
import com.andrew.cryptocurrency_app.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading<List<Coin>>())
            val coins = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success<List<Coin>>(data = coins))

        } catch (e: HttpException) {
            emit(Resource.Error<List<Coin>>(e.localizedMessage ?: "An Unexpected error"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Coin>>("No internet connection"))

        }
    }

}
package com.cesdev.horoscapp.data.providers

import com.cesdev.horoscapp.data.providers.network.HoroscopeApiService
import com.cesdev.horoscapp.data.providers.network.response.PredictionResponse
import com.cesdev.horoscapp.domain.model.PredictionModel
import com.cesdev.horoscapp.domain.model.Repository

import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiService: HoroscopeApiService) : Repository {
    override suspend fun getPrediction(sign: String): PredictionModel? {
        runCatching { apiService.getHoroscope(sign) }.onSuccess { return it.toDomain() }
        return null
    }

}
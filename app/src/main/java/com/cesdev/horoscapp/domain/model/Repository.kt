package com.cesdev.horoscapp.domain.model

import com.cesdev.horoscapp.data.providers.network.response.PredictionResponse

interface Repository {
    suspend fun getPrediction(sign:String): PredictionModel?
}
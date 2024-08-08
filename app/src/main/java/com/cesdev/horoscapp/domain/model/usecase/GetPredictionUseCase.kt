package com.cesdev.horoscapp.domain.model.usecase

import com.cesdev.horoscapp.domain.model.Repository
import javax.inject.Inject

class GetPredictionUseCase @Inject constructor(private val repository: Repository) {
   suspend operator fun invoke(sign:String)= repository.getPrediction(sign)}

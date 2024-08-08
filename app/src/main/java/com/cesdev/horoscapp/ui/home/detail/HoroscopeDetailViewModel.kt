package com.cesdev.horoscapp.ui.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cesdev.horoscapp.domain.model.HoroscopeModel
import com.cesdev.horoscapp.domain.model.PredictionModel
import com.cesdev.horoscapp.domain.model.usecase.GetPredictionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HoroscopeDetailViewModel @Inject constructor(private val getPredictionUseCase: GetPredictionUseCase) :
    ViewModel() {
    private var _state = MutableStateFlow<HoroscopeDetailState>(HoroscopeDetailState.Loading)
    val state: StateFlow<HoroscopeDetailState> = _state
    lateinit var horoscope:HoroscopeModel
    fun getHoroscope(sign: HoroscopeModel) {
        horoscope=sign
        viewModelScope.launch() {
            _state.value = HoroscopeDetailState.Loading
            val result = withContext(Dispatchers.IO) {
                getPredictionUseCase(sign.name)
            }
            _state.value =
                if (result != null) HoroscopeDetailState.Success(result,sign) else HoroscopeDetailState.Error(
                    "Ha ocurrido un error"
                )

        }
    }

}

sealed class HoroscopeDetailState {
    data object Loading : HoroscopeDetailState()
    data class Error(val error: String) : HoroscopeDetailState()
    data class Success(val data: PredictionModel,val sign:HoroscopeModel ) : HoroscopeDetailState()


}

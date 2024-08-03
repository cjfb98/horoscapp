@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.cesdev.horoscapp.ui.home.horoscope

import androidx.lifecycle.ViewModel
import com.cesdev.horoscapp.data.providers.HoroscopeProvider
import com.cesdev.horoscapp.domain.model.HoroscopeInfo
import com.cesdev.horoscapp.domain.model.HoroscopeInfo.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class HoroscopeViewModel
    @Inject
    constructor(private val horoscopeProvider: HoroscopeProvider) : ViewModel() {
        private var _horoscope = MutableStateFlow<List<HoroscopeInfo>>(emptyList())
        val horoscope: StateFlow<List<HoroscopeInfo>> = _horoscope

        init {
            _horoscope.value = horoscopeProvider.getHoroscopes()
        }


    }

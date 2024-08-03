package com.cesdev.horoscapp.data.providers
import com.cesdev.horoscapp.domain.model.HoroscopeInfo
import com.cesdev.horoscapp.domain.model.HoroscopeInfo.*
import javax.inject.Inject

class HoroscopeProvider @Inject constructor() {
    fun getHoroscopes(): List<HoroscopeInfo> = listOf(
        Aries,
        Gemini,
        Cancer,
        Taurus,
        Capricorn,
        Scorpio,
        Sagittarius,
        Pisces,
        Libra,
        Virgo,
        Leo,
        Aquarius
    )
}
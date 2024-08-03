package com.cesdev.horoscapp.ui.home.horoscope.adapter

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.cesdev.horoscapp.databinding.ItemHoroscopeBinding
import com.cesdev.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    private val binding=ItemHoroscopeBinding.bind(view)
    fun render(horoscopeInfo: HoroscopeInfo) {
        binding.ivHoroscope.setImageDrawable(binding.ivHoroscope.context.getDrawable(horoscopeInfo.img))
        binding.tvHoroscope.text=binding.tvHoroscope.context.getString(horoscopeInfo.name )
    }
}

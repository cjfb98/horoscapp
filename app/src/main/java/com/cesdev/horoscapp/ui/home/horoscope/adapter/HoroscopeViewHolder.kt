package com.cesdev.horoscapp.ui.home.horoscope.adapter

import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.RecyclerView
import com.cesdev.horoscapp.databinding.ItemHoroscopeBinding
import com.cesdev.horoscapp.domain.model.HoroscopeInfo

class HoroscopeViewHolder(
    view: View,
) : RecyclerView.ViewHolder(view) {
    private val binding = ItemHoroscopeBinding.bind(view)
    fun render(horoscopeInfo: HoroscopeInfo, onItemSelected: (HoroscopeInfo) -> Unit) {
        binding.ivHoroscope.setImageDrawable(binding.ivHoroscope.context.getDrawable(horoscopeInfo.img))
        binding.tvHoroscope.text = binding.tvHoroscope.context.getString(horoscopeInfo.name)
        binding.iHoroscope.setOnClickListener {
            startRotation(binding.ivHoroscope) {
                onItemSelected(horoscopeInfo)
            }
//
        }
    }

    private fun startRotation(view: View, newLambda: () -> Unit) {
        view.animate().apply {
            duration = 300
            interpolator = LinearInterpolator()
            rotationBy(360f)
            withEndAction {
                newLambda()
            }
            start()
        }
    }
}

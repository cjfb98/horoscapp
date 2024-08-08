package com.cesdev.horoscapp.ui.home.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import com.cesdev.horoscapp.R
import com.cesdev.horoscapp.databinding.ActivityHorocopeDetailBinding
import com.cesdev.horoscapp.domain.model.HoroscopeModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HorocopeDetailActivity : AppCompatActivity() {
    private val horoscopeDetailViewModel: HoroscopeDetailViewModel by viewModels()
    private lateinit var binding: ActivityHorocopeDetailBinding
    private val arg: HorocopeDetailActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHorocopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        horoscopeDetailViewModel.getHoroscope(arg.type)
        initUI()
    }

    private fun initUI() {
        initListener()
        initUiState()
    }

    private fun initListener() {
        binding.ivBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun initUiState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                horoscopeDetailViewModel.state.collect {
                    when (it) {
                        is HoroscopeDetailState.Error -> errorState()
                        HoroscopeDetailState.Loading -> {
                            binding.pbHoroscopes.isVisible = true
                        }

                        is HoroscopeDetailState.Success -> ssuccessState(it)
                    }
                }
            }
        }
    }

    private fun errorState() {
        binding.pbHoroscopes.isVisible = false

    }

    private fun ssuccessState(state: HoroscopeDetailState.Success) {
        binding.pbHoroscopes.isVisible = false
        binding.tvTitle.text = state.data.sign
        binding.tvBody.text = state.data.horoscope

        val image=when (state.sign) {
            HoroscopeModel.Aries -> R.drawable.detail_aries
            HoroscopeModel.Aquarius -> R.drawable.detail_aquarius
            HoroscopeModel.Leo -> R.drawable.detail_leo
            HoroscopeModel.Gemini -> R.drawable.detail_gemini
            HoroscopeModel.Capricorn -> R.drawable.detail_capricorn
            HoroscopeModel.Virgo -> R.drawable.detail_virgo
            HoroscopeModel.Cancer -> R.drawable.detail_cancer
            HoroscopeModel.Pisces -> R.drawable.detail_pisces
            HoroscopeModel.Taurus -> R.drawable.detail_taurus
            HoroscopeModel.Sagittarius -> R.drawable.detail_sagittarius
            HoroscopeModel.Scorpio -> R.drawable.detail_scorpio
            HoroscopeModel.Libra -> R.drawable.detail_libra
        }
        binding.ivHoroscope.setImageResource(image)
    }

}
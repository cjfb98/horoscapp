package com.cesdev.horoscapp.ui.home.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.whenCreated
import androidx.navigation.navArgs
import com.cesdev.horoscapp.R
import com.cesdev.horoscapp.databinding.ActivityHorocopeDetailBinding
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
        initUI()
    }

    private fun initUI() {
        initUiState()
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

                        is HoroscopeDetailState.Success -> ssuccessState()
                    }
                }
            }
        }
    }
    private fun errorState(){

    }
    private fun ssuccessState(){

    }

}
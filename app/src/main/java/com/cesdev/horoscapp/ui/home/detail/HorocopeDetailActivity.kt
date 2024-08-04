package com.cesdev.horoscapp.ui.home.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.navArgs
import com.cesdev.horoscapp.R
import com.cesdev.horoscapp.databinding.ActivityHorocopeDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HorocopeDetailActivity : AppCompatActivity() {
    private val horoscopeDetailViewModel:HoroscopeDetailViewModel by viewModels()
    private lateinit var binding:ActivityHorocopeDetailBinding
    private val arg:HorocopeDetailActivityArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHorocopeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
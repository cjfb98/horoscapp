package com.cesdev.horoscapp.ui.home.luck

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import androidx.core.animation.doOnEnd
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.cesdev.horoscapp.R
import com.cesdev.horoscapp.databinding.FragmentLockBinding
import com.cesdev.horoscapp.ui.home.core.listener.OnSwipeTouchListener
import com.cesdev.horoscapp.ui.home.providers.RandomCardProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random
import javax.inject.Inject

@AndroidEntryPoint
class LuckFragment : Fragment() {
    private var _binding: FragmentLockBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var randomCardProvider:RandomCardProvider

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        preparePrediction()
        initListener()
    }

    private fun preparePrediction() {
        val luck=randomCardProvider.getLucky()
        luck?.let { luck->
            binding.tvluck.text=getString(luck.prediction)
            binding.ivLucky.setImageResource(luck.image)
            binding.ttvShare.setOnClickListener {
                shareResult(getString(luck.prediction))
            }
        }
    }

    private fun shareResult(string: String) {
    val sendIntent:Intent=Intent().apply {
        action=Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT,string)
        type="text/plain"
    }
        val shareIntent=Intent.createChooser(sendIntent, null)
        startActivity(sendIntent)
    }

    private fun initListener() {
      binding.ivRullete.setOnTouchListener(object : OnSwipeTouchListener(requireContext()) {
          override fun onSwipeRight() {
             spinRoulette()
          }
      } )

    }

    private fun spinRoulette() {
        val ramdon= Random()
        val degrees= ramdon.nextInt(1440)+360
        val animator=ObjectAnimator.ofFloat(binding.ivRullete,View.ROTATION,0f,degrees.toFloat())
        animator.duration=2000
        animator.interpolator=DecelerateInterpolator()
        animator.doOnEnd {
            slideCard()
        }
        animator.start()
    }
    private fun slideCard(){
        val slideCardAnimation= AnimationUtils.loadAnimation(requireContext(), R.anim.slideup)
        slideCardAnimation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {
                binding.reverse.isVisible=true
            }

            override fun onAnimationEnd(p0: Animation?) {
growCard()
            }

            override fun onAnimationRepeat(p0: Animation?) {

            }

        })
        binding.reverse.startAnimation(slideCardAnimation)
    }
    fun growCard(){
        val growAnimation = AnimationUtils.loadAnimation(requireContext(),R.anim.grow)
        growAnimation.setAnimationListener(object :Animation.AnimationListener{
            override fun onAnimationStart(p0: Animation?) {}

            override fun onAnimationEnd(p0: Animation?) {
                binding.reverse.isVisible=false
                showPremonition()
            }

            override fun onAnimationRepeat(p0: Animation?) {}

        })
        binding.reverse.startAnimation(growAnimation)


    }

    private fun showPremonition() {
        val disappearAnimation=AlphaAnimation(1.0f,0.0f)
        disappearAnimation.duration=200
        val appearAnimation=AlphaAnimation(0.0f,1.0f)
        appearAnimation.duration=1000
        disappearAnimation.setAnimationListener(object :Animation.AnimationListener{



            override fun onAnimationStart(p0: Animation?) {
                TODO("Not yet implemented")
            }

            override fun onAnimationEnd(p0: Animation?) {
                binding.preview.isVisible=false
                binding.prediction.isVisible=true
            }

            override fun onAnimationRepeat(p0: Animation?) {
                TODO("Not yet implemented")
            }

        })
        binding.preview.startAnimation(disappearAnimation)
        binding.prediction.startAnimation(appearAnimation)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLockBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}

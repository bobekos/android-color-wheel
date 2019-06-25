package com.apandroid.colorwheelapp

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.colorwheelapp.colorwheel.utils.setAlpha
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    private lateinit var colorIndicatorBackground: GradientDrawable

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        colorIndicatorBackground = GradientDrawable().apply { cornerRadius = resources.displayMetrics.density * 16 }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        colorIndicator.background = colorIndicatorBackground
        colorWheel.colorChangeListener = this::onColorWheelUpdateListener
        alphaSeekBar.alphaChangeListener = this::onAlphaSeekBarUpdateListener
        alphaSeekBar.setBarColorRgb(colorWheel.argb)
        colorIndicatorBackground.setColor(setAlpha(colorWheel.argb, alphaSeekBar.alphaValue))
    }

    private fun onColorWheelUpdateListener(argb: Int) {
        alphaSeekBar.setBarColorRgb(argb)
        colorIndicatorBackground.setColor(setAlpha(argb, alphaSeekBar.alphaValue))
    }

    private fun onAlphaSeekBarUpdateListener(alpha: Int) {
        colorIndicatorBackground.setColor(setAlpha(colorWheel.argb, alpha))
    }
}
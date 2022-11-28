package com.warchief.bullseye

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import com.warchief.bullseye.databinding.ActivityMainBinding
import kotlin.math.abs
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var sliderValue = 0
    private var targetValue = Random.nextInt(1, 100)

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        binding.hitMeButton.height = 200

        setContentView(view)

        binding.targetTextView.text = targetValue.toString()

        binding.hitMeButton.setOnClickListener {
            showResult()
        }

        binding.seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                sliderValue = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
    }

    private fun pointsForCurrentRound(): Int {
        val maxScore = 100
        val difference = abs(targetValue - sliderValue)
        return maxScore - difference
    }

    private fun showResult() {
        val dialogTitle = getString(R.string.result_dialog_title)
        val dialogMessage =
            getString(R.string.result_dialog_message, sliderValue, pointsForCurrentRound())

        val builder = AlertDialog.Builder(this)

        builder.setTitle(dialogTitle)
        builder.setMessage(dialogMessage)
        builder.setPositiveButton(R.string.hit_me_text) {dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
}
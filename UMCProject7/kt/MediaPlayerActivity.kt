package com.kjh.umcproject7

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.SeekBar
import com.kjh.umcproject7.databinding.ActivityMediaPlayerBinding

class MediaPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMediaPlayerBinding
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var handler: Handler
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMediaPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayer = MediaPlayer.create(this, R.raw.music)
        handler = Handler(Looper.getMainLooper())

        with(binding) {
            btPlayPause.setOnClickListener {
                if (isPlaying) {
                    pauseMusic()
                } else {
                    playMusic()
                }
            }
            btStop.setOnClickListener {
                stopMusic()
            }

            val durationInSeconds = mediaPlayer.duration / 1000
            val durationFormatted = String.format(
                "%02d:%02d",
                durationInSeconds / 60, // 분
                durationInSeconds % 60 // 초
            )
            tvTime.text = durationFormatted
        }

        setupSeekBar()
    }

    private fun playMusic() {
        mediaPlayer.start()
        isPlaying = true
        binding.btPlayPause.text = "일시정지"
        updateSeekBar()
    }

    private fun pauseMusic() {
        mediaPlayer.pause()
        isPlaying = false
        binding.btPlayPause.text = "재생"
    }

    private fun stopMusic() {
        mediaPlayer.stop()
        mediaPlayer.prepare()
        isPlaying = false
        binding.btPlayPause.text = "재생"
        binding.tvTime.text = "00:00"
        binding.seekBar.progress = 0
    }

    private fun setupSeekBar() {
        with(binding) {
            with(seekBar) {
                max = mediaPlayer.duration
                setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                        if (fromUser) {
                            mediaPlayer.seekTo(progress)
                        }
                    }

                    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

                    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
                })
            }
        }
    }

    private fun updateSeekBar() {
        binding.seekBar.progress = mediaPlayer.currentPosition
        if (isPlaying) {
            handler.postDelayed({ updateSeekBar() }, 1000)
            handler.post {
                val sec = mediaPlayer.duration / 1000 - mediaPlayer.currentPosition / 1000
                val durationFormatted = String.format(
                    "%02d:%02d",
                    sec / 60, // 분
                    sec % 60 // 초
                )
                binding.tvTime.text = durationFormatted
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
        handler.removeCallbacksAndMessages(null)
    }
}
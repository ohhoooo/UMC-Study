package com.kjh.umcproject7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.kjh.umcproject7.databinding.ActivityTimerBinding

class TimerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTimerBinding
    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTimerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handler = Handler()

        with(binding) {
            btStart.setOnClickListener {
                containerSetting.visibility = View.GONE
                containerCount.visibility = View.VISIBLE

                val hour = etHour.text.toString()
                val minute = etMinute.text.toString()
                val second = etSecond.text.toString()

                tvHour.text = hour
                tvMinute.text = minute
                tvSecond.text = second

                val sum = hour.toInt() * 60 * 60 + minute.toInt() * 60 + second.toInt()
                TimerThread(sum).start()
            }
        }
    }

    inner class TimerThread(private var time: Int) : Thread() {
        override fun run() {

            while (time > 0) {

                sleep(1000)
                --time

                val h = time / 3600
                val m = (time % 3600) / 60
                val s = ((time % 3600) % 60) % 60

                handler.post {
                    with(binding) {
                        tvHour.text = h.toString()
                        tvMinute.text = m.toString()
                        tvSecond.text = s.toString()
                    }
                }
            }
            handler.post {
                binding.tvFinish.text = "타이머가 종료되었습니다 !"
            }
        }
    }
}
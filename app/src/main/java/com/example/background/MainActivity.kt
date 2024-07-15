package com.example.background

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore.Audio
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val btnPlay = findViewById<Button>(R.id.btnPlay)
        val btnPause = findViewById<Button>(R.id.btnPause)
        val btnResume = findViewById<Button>(R.id.btnResume)
        val btnStop = findViewById<Button>(R.id.btnStop)

        btnPlay.setOnClickListener(onClickListenerPlay())
        btnPause.setOnClickListener(onClickListenerPause())
        btnResume.setOnClickListener(onClickListenerResume())
        btnStop.setOnClickListener(onClickListenerStop())


    }

    private fun onClickListenerPlay():(View)->Unit={
        val audioplayServiceIntent = Intent(applicationContext,AudioplayService::class.java)
        audioplayServiceIntent.putExtra(AudioplayService.FILENAME, "image.mp3")
        audioplayServiceIntent.putExtra(AudioplayService.COMMAND, AudioplayService.PLAY)
        startService(audioplayServiceIntent)
    }
    private fun onClickListenerPause(): (View) -> Unit = {
        val audioplayServiceIntent = Intent(applicationContext, AudioplayService::class.java)
        audioplayServiceIntent.putExtra(AudioplayService.COMMAND, AudioplayService.PAUSE)
        startService(audioplayServiceIntent)
    }

    private fun onClickListenerResume(): (View) -> Unit = {
        val audioplayServiceIntent = Intent(applicationContext, AudioplayService::class.java)
        audioplayServiceIntent.putExtra(AudioplayService.COMMAND, AudioplayService.RESUME)
        startService(audioplayServiceIntent)
    }

    private fun onClickListenerStop():(View)->Unit={
        val audioplayServiceIntent = Intent(applicationContext,AudioplayService::class.java)
        audioplayServiceIntent.putExtra(AudioplayService.COMMAND, AudioplayService.STOP)
        startService(audioplayServiceIntent)
    }


}
package com.example.dudnikovmov

import android.net.Uri.parse
import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.dudnikovmov.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    lateinit var bind:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.btn.visibility= View.GONE
        val mediaController=MediaController(this)
        mediaController.hide()
        var videopath="android.resource://"+packageName+"/"+R.raw.jump
        var uri=parse(videopath)
        bind.vidView.setVideoURI(uri)
        bind.vidView.setMediaController(null)

        val animat: Animation = AlphaAnimation(0.0f, 1.0f)
        animat.duration = 800 
        animat.startOffset = 20
        animat.repeatMode = Animation.REVERSE
        animat.repeatCount = Animation.INFINITE
        bind.vidView.start()

        GlobalScope.launch {
            var lol=true
            while (lol){
            if (bind.vidView.currentPosition > 10000) {
                runOnUiThread{
                    bind.txt.text="Интерактив не пройден"
                    bind.btn.startAnimation(AnimationUtils.loadAnimation(applicationContext,R.anim.btn_anim_left_to_right))
                    bind.pushBtn.startAnimation(animat)
                    setVisibility(View.VISIBLE)

                }
                for(i in 1..7){
                    runOnUiThread{
                        bind.prgR.setProgress(i*100/7)
                        bind.prgL.setProgress(i*100/7)
                        bind.txtR.text="${7-i}"
                        bind.txtL.text="${7-i}"
                    }
                    if(i<7)
                    delay(1000)
                }
                runOnUiThread{
                    bind.btn.clearAnimation()
                    bind.pushBtn.clearAnimation()
                    setVisibility(View.GONE)
                bind.txt.visibility=View.VISIBLE
                }
                lol=false
            }
            }
        }

        bind.btn.setOnClickListener {
videopath="android.resource://"+packageName+"/"+R.raw.tired
            bind.btn.clearAnimation()
            bind.pushBtn.clearAnimation()
           setVisibility(View.GONE)
                uri=parse(videopath)
            bind.vidView.pause()
                bind.vidView.setVideoURI(uri)
            bind.vidView.start()
            bind.txt.visibility=View.VISIBLE
            bind.txt.text="Интерактив успешно пройден"
        }

    }
    fun setVisibility(vis:Int){
        bind.btn.visibility=vis
        bind.prgR.visibility=vis
        bind.prgL.visibility=vis
        bind.txtR.visibility=vis
        bind.txtL.visibility=vis
        bind.pushBtn.visibility=vis
    }
}


package com.example.dudnikovmov
import android.net.Uri
import android.net.Uri.parse
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.example.dudnikovmov.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import java.net.URI
import kotlin.coroutines.coroutineContext

class MainActivity : AppCompatActivity() {
    lateinit var bind:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind= ActivityMainBinding.inflate(layoutInflater)
        setContentView(bind.root)

        bind.btn.visibility= View.GONE
        val mediaController=MediaController(this)
        mediaController.hide()
        var videopath="android.resource://"+packageName+"/"+R.raw.jumping
        var uri=parse(videopath)
        bind.vidView.setVideoURI(uri)
        bind.vidView.setMediaController(null)

var isPaused=false
        bind.vidView.start()
        //bind.prgRight.setProgress(50)
        GlobalScope.launch {
            var lol=true
            while (lol){
            if (bind.vidView.currentPosition > 10000) {
                runOnUiThread{
                    bind.txt.text="Интерактив не пройден"
                    bind.btn.startAnimation(AnimationUtils.loadAnimation(applicationContext,R.anim.btn_anim_left_to_right))
                    setVisibility(View.VISIBLE)
                }
                for(i in 1..7){
                    runOnUiThread{
                        bind.prgUpR.setProgress(i*100/7)
                        bind.prgDownR.setProgress(i*100/7)
                        bind.prgUpL.setProgress(i*100/7)
                        bind.prgDownL.setProgress(i*100/7)
                        bind.txtR.text="${7-i}"
                        bind.txtL.text="${7-i}"
                    }
                    if(i<7)
                    delay(1000)
                }
                runOnUiThread{
                    bind.btn.clearAnimation()
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
        bind.prgUpR.visibility=vis
        bind.prgDownR.visibility=vis
        bind.prgUpL.visibility=vis
        bind.prgDownL.visibility=vis
        bind.txtR.visibility=vis
        bind.txtL.visibility=vis
    }
}


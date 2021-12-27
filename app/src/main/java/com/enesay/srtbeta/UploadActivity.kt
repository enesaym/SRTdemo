package com.enesay.srtbeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.enesay.srtbeta.databinding.ActivityFeedBinding

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityFeedBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        
    }
    fun upload(view: View){

    }
    fun selectImage(view: View){

    }


}
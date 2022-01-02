package com.enesay.srtbeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import com.enesay.srtbeta.databinding.ActivityAnaGovdeBinding
import com.enesay.srtbeta.databinding.ActivityMainBinding

class AnaGovde : AppCompatActivity() {
    private lateinit var binding: ActivityAnaGovdeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAnaGovdeBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setUpTabBar()
    }
    fun setUpTabBar(){
        binding.bottomNavBar.setOnItemSelectedListener {
            when (it) {
                R.id.message -> binding.textGovde.text = "Mesajlar"
                R.id.home -> binding.textGovde.text = "Blog"
                R.id.user-> {
                    binding.textGovde.text = "Profile"
                    binding.bottomNavBar.showBadge(R.id.calender)
                }
                R.id.calender -> {
                    binding.textGovde.text = "Randevular"
                    binding.bottomNavBar.dismissBadge(R.id.calender)
                }


            }
    }


}
}
package com.enesay.srtbeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.enesay.srtbeta.databinding.ActivityAnaGovdeBinding
import com.enesay.srtbeta.databinding.ActivityMainBinding

class AnaGovde : AppCompatActivity() {
        private lateinit var binding: ActivityAnaGovdeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAnaGovdeBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //dark mode engelleme
        setUpTabBar()

    }

    fun setUpTabBar(){   //bottom nav bar kontrolleri
        binding.bottomNavBar.setOnItemSelectedListener {
            val firstFragment=BlogFragment()
            val secondFragment=ChatFragment()
            setCurrentFragment(firstFragment)
            when (it) {
                R.id.message -> setCurrentFragment(firstFragment)
                R.id.home -> setCurrentFragment(secondFragment)
                /*R.id.user-> {
                    binding.textGovde.text = "Profile"
                    binding.bottomNavBar.showBadge(R.id.calender)
                }
                R.id.calender -> {
                    binding.textGovde.text = "Randevular"
                    binding.bottomNavBar.dismissBadge(R.id.calender)
                }

                 */


            }
    }

    }
    private fun setCurrentFragment(fragment:Fragment)=
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }



}
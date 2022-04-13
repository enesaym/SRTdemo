package com.enesay.srtbeta.view

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.enesay.srtbeta.R
import com.enesay.srtbeta.adapter.Communication
import com.enesay.srtbeta.databinding.ActivityAnaGovdeBinding

class AnaGovde : AppCompatActivity(){
        private lateinit var binding: ActivityAnaGovdeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAnaGovdeBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO) //dark mode engelleme
        setUpTabBar()

    }

    /*override fun onBackPressed() {
        AlertDialog.Builder(this).apply { setTitle("Bilgilendirme")
            setMessage("Çıkmak istediğinize emin misiniz?")

            setPositiveButton("Evet") { _, _ ->
                // if user press yes, then finish the current activity
                finishAffinity()
            }

            setNegativeButton("Hayır"){_, _ ->
                // if user press no, then return the activity
                Toast.makeText(applicationContext, "Teşekkürler",
                    Toast.LENGTH_LONG).show()
            }

            setCancelable(true)
        }.create().show()
    }*/

    fun setUpTabBar(){   //bottom nav bar kontrolleri
        binding.bottomNavBar.setOnItemSelectedListener {
            val firstFragment= ChatFragment()
            val secondFragment= BlogFragment()
            val thirdFragment= RandevuFragment()
            val fifthFragment= FragmentProfil()
            setCurrentFragment(firstFragment)
            when (it) {
                R.id.message -> setCurrentFragment(firstFragment)
                R.id.home -> setCurrentFragment(secondFragment)
                R.id.user ->setCurrentFragment(fifthFragment)
                R.id.calender ->setCurrentFragment(thirdFragment)
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
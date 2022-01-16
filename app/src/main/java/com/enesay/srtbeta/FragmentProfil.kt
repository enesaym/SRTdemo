package com.enesay.srtbeta

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.enesay.srtbeta.databinding.ActivityAnaGovdeBinding
import com.enesay.srtbeta.databinding.ActivityFeedBinding
import com.enesay.srtbeta.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


class FragmentProfil : Fragment() {
    private lateinit var auth : FirebaseAuth
    private lateinit var db : FirebaseFirestore
    private lateinit var binding: ActivityFeedBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) { //gorunumler olusturulduğunda yapılacaklar
        super.onViewCreated(view, savedInstanceState)
        button4.setOnClickListener{
            FirebaseAuth.getInstance().signOut()   //cıkıs yapma
            activity?.let{

                val intent = Intent (it, MainActivity::class.java)  //giris ekranına yonlendirme
                it.startActivity(intent)
                requireActivity().finish()

            }
        }


    }



}
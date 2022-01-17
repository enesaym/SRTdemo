package com.enesay.srtbeta.view

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.enesay.srtbeta.R
import kotlinx.android.synthetic.main.activity_ana_govde.*
import kotlinx.android.synthetic.main.fragment_blog.*

import androidx.fragment.app.FragmentManager


class BlogFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fab.setOnClickListener{                        //blog paylasım sayfasına yonlendirme navigation ile
            val fr = getParentFragmentManager()?.beginTransaction()  //get fragment yontemi kullanımdan kaldırıldı.
            fr.replace(com.enesay.srtbeta.R.id.flFragment, UploadFragment())
            fr.commit()

        }

    }


}
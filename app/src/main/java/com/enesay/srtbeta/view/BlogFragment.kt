package com.enesay.srtbeta.view

import android.os.Bundle
import android.text.TextUtils.replace
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.Toast
import androidx.navigation.Navigation
import com.enesay.srtbeta.R
import kotlinx.android.synthetic.main.activity_ana_govde.*
import kotlinx.android.synthetic.main.fragment_blog.*

import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesay.srtbeta.adapter.BlogRecyclerAdapter
import com.enesay.srtbeta.databinding.FragmentBlogBinding
import com.enesay.srtbeta.databinding.FragmentUploadBinding
import com.enesay.srtbeta.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage


class BlogFragment : Fragment() {
    private lateinit var binding: FragmentBlogBinding
    private lateinit var db : FirebaseFirestore
    private lateinit var auth : FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var postArrayList: ArrayList<Post>
    private lateinit var BlogAdapter: BlogRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth=Firebase.auth
        db= Firebase.firestore

        postArrayList=ArrayList<Post>() //bos oldugunu gosterir




    }

    private fun getData(){

        db.collection("Postlar").addSnapshotListener { value, error ->

            if (error!=null){
                Toast.makeText(this.requireContext(),error.localizedMessage,Toast.LENGTH_LONG).show()
            }else{
                if(value!=null){
                    if(!value.isEmpty){
                        val documents=value.documents
                        for (document in documents){

                            //veri cekme
                            val headerComment=document.get("headerComment") as String
                            val userEmail=document.get("userEmail") as String
                            val comment=document.get("comment") as String
                            val downloadUrl=document.get("downloadUrl") as String


                            val post= Post(userEmail,headerComment,comment,downloadUrl)
                            postArrayList.add(post)
                        }
                        BlogAdapter.notifyDataSetChanged()
                    }

                }
            }




        }  //value dokumanları verir, error hataları verir.

    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentBlogBinding.inflate(inflater,container,false)   //fragment view binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        binding.recyclerView.layoutManager=LinearLayoutManager(this.requireContext())
        BlogAdapter=BlogRecyclerAdapter(postArrayList)
        binding.recyclerView.adapter=BlogAdapter

        fab.setOnClickListener{                        //blog paylasım sayfasına yonlendirme navigation ile
            val fr = getParentFragmentManager().beginTransaction()  //get fragment yontemi kullanımdan kaldırıldı.
            fr.replace(com.enesay.srtbeta.R.id.flFragment, UploadFragment())
            fr.commit()

        }
    }



}
package com.enesay.srtbeta.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesay.srtbeta.adapter.BlogRecyclerAdapter
import com.enesay.srtbeta.adapter.UserRecyclerAdapter
import com.enesay.srtbeta.databinding.FragmentChatBinding
import com.enesay.srtbeta.model.Post
import com.enesay.srtbeta.model.Users
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.auth.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_blog.*

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage
    private lateinit var UsersAdapter: UserRecyclerAdapter
    private lateinit var userArrayList: ArrayList<Users>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db=Firebase.firestore
        userArrayList=ArrayList<Users>()



    }
    private fun UserAl(){
        db.collection("Doktorlar").orderBy("ad",
            Query.Direction.DESCENDING).addSnapshotListener { value, error ->
            //whereequelto() ile filtreleme yapabiliriz. sadece kendi paylastıklarımızı gorebiliriz.
            if (error!=null){
                if(context!=null){
                    Toast.makeText(this.context,error.localizedMessage, Toast.LENGTH_SHORT).show() //cıkıs yapma sorunu
                }

            }else{
                if(value!=null){
                    if(!value.isEmpty){
                        val documents=value.documents
                        userArrayList.clear()
                        for (document in documents){

                            //veri cekme
                            val ad=document.get("ad") as String
                            //val userEmail=document.get("email") as String
                            val soyad=document.get("soyad") as String
                            //val downloadUrl=document.get("downloadUrl") as String
                            val unvan=document.get("ünvan") as String

                            val user= Users(ad,soyad,unvan)
                            userArrayList.add(user)
                            println(user.isim)

                        }
                        UsersAdapter.notifyDataSetChanged()
                    }

                }
            }




        }  //value dokumanları verir, error hataları verir.
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentChatBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        UserAl()

        binding.recylerViewUsers.layoutManager= LinearLayoutManager(this.requireContext())
        UsersAdapter= UserRecyclerAdapter(userArrayList)
        binding.recylerViewUsers.adapter=UsersAdapter

    }


}
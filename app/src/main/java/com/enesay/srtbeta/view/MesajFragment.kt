package com.enesay.srtbeta.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.enesay.srtbeta.R
import com.enesay.srtbeta.adapter.MesajRecyclerAdapter
import com.enesay.srtbeta.databinding.FragmentChatBinding
import com.enesay.srtbeta.databinding.FragmentMesajBinding
import com.enesay.srtbeta.model.Chat
import com.enesay.srtbeta.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class MesajFragment : Fragment() {

    private lateinit var binding:FragmentMesajBinding
    private lateinit var firestore:FirebaseFirestore
    private lateinit var auth:FirebaseAuth
    private lateinit var adapter:MesajRecyclerAdapter
    private var chats= arrayListOf<Chat>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firestore=Firebase.firestore
        auth=Firebase.auth
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter= MesajRecyclerAdapter()
        binding.recyclerMesaj.adapter=adapter
        binding.recyclerMesaj.layoutManager=LinearLayoutManager(requireContext())

        binding.gonderButon.setOnClickListener{
            //gonder butonuna tıklanınca
            val mesaj=binding.mesajText.text.toString()
            val gonderici= auth.currentUser!!.email.toString()
            val tarih=FieldValue.serverTimestamp()
            val alici=MesajFragmentArgs.fromBundle(requireArguments()).email

            //firestore uzerınde hashmap formatında yuklenır
            val dataMap=HashMap<String,Any>()
            dataMap.put("mesaj",mesaj)
            dataMap.put("gonderici",gonderici)
            dataMap.put("tarih",tarih)
            dataMap.put("alici",alici)
            firestore.collection("Mesajlar").add(dataMap).addOnSuccessListener {
                binding.mesajText.setText("")
            }.addOnFailureListener{
                Toast.makeText(requireContext(),it.localizedMessage,Toast.LENGTH_LONG).show()
            }
        }
        val email=auth.currentUser!!.email
        val gonderici=MesajFragmentArgs.fromBundle(requireArguments()).email
        firestore.collection("Mesajlar").whereEqualTo("gonderici",gonderici).orderBy("tarih",Query.Direction.ASCENDING).addSnapshotListener {
                value, error ->
            if(error!=null){
                Toast.makeText(requireContext(),error.localizedMessage,Toast.LENGTH_LONG).show()
            }
            else{
                if(value!=null){
                    if(value.isEmpty){
                        Toast.makeText(requireContext(),"Mesaj yok",Toast.LENGTH_LONG).show()
                    }
                    else{
                        val documents=value.documents
                        chats.clear()
                        for(document in documents){

                            val mesaj=document.get("mesaj") as String
                            val kullanici=document.get("gonderici") as String
                            val chat=Chat(kullanici,mesaj)
                            chats.add(chat)
                            adapter.chats=chats

                        }
                    }
                    adapter.notifyDataSetChanged()
                }
            }
        }




    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentMesajBinding.inflate(inflater,container,false)
        return binding.root
    }


}
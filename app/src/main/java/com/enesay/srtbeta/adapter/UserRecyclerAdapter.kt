package com.enesay.srtbeta.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.enesay.srtbeta.databinding.RecyclerChatRowBinding
import com.enesay.srtbeta.model.Post
import com.enesay.srtbeta.model.Users
import com.enesay.srtbeta.view.BlogFragmentDirections
import com.enesay.srtbeta.view.ChatFragmentDirections
import com.google.firebase.firestore.auth.User
import org.jitsi.meet.sdk.JitsiMeet

class UserRecyclerAdapter(private val userList: ArrayList<Users>) : RecyclerView.Adapter<UserRecyclerAdapter.UserHolder>(){

    class UserHolder(val binding: RecyclerChatRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserHolder {
        val binding=RecyclerChatRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserHolder(binding)

    }

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        val unvan=userList.get(position).unvan
        val isim=userList.get(position).isim
        val soyisim=userList.get(position).soyad
        val email=userList.get(position).email
        holder.binding.textView6.text=email
        holder.binding.imageView4.setOnClickListener{
            val action= ChatFragmentDirections.actionChatFragmentToMesajFragment(email)
            Navigation.findNavController(it).navigate(action)
            println(email)
        }
        holder.binding.imageView5.setOnClickListener{
            val action=ChatFragmentDirections.actionChatFragmentToVideoCallEkrani()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}
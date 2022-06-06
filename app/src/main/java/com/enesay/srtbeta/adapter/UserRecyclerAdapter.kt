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
        holder.binding.textView6.text=unvan+" "+isim+" "+soyisim
        holder.binding.imageView4.setOnClickListener{
            val action= ChatFragmentDirections.actionChatFragmentToMesajFragment()
            Navigation.findNavController(it).navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}
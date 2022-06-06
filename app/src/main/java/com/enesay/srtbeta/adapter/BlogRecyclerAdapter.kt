package com.enesay.srtbeta.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enesay.srtbeta.databinding.FragmentBlogBinding
import com.enesay.srtbeta.databinding.RecyclerRowBinding
import com.enesay.srtbeta.model.Post
import com.enesay.srtbeta.view.BlogSayfaFragment
import com.enesay.srtbeta.view.UploadFragment
import com.squareup.picasso.Picasso
import android.app.Activity
import android.text.TextUtils.replace
import android.view.View
import android.view.ViewManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.*
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.navigateUp
import com.google.android.gms.common.api.internal.LifecycleCallback.getFragment


import androidx.navigation.fragment.FragmentNavigator
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.Navigation
import com.enesay.srtbeta.view.BlogFragment
import com.enesay.srtbeta.view.BlogFragmentDirections
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.recycler_row.view.*


class BlogRecyclerAdapter(private val postlist:ArrayList<Post>) : RecyclerView.Adapter<BlogRecyclerAdapter.PostHolder>() {

    private lateinit var db:FirebaseFirestore

    class PostHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
            val binding=RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PostHolder(binding)

    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
        holder.binding.recyclerEmailText.text=postlist.get(position).email

        holder.binding.recyclerHead.text=postlist.get(position).headerComment
        holder.binding.recyclerComment.text=postlist.get(position).comment
        Picasso.get().load(postlist.get(position).downloadUrl).into(holder.binding.recyclerImageView)

        holder.itemView.setOnClickListener{
            
            val action=BlogFragmentDirections.actionBlogFragmentToBlogSayfaFragment(postlist.get(position).headerComment,postlist.get(position).comment,postlist.get(position).downloadUrl)
            Navigation.findNavController(it).navigate(action)

        }





    }

    override fun getItemCount(): Int {
        return postlist.size
    }


}
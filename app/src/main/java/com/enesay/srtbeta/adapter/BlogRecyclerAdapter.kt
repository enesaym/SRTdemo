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
import com.google.android.gms.common.api.internal.LifecycleCallback.getFragment


import androidx.navigation.fragment.FragmentNavigator
import android.os.Bundle





class BlogRecyclerAdapter(private val postlist:ArrayList<Post>) : RecyclerView.Adapter<BlogRecyclerAdapter.PostHolder>() {



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
        holder.binding.recyclerImageView.setOnClickListener{
            val fm = (holder.itemView.context as FragmentActivity).supportFragmentManager.beginTransaction()
             //fragment ile fragment arasi gecis
            fm.replace(com.enesay.srtbeta.R.id.flFragment,BlogSayfaFragment())
            fm.addToBackStack(null)
            fm.commit()
            //fragment veri aktarimi




        }
    }


    override fun getItemCount(): Int {
        return postlist.size
    }

}
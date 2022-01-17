package com.enesay.srtbeta.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enesay.srtbeta.databinding.FragmentBlogBinding
import com.enesay.srtbeta.databinding.RecyclerRowBinding
import com.enesay.srtbeta.model.Post

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
    }

    override fun getItemCount(): Int {
        return postlist.size
    }

}
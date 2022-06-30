package com.enesay.srtbeta.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.enesay.srtbeta.R
import com.enesay.srtbeta.model.Chat
import com.google.firebase.auth.FirebaseAuth

class MesajRecyclerAdapter: RecyclerView.Adapter<MesajRecyclerAdapter.ChatHolder>() {

    private val VIEW_TYPE_MESSAGE_SENT=1
    private val VIEW_TYPE_MESSAGE_RECEIVED=2
    class ChatHolder(itemview: View):RecyclerView.ViewHolder(itemview){

    }

    private val diffutil=object:DiffUtil.ItemCallback<Chat>(){
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
                return oldItem==newItem
        }

    }

    private val recylerListDiffer=AsyncListDiffer(this,diffutil)


    var chats:List<Chat>
    get()=recylerListDiffer.currentList
        set(value)=recylerListDiffer.submitList(value)

    override fun getItemViewType(position: Int): Int { //tip dondurur

        val chat=chats.get(position)
        if(chat.gonderici==FirebaseAuth.getInstance().currentUser!!.email.toString()){
            return VIEW_TYPE_MESSAGE_SENT
        }else{
            return VIEW_TYPE_MESSAGE_RECEIVED
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        if(viewType==VIEW_TYPE_MESSAGE_RECEIVED){
            val view=LayoutInflater.from(parent.context).inflate(R.layout.chats_row,parent,false)
            return ChatHolder(view)
        }
        else{
            val view=LayoutInflater.from(parent.context).inflate(R.layout.chats_row_right,parent,false)
            return ChatHolder(view)

        }


    }


    override fun onBindViewHolder(holder: ChatHolder, position: Int) {
        val textview=holder.itemView.findViewById<TextView>(R.id.chatText)
        textview.text="${chats.get(position).gonderici}:${chats.get(position).mesaj}"
    }

    override fun getItemCount(): Int {
        return chats.size
    }
}
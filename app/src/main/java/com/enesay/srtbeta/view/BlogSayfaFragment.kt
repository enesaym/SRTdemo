package com.enesay.srtbeta.view

import android.os.Bundle
import android.provider.Settings.Global.getString
import android.provider.Settings.Secure.getString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.ui.navigateUp
import androidx.core.content.res.TypedArrayUtils.getString
import androidx.core.os.bundleOf
import androidx.core.view.accessibility.AccessibilityViewCommand
import com.enesay.srtbeta.R
import com.enesay.srtbeta.adapter.BlogRecyclerAdapter
import com.enesay.srtbeta.databinding.FragmentBlogSayfaBinding
import com.enesay.srtbeta.databinding.FragmentUploadBinding
import com.enesay.srtbeta.model.Post
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_blog_sayfa.*
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.recycler_row.*


class BlogSayfaFragment : Fragment() {

    private lateinit var binding: FragmentBlogSayfaBinding
    private lateinit var db:FirebaseFirestore
    val str=arguments?.getString("header")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db= Firebase.firestore
        /*val str=arguments?.getString("header")
        binding.textView3.setText(str)*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentBlogSayfaBinding.inflate(inflater,container,false)   //fragment view binding


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments.let {
            val head=BlogSayfaFragmentArgs.fromBundle(requireArguments()).header
            val comment=BlogSayfaFragmentArgs.fromBundle(requireArguments()).comment
            val photo=BlogSayfaFragmentArgs.fromBundle(requireArguments()).photo
            Picasso.get().load(photo).into(binding.imageView)
            binding.textView3.text=head
            binding.textView4.text=comment
            println(head)

        }

        //textView3.setText(str.toString())
       // binding.textView4.text=bundle?.getString("header").toString()
       // BlogRecyclerAdapter = BlogRecyclerAdapter{
        //binding.textView4.text=recyclerEmailText.text.toString()
        /*if(arguments!=null){
            binding.textView3.setText(header)
        }*/
        //println(header)

        }


}
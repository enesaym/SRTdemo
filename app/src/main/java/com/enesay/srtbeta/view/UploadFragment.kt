package com.enesay.srtbeta.view

import android.Manifest
import android.app.Activity.RESULT_OK
import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.enesay.srtbeta.R
import com.enesay.srtbeta.databinding.ActivityUploadBinding
import com.enesay.srtbeta.databinding.FragmentUploadBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import kotlinx.android.synthetic.main.fragment_upload.*
import java.util.*


class UploadFragment : Fragment() {

    var selectedPicture : Uri? = null   //secilen resmin konumunu tutar
    //var selectedBitmap : Bitmap? = null
    private lateinit var db : FirebaseFirestore
    private lateinit var auth : FirebaseAuth
    private lateinit var storage: FirebaseStorage
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var permissionLauncher: ActivityResultLauncher<String>
    private lateinit var binding : FragmentUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerLauncher()

        auth= Firebase.auth
        db=Firebase.firestore
        storage=Firebase.storage


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=FragmentUploadBinding.inflate(inflater,container,false)   //fragment view binding
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button5.setOnClickListener{
            //UUID -> image name
            val uuid = UUID.randomUUID()
            val imageName = "$uuid.jpg"         //RANDOM FOTOĞRAF İSMİ
            val reference=storage.reference
            val imageReference=reference.child("images").child(imageName)   //random isimle kaydetmeye yarar.

            if (selectedPicture!=null){
                imageReference.putFile(selectedPicture!!).addOnSuccessListener {
                    //download url firestore kaydetme
                    val updatePictureReference=storage.reference.child("images").child(imageName) //yüklenen görselin urlsini almak.
                    updatePictureReference.downloadUrl.addOnSuccessListener {
                        val downloadUrl=it.toString()
                        val postMap = hashMapOf<String,Any>()
                        if (auth.currentUser!=null){
                            postMap.put("downloadUrl",downloadUrl)
                            postMap.put("userEmail",auth.currentUser!!.email.toString())
                            postMap.put("headerComment",binding.editTextTextPersonName.text.toString())
                            postMap.put("comment",binding.editTextTextMultiLine.text.toString())
                            postMap.put("date", Timestamp.now())

                            db.collection("Postlar").add(postMap).addOnSuccessListener {    //veri tabanına ekler

                                Toast.makeText(this.requireContext(),"GÖNDERİ PAYLAŞILDI",Toast.LENGTH_SHORT).show()
                                //blog ekranına intent yapar
                                val fr = getParentFragmentManager().beginTransaction()  //get fragment yontemi kullanımdan kaldırıldı.
                                fr.replace(com.enesay.srtbeta.R.id.flFragment, BlogFragment())
                                fr.commit()

                            }.addOnFailureListener{
                                Toast.makeText(this.requireContext(),it.localizedMessage,Toast.LENGTH_LONG).show()
                            }
                        }


                    }.addOnFailureListener{
                        Toast.makeText(this.requireContext(),it.localizedMessage,Toast.LENGTH_LONG).show()
                    }


                }.addOnFailureListener{
                    Toast.makeText(this.requireContext(),it.localizedMessage,Toast.LENGTH_LONG).show()
                }
            }




        }

        binding.imageView2.setOnClickListener{

            if (ContextCompat.checkSelfPermission(this.requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this.requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    Snackbar.make(view, "Permission needed for gallery", Snackbar.LENGTH_INDEFINITE).setAction("Give Permission",
                        View.OnClickListener {
                            permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                        }).show()
                } else {
                    permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            } else {
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)

            }
        }

    }

    //galeriye fotoğraf secmek icin yonlendirir.
    private fun registerLauncher(){
        activityResultLauncher=registerForActivityResult(ActivityResultContracts.StartActivityForResult()){result->
            if(result.resultCode==RESULT_OK){
                val intentFromResult=result.data
                if (intentFromResult!=null){
                    selectedPicture=intentFromResult.data  //uri alma
                    selectedPicture?.let {
                        binding.imageView2.setImageURI(it)
                    } //null kontrolu
                }

            }
        }
        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { result ->
            if (result) {
                //permission granted
                val intentToGallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                activityResultLauncher.launch(intentToGallery)
            } else {
                //permission denied
                Toast.makeText(this.requireContext(), "Permisson needed!", Toast.LENGTH_LONG).show()
            }
         }
    }












}
package com.enesay.srtbeta.view

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.enesay.srtbeta.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)

        auth= Firebase.auth //initialize etme

        val currentUser=auth.currentUser
        if(currentUser!=null){       //oturum hatırlama
            val intent=Intent(this, AnaGovde::class.java)
            startActivity(intent)
            //finish()
        }

    }



    //Giris yapma
    fun signInClicked(view: View){


        val email=binding.emailText.text.toString()
        val password=binding.passwordText.text.toString()

        if (emailText.equals("") || password.equals("")){
            Toast.makeText(this,"Kullanıcı adı veya sifre alanı bos gecilemez !", Toast.LENGTH_LONG).show()
        }else{
            auth.signInWithEmailAndPassword(email,password).addOnSuccessListener{
                val intent= Intent(this, AnaGovde::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,it.localizedMessage, Toast.LENGTH_LONG).show()
            }
        }

    }
    //kullanıcı kayıt
    fun signUpClicked(view: View){

        val email=binding.emailText.text.toString()
        val password= binding.passwordText.text.toString()

        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Kullanıcı adı veya şifre alanı boş geçilemez !", Toast.LENGTH_LONG).show()
        }else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener {
                //basarılı olursa cagrılır
                val intent= Intent(this@MainActivity, AnaGovde::class.java)  //diger sayfaya gecer
                startActivity(intent)
                finish() //ilk aktiviteyi sonlandırır.

            }.addOnFailureListener {
                Toast.makeText(this,it.localizedMessage, Toast.LENGTH_LONG).show()
            }//asenkron islem


        }
    }


}
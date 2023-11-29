package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMain2Binding
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Firebase.firestore
        binding.nameupdate.setText(intent.getStringExtra("name"))
        binding.passwordupdate.setText(intent.getStringExtra("password"))

        binding.button.setOnClickListener(){

            val user= hashMapOf(
                "name" to binding.nameupdate.text.toString(),
                "password" to binding.passwordupdate.text.toString(),
            )

            db.collection("users").document(intent.getStringExtra("id")!!).set(user)

            finish()
        }
    }
}
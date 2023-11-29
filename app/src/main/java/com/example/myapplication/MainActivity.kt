package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var list=arrayListOf<User>()
        var rvadapter=RvAdapter(this,list)
        binding.recyclerview.layoutManager=LinearLayoutManager(this)
        binding.recyclerview.adapter=rvadapter
        val db = Firebase.firestore
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                list.clear()
                for (document in result) {
                    var user=document.toObject(User::class.java)
                    user.id=document.id
                    list.add(user)
                }
                rvadapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
            }
    }
}
package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.RvDesignBinding
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RvAdapter(var context: Context, val list:ArrayList<User>):
    RecyclerView.Adapter<RvAdapter.viewholder>() {
    class viewholder(var binding: RvDesignBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val binding= RvDesignBinding.inflate(LayoutInflater.from(context),parent,false)

        return viewholder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        holder.binding.name.text=list.get(position).name
        holder.binding.password.text=list.get(position).password
        holder.binding.update.setOnClickListener{
            var intent=Intent(context,MainActivity2::class.java)
            intent.putExtra("name",list.get(position).name)
            intent.putExtra("password",list.get(position).password)
            intent.putExtra("id",list.get(position).id)
            context.startActivity(intent)
        }
        holder.binding.delete.setOnClickListener{
            val db =Firebase.firestore
            db.collection("users").document(list.get(position).id!!).delete()
                .addOnSuccessListener {
                    Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show()
                    list.removeAt(position)
                    notifyDataSetChanged()
                }
                .addOnFailureListener{
                    Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show()
                }

        }
    }
}
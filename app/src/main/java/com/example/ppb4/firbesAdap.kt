package com.example.ppb4

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class firbesAdap(private val listDataku: ArrayList<firbes>): RecyclerView.Adapter<firbesAdap.FirebaseViewHolder>() {
    inner class FirebaseViewHolder(myView: View):RecyclerView.ViewHolder(myView) {
        var tvName: TextView = myView.findViewById(R.id.tv_name)
        var tvPrice: TextView = myView.findViewById(R.id.tv_price)
        var btnEdit: ImageButton = myView.findViewById(R.id.btn_data_edit)
        var btnDel: ImageButton = myView.findViewById(R.id.btn_data_del)
        lateinit var ref: DatabaseReference
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FirebaseViewHolder {
        val viewku: View = LayoutInflater.from(parent.context).inflate(R.layout.data_view, parent, false)
        return FirebaseViewHolder(viewku)
    }

    override fun onBindViewHolder(holder: FirebaseViewHolder, position: Int) {

        val dataku = listDataku[position]
        holder.tvName.text = dataku.nama
        holder.tvPrice.text = dataku.harga.toString()
        holder.ref = FirebaseDatabase.getInstance().getReference("minuman").child(dataku.uid)
        holder.btnDel.setOnClickListener{
            holder.ref.removeValue()
        }
        holder.btnEdit.setOnClickListener {
            val bundle = Bundle()
            val pindah = Intent(holder.itemView.context, update::class.java)
            bundle.putString("uid", dataku.uid)
            bundle.putString("name", dataku.nama)
            bundle.putString("price", dataku.harga.toInt().toString())
            pindah.putExtras(bundle)
            holder.itemView.context.startActivity(pindah)
        }
    }

    override fun getItemCount(): Int {

        return listDataku.size
    }
}
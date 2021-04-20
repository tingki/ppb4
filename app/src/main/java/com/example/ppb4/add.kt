package com.example.ppb4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class add : AppCompatActivity() {
    lateinit var btnSubmit: Button
    lateinit var btnCancel: Button
    lateinit var etName: EditText
    lateinit var etPrice: EditText
    lateinit var ref: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)
        declaration()
        myfunction()
    }
    fun declaration(){
        btnSubmit = findViewById(R.id.btn_add_submit)
        btnCancel = findViewById(R.id.btn_add_cancel)
        etPrice = findViewById(R.id.et_add_price)
        etName = findViewById(R.id.et_add_name)
        ref = FirebaseDatabase.getInstance().getReference("minuman")
    }
    fun myfunction(){
        val pindah = Intent(this, MainActivity::class.java)
        val arrayKu = fibesAdd()
        btnSubmit.setOnClickListener {
            arrayKu.nama = etName.text.toString()
            arrayKu.harga = etPrice.text.toString().toInt()
            val taskPush = ref.push()
            taskPush.setValue(arrayKu)
            startActivity(pindah)
        }
        btnCancel.setOnClickListener {
            startActivity(pindah)
        }
    }
}
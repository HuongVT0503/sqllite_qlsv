package com.example.sqllite_qlsv

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.EditText
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this)

        val hoTen = findViewById<EditText>(R.id.edtHoTen)
        val lop = findViewById<EditText>(R.id.edtLop)
        val diem = findViewById<EditText>(R.id.edtDiem)
        val btnThem = findViewById<Button>(R.id.btnThem)
        val txtDanhSach = findViewById<TextView>(R.id.txtDanhSach)

        btnThem.setOnClickListener {
            val sv = SinhVien(0, hoTen.text.toString(), lop.text.toString(), diem.text.toString().toFloat())
            db.themSinhVien(sv)
            val list = db.layDanhSachSinhVien()
            txtDanhSach.text = list.joinToString("\n") { "${it.id}: ${it.hoTen} - ${it.lop} - ${it.diem}" }
        }
    }
}

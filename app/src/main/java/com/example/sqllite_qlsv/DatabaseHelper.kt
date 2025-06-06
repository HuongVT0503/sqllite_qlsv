package com.example.sqllite_qlsv

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "SinhVienDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE SinhVien (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                hoTen TEXT,
                lop TEXT,
                diem REAL
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS SinhVien")
        onCreate(db)
    }

    // Hàm thêm sinh viên
    fun themSinhVien(sv: SinhVien): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("hoTen", sv.hoTen)
            put("lop", sv.lop)
            put("diem", sv.diem)
        }
        val result = db.insert("SinhVien", null, values)
        return result != -1L
    }

    // Hàm lấy tất cả sinh viên
    fun layDanhSachSinhVien(): List<SinhVien> {
        val list = mutableListOf<SinhVien>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM SinhVien", null)
        if (cursor.moveToFirst()) {
            do {
                val sv = SinhVien(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getFloat(3)
                )
                list.add(sv)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }
}

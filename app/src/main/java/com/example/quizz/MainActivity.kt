package com.example.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.quizz.DBHelper.DBHelper
import com.example.quizz.DBHelper.heightScore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val diem = 5
        btnThem.setOnClickListener {
            val ten = txtName.text.toString()
            val diemCao = heightScore()
            diemCao.ten = ten
            diemCao.diemCao = diem
            DBHelper(this).addScore(diemCao)
            intent.putExtra("diem", diem)
            val intent: Intent = Intent(applicationContext, heightScore::class.java)
            startActivity(intent)
        }
        Menu.setOnClickListener {
            val intent = Intent(applicationContext, heightScore::class.java)
            startActivity(intent)
        }
    }
}
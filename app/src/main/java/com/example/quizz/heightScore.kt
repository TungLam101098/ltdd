package com.example.quizz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.quizz.DBHelper.DBHelper
import com.example.quizz.DBHelper.heightScore
import kotlinx.android.synthetic.main.activity_height_score.*

class heightScore : AppCompatActivity() {
    private var list: ArrayList<heightScore>? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_height_score)
        list = DBHelper(this).getScore()
        txtTen.text =  list!!.get(0).ten
        txtDiemcao.text = list!!.get(0).diemCao.toString()
        Menu.setOnClickListener {
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
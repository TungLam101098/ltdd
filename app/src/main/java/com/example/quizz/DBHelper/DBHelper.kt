package com.example.quizz.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.core.content.contentValuesOf
import java.io.File
import java.io.FileOutputStream

const val dbName ="Data.db";
const val dbVersionNumber = 2;

class DBHelper(private  val context: Context): SQLiteOpenHelper (context, dbName, null, dbVersionNumber) {
    private var dataBase: SQLiteDatabase? = null


    init {

        val dbExist = checkDatabase()
        if (dbExist) {

            Log.e("-----", "Database exist")
        } else {

            Log.e("-----", "Database doesn't exist")
            createDatabase()
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {}

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}

    private fun createDatabase() {
        copyDatabase()
    }

    private fun checkDatabase(): Boolean {
        val dbFile = File(context.getDatabasePath(dbName).path)
        return dbFile.exists()
    }

    private fun copyDatabase() {

        val inputStream = context.assets.open("databases/$dbName")

        val outputFile = File(context.getDatabasePath(dbName).path)
        val outputStream = FileOutputStream(outputFile)

        val bytesCopied = inputStream.copyTo(outputStream)
        Log.e("bytesCopied", "$bytesCopied")
        inputStream.close()

        outputStream.flush()
        outputStream.close()
    }

    private fun openDatabase() {
        dataBase = SQLiteDatabase.openDatabase(
            context.getDatabasePath(dbName).path,
            null,
            SQLiteDatabase.OPEN_READWRITE
        )
    }
    override fun close() {
        dataBase?.close()
        super.close()
    }

    fun getScore(): ArrayList<heightScore> {
        openDatabase()
        val cursor = dataBase?.rawQuery("select * from heightScore ORDER BY height DESC ", null)
        cursor?.moveToFirst();
        val array = ArrayList<heightScore>()
        do {
            val diemcao = heightScore()
            diemcao.id = cursor!!.getInt(0)
            diemcao.diemCao  = cursor.getInt(1)
            diemcao.ten  = cursor.getString(2)
            array.add(diemcao)
        }while (cursor?.moveToNext() == true)
        cursor.close()
        close()
        return array

    }

    fun addScore(heightScore: heightScore){
        openDatabase()
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("ten", heightScore.ten)
        contentValues.put("height",heightScore.diemCao)
        db.insert("heightScore", null, contentValues)
        db.close()

    }
}
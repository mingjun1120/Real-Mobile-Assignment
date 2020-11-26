package com.example.warehouselimmingjun.DBHelper

import android.accounts.AccountManager.KEY_PASSWORD
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.warehouselimmingjun.model.Register


class DBHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "WAREHOUSE.db"
        private val TABLE_NAME = "Register"
        private val COL_EMAIL = "Email"
        private val COL_PASS = "Pass"
        private val COL_NAME = "Name"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String =
            ("CREATE TABLE  $TABLE_NAME ($COL_EMAIL TEXT PRIMARY KEY, $COL_PASS TEXT, $COL_NAME TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD
    fun addregister(register: Register) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_EMAIL, register.email)
        values.put(COL_PASS, register.pass)
        values.put(COL_NAME, register.name)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }


    fun Login(email: String, pass: String): Boolean {
        val db = readableDatabase
        val query = "select * from $TABLE_NAME where $COL_EMAIL = '$email' and $COL_PASS = '$pass'"
        val cursor = db.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }

        cursor.close()
        return true
    }

    fun checkEmailExist(email: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_EMAIL ='$email'"
        val cursor = db.rawQuery(query, null)

        if (cursor.count > 0) {
            cursor.close()
            return true
        }
        cursor.close()
        return false
    }

    fun getEmail(email: String):String? {
        val db = this.readableDatabase
        val query = "SELECT $COL_NAME FROM $TABLE_NAME where $COL_EMAIL = '$email'"
        var cursor: Cursor? = null

        try{
            cursor = db.rawQuery(query, null)
        }catch (e: SQLiteException) {
            Log.i("dbhelper.kt", "SQLite exception")
            db.execSQL(query)
            return null
        }

        var userName: String? = null

            if (cursor.moveToFirst()) {
                do {
                    userName = cursor.getString(cursor.getColumnIndex("Name"))
                } while (cursor.moveToNext())
            }
            return userName
        }
    fun checkPassExist(pass: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_PASS ='$pass'"
        val cursor = db.rawQuery(query, null)

        if (cursor.count > 0) {
            cursor.close()
            return true
        }
        cursor.close()
        return false
    }

    fun updatePass(pass: String, email:String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_EMAIL, email)
        values.put(COL_PASS, pass)

        db.update(TABLE_NAME, values, "$COL_EMAIL= ?", arrayOf(email))
        return true
        db.close()
    }
    fun updateProfile(name: String, email:String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_EMAIL, email)
        values.put(COL_NAME, name)

        db.update(TABLE_NAME, values, "$COL_EMAIL= ?", arrayOf(email))
        return true
        db.close()
    }
}

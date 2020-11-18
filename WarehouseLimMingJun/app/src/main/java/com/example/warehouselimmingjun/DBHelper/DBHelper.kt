package com.example.warehouselimmingjun.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.warehouselimmingjun.model.Register

class DBHelper(context : Context):SQLiteOpenHelper(context,DATABASE_NAME, null,DATABASE_VER) {
    companion object {
            private val DATABASE_VER = 1
            private val DATABASE_NAME = "WAREHOUSE.db"
            private val TABLE_NAME="Register"
            private val COL_EMAIL="Email"
            private val COL_PASS="Pass"
            private val COL_NAME="Name"
        }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY : String = ("CREATE TABLE  $TABLE_NAME ($COL_EMAIL INTEGER PRIMARY KEY, $COL_PASS TEXT, $COL_NAME TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD
    fun addregister(register: Register)
    {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_EMAIL, register.email)
        values.put(COL_PASS, register.pass)
        values.put(COL_NAME, register.name)

        db.insert(TABLE_NAME, null,values)
        db.close()

    }
}
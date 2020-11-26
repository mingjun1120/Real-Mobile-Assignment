package com.example.warehouselimmingjun.DBHelper

import android.R.attr
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.util.Log
import com.example.warehouselimmingjun.model.Item


class DBHelper_item(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "Products.db"
        private val TABLE_NAME = "Item"
        private val COL_ID = "Id"
        private val COL_NAME = "Name"
        private val COL_QUANTITY = "Quantity"
        private val COL_CATEGORY = "Category"
        private val COL_PRICE = "Price"
        private val COL_SIZE = "Size"
        private val COL_LOCATION = "Location"
        private val COL_IMAGE = "Image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String =
            ("CREATE TABLE $TABLE_NAME ($COL_ID TEXT PRIMARY KEY, $COL_NAME TEXT, " +
                    "$COL_QUANTITY TEXT, $COL_CATEGORY TEXT, $COL_PRICE TEXT, " +
                    "$COL_SIZE TEXT, $COL_LOCATION TEXT, $COL_IMAGE TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    fun addItem(item: Item) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, item.id)
        values.put(COL_NAME, item.name)
        values.put(COL_QUANTITY, item.name)
        values.put(COL_CATEGORY, item.name)
        values.put(COL_PRICE, item.name)
        values.put(COL_SIZE, item.name)
        values.put(COL_LOCATION, item.name)
        values.put(COL_IMAGE, item.name)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getBitmapByName(name: String): ByteArray{
        val db = this.writableDatabase
        val select = arrayOf<String>(DBHelper_item.COL_NAME, DBHelper_item.COL_IMAGE)
        val query = "SELECT ${DBHelper_item.COL_IMAGE} FROM ${DBHelper_item.TABLE_NAME} where ${DBHelper_item.COL_NAME} = '$name'"
        val cursor = db.rawQuery(query, null)
        var result: ByteArray? = null


            if (cursor.moveToFirst()) {
                do {
                    result = cursor.getBlob(cursor.getColumnIndex(COL_IMAGE))
                } while (cursor.moveToNext())
            }

        return result!!

    }

}
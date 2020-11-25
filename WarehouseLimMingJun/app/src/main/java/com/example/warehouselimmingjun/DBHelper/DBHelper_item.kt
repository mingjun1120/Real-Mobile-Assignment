package com.example.warehouselimmingjun.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.warehouselimmingjun.model.Item
import com.example.warehouselimmingjun.model.Register

class DBHelper_item(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "WAREHOUSE.db"
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
            ("CREATE TABLE  ${DBHelper_item.TABLE_NAME} (${DBHelper_item.COL_ID} TEXT PRIMARY KEY, ${DBHelper_item.COL_NAME} TEXT, " +
                    "${DBHelper_item.COL_QUANTITY} TEXT, ${DBHelper_item.COL_CATEGORY} TEXT, ${DBHelper_item.COL_PRICE} TEXT, " +
                    "${DBHelper_item.COL_SIZE} TEXT, ${DBHelper_item.COL_LOCATION} TEXT, ${DBHelper_item.COL_IMAGE} TEXT)")
        db!!.execSQL(CREATE_TABLE_QUERY);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS ${DBHelper_item.TABLE_NAME}")
        onCreate(db!!)
    }

    fun addItem(item: Item) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(DBHelper_item.COL_ID, item.id)
        values.put(DBHelper_item.COL_NAME, item.name)
        values.put(DBHelper_item.COL_QUANTITY, item.name)
        values.put(DBHelper_item.COL_CATEGORY, item.name)
        values.put(DBHelper_item.COL_PRICE, item.name)
        values.put(DBHelper_item.COL_SIZE, item.name)
        values.put(DBHelper_item.COL_LOCATION, item.name)
        values.put(DBHelper_item.COL_IMAGE, item.name)

        db.insert(DBHelper_item.TABLE_NAME, null, values)
        db.close()

    }

}
package com.example.warehouselimmingjun.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper_History (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "History.db"
        private val TABLE_NAME = "History"
        private val COL_History_ID = "HistoryId"
        private val COL_History_Date = "HistoryDate"
        private val COL_Product_ID = "ProductId"
        private val COL_Product_Name = "ProductName"
        private val COL_StockIn = "StockIN"
        private val COL_StockOut = "StockOut"
        private val COL_Image = "Image"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String =
            ("CREATE TABLE $TABLE_NAME ($COL_History_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_History_Date TEXT ,$COL_Product_ID TEXT, $COL_Product_Name TEXT, $COL_StockIn TEXT, $COL_StockOut TEXT, $COL_Image BLOB)")
        db!!.execSQL(CREATE_TABLE_QUERY);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    fun addHistory(
        HistoryDate: String,
        ProductId: String,
        ProductName: String,
        StockIN: String,
        StockOut: String,
        image: ByteArray
    ) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_History_Date, HistoryDate)
        values.put(COL_Product_ID, ProductId)
        values.put(COL_Product_Name, ProductName)
        values.put(COL_StockIn, StockIN)
        values.put(COL_StockOut, StockOut)
        values.put(COL_Image, image)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }
}
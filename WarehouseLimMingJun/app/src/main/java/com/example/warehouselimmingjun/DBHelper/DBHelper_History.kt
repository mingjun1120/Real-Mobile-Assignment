package com.example.warehouselimmingjun.DBHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import com.example.warehouselimmingjun.StockIn
import com.example.warehouselimmingjun.model.History
import com.example.warehouselimmingjun.model.Item

class DBHelper_History (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "History1.db"
        private val TABLE_NAME = "History1"
        private val COL_History_ID = "HistoryId"
        private val COL_History_Date = "HistoryDate"
        private val COL_Product_ID = "ProductId"
        private val COL_Product_Name = "ProductName"
        private val COL_StockIn = "StockIn"
        private val COL_StockOut = "StockOut"
        private val COL_Image = "Image"
        private val COL_UserName = "UserName"
        private val COL_Email = "Email"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String =
            ("CREATE TABLE $TABLE_NAME ($COL_History_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_History_Date TEXT ,$COL_Product_ID TEXT, $COL_Product_Name TEXT, $COL_StockIn TEXT, $COL_StockOut TEXT, $COL_Image BLOB, $COL_UserName TEXT, $COL_Email TEXT)")
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
        StockIn: String,
        StockOut: String,
        image: ByteArray,
        userName:String,
        email: String
    ) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_History_Date, HistoryDate)
        values.put(COL_Product_ID, ProductId)
        values.put(COL_Product_Name, ProductName)
        values.put(COL_StockIn, StockIn)
        values.put(COL_StockOut, StockOut)
        values.put(COL_Image, image)
        values.put(COL_UserName, userName)
        values.put(COL_Email, email)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun retrieveAllItem(): List<History> {
        val historyList: ArrayList<History> = ArrayList<History>()
        val query = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            db.execSQL(query)
            return ArrayList()
        }
        var HistoryId: Int
        var HistoryDate: String
        var ProductId: String
        var ProductName: String
        var StockIn: String
        var StockOut: String
        var image: ByteArray
        var userName: String
        var email: String

        if (cursor.moveToFirst()) {
            do {
                HistoryId = cursor.getInt(cursor.getColumnIndex("HistoryId"))
                HistoryDate = cursor.getString(cursor.getColumnIndex("HistoryDate"))
                ProductId = cursor.getString(cursor.getColumnIndex("ProductId"))
                ProductName = cursor.getString(cursor.getColumnIndex("ProductName"))
                StockIn = cursor.getString(cursor.getColumnIndex("StockIn"))
                StockOut = cursor.getString(cursor.getColumnIndex("StockOut"))
                image = cursor.getBlob(cursor.getColumnIndex("Image"))
                userName = cursor.getString(cursor.getColumnIndex("UserName"))
                email = cursor.getString(cursor.getColumnIndex("Email"))
                val history = History(
                    HistoryId = HistoryId,
                    HistoryDate = HistoryDate,
                    ProductId = ProductId,
                    ProductName = ProductName,
                    StockIn = StockIn,
                    StockOut = StockOut,
                    image = image,
                    userName = userName,
                    email = email
                )
                historyList.add(history)
            } while (cursor.moveToNext())
        }
        return historyList
    }

    fun retrieveStockInItem(): List<History> {
        val historyList: ArrayList<History> = ArrayList<History>()
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_StockIn != '0' "
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            db.execSQL(query)
            return ArrayList()
        }
        var HistoryId: Int
        var HistoryDate: String
        var ProductId: String
        var ProductName: String
        var StockIn: String
        var StockOut: String
        var image: ByteArray
        var userName: String
        var email: String

        if (cursor.moveToFirst()) {
            do {
                HistoryId = cursor.getInt(cursor.getColumnIndex("HistoryId"))
                HistoryDate = cursor.getString(cursor.getColumnIndex("HistoryDate"))
                ProductId = cursor.getString(cursor.getColumnIndex("ProductId"))
                ProductName = cursor.getString(cursor.getColumnIndex("ProductName"))
                StockIn = cursor.getString(cursor.getColumnIndex("StockIn"))
                StockOut = cursor.getString(cursor.getColumnIndex("StockOut"))
                image = cursor.getBlob(cursor.getColumnIndex("Image"))
                userName = cursor.getString(cursor.getColumnIndex("UserName"))
                email = cursor.getString(cursor.getColumnIndex("Email"))
                val history = History(
                    HistoryId = HistoryId,
                    HistoryDate = HistoryDate,
                    ProductId = ProductId,
                    ProductName = ProductName,
                    StockIn = StockIn,
                    StockOut = StockOut,
                    image = image,
                    userName = userName,
                    email = email
                )
                historyList.add(history)
            } while (cursor.moveToNext())
        }
        return historyList
    }

    fun retrieveStockOutItem(): List<History> {
        val historyList: ArrayList<History> = ArrayList<History>()
        val query = "SELECT * FROM $TABLE_NAME WHERE $COL_StockOut != '0'"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            db.execSQL(query)
            return ArrayList()
        }
        var HistoryId: Int
        var HistoryDate: String
        var ProductId: String
        var ProductName: String
        var StockIn: String
        var StockOut: String
        var image: ByteArray
        var userName: String
        var email: String

        if (cursor.moveToFirst()) {
            do {
                HistoryId = cursor.getInt(cursor.getColumnIndex("HistoryId"))
                HistoryDate = cursor.getString(cursor.getColumnIndex("HistoryDate"))
                ProductId = cursor.getString(cursor.getColumnIndex("ProductId"))
                ProductName = cursor.getString(cursor.getColumnIndex("ProductName"))
                StockIn = cursor.getString(cursor.getColumnIndex("StockIn"))
                StockOut = cursor.getString(cursor.getColumnIndex("StockOut"))
                image = cursor.getBlob(cursor.getColumnIndex("Image"))
                userName = cursor.getString(cursor.getColumnIndex("UserName"))
                email = cursor.getString(cursor.getColumnIndex("Email"))
                val history = History(
                    HistoryId = HistoryId,
                    HistoryDate = HistoryDate,
                    ProductId = ProductId,
                    ProductName = ProductName,
                    StockIn = StockIn,
                    StockOut = StockOut,
                    image = image,
                    userName = userName,
                    email = email
                )
                historyList.add(history)
            } while (cursor.moveToNext())
        }
        return historyList
    }

    fun updateProfile(name:String, email:String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_Email, email)
        values.put(COL_UserName, name)

        db.update(TABLE_NAME, values, "$COL_Email= ?", arrayOf(email))
        return true
        db.close()
    }
    fun editProductName(id: String, latestName:String): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_Product_Name, latestName)
        values.put(COL_Product_ID, id)

        db.update(TABLE_NAME, values, "$COL_Product_ID= ?", arrayOf(id))
        return true
        db.close()
    }
    fun editImg(id: String, latestImg:ByteArray): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_Image, latestImg)
        values.put(COL_Product_ID, id)

        db.update(TABLE_NAME, values, "${COL_Product_ID}= ?", arrayOf(id))
        return true
        db.close()
    }

    fun deleteProduct(id: Int):Boolean{
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_History_ID, id)

        db.delete(TABLE_NAME,"$COL_History_ID = ?", arrayOf(id.toString()))
        return true
        db.close() // Closing database connection
    }
}
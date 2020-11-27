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
        private val DATABASE_NAME = "Item1.db"
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
            ("CREATE TABLE $TABLE_NAME ($COL_ID TEXT PRIMARY KEY, $COL_NAME TEXT ,$COL_QUANTITY TEXT, $COL_CATEGORY TEXT, $COL_PRICE TEXT, $COL_SIZE TEXT, $COL_LOCATION TEXT, $COL_IMAGE BLOB)")
        db!!.execSQL(CREATE_TABLE_QUERY);
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    fun addItem(
        id: String,
        name: String,
        quantity: String,
        category: String,
        price: String,
        size: String,
        location: String,
        image: ByteArray
    ) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COL_ID, id)
        values.put(COL_NAME, name)
        values.put(COL_QUANTITY, quantity)
        values.put(COL_CATEGORY, category)
        values.put(COL_PRICE, price)
        values.put(COL_SIZE, size)
        values.put(COL_LOCATION, location)
        values.put(COL_IMAGE, image)

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun retrieveShirtItem(): List<Item> {
        val itemList: ArrayList<Item> = ArrayList<Item>()
        val query = "SELECT * FROM ${TABLE_NAME} WHERE ${COL_CATEGORY} = 'Shirt'"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            db.execSQL(query)
            return ArrayList()
        }
        var id: String
        var name: String
        var quantity: String
        var category: String
        var price: String
        var size: String
        var location: String
        var image: ByteArray
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getString(cursor.getColumnIndex("Id"))
                name = cursor.getString(cursor.getColumnIndex("Name"))
                quantity = cursor.getString(cursor.getColumnIndex("Quantity"))
                category = cursor.getString(cursor.getColumnIndex("Category"))
                price = cursor.getString(cursor.getColumnIndex("Price"))
                size = cursor.getString(cursor.getColumnIndex("Size"))
                location = cursor.getString(cursor.getColumnIndex("Location"))
                image = cursor.getBlob(cursor.getColumnIndex("Image"))
                val item = Item(
                    id = id,
                    name = name,
                    quantity = quantity,
                    category = category,
                    price = price,
                    size = size,
                    location = location,
                    image = image
                )
                itemList.add(item)
            } while (cursor.moveToNext())
        }
        return itemList
    }

    fun retrieveShoesItem(): List<Item> {
        val itemList: ArrayList<Item> = ArrayList<Item>()
        val query = "SELECT * FROM ${TABLE_NAME} WHERE ${COL_CATEGORY} = 'Shoe'"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            db.execSQL(query)
            return ArrayList()
        }
        var id: String
        var name: String
        var quantity: String
        var category: String
        var price: String
        var size: String
        var location: String
        var image: ByteArray
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getString(cursor.getColumnIndex("Id"))
                name = cursor.getString(cursor.getColumnIndex("Name"))
                quantity = cursor.getString(cursor.getColumnIndex("Quantity"))
                category = cursor.getString(cursor.getColumnIndex("Category"))
                price = cursor.getString(cursor.getColumnIndex("Price"))
                size = cursor.getString(cursor.getColumnIndex("Size"))
                location = cursor.getString(cursor.getColumnIndex("Location"))
                image = cursor.getBlob(cursor.getColumnIndex("Image"))
                val item = Item(
                    id = id,
                    name = name,
                    quantity = quantity,
                    category = category,
                    price = price,
                    size = size,
                    location = location,
                    image = image
                )
                itemList.add(item)
            } while (cursor.moveToNext())
        }
        return itemList
    }

    fun retrieveAllItem(): List<Item> {
        val itemList: ArrayList<Item> = ArrayList<Item>()
        val query = "SELECT * FROM ${TABLE_NAME}"
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery(query, null)
        } catch (e: SQLiteException) {
            db.execSQL(query)
            return ArrayList()
        }
        var id: String
        var name: String
        var quantity: String
        var category: String
        var price: String
        var size: String
        var location: String
        var image: ByteArray
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getString(cursor.getColumnIndex("Id"))
                name = cursor.getString(cursor.getColumnIndex("Name"))
                quantity = cursor.getString(cursor.getColumnIndex("Quantity"))
                category = cursor.getString(cursor.getColumnIndex("Category"))
                price = cursor.getString(cursor.getColumnIndex("Price"))
                size = cursor.getString(cursor.getColumnIndex("Size"))
                location = cursor.getString(cursor.getColumnIndex("Location"))
                image = cursor.getBlob(cursor.getColumnIndex("Image"))
                val item = Item(
                    id = id,
                    name = name,
                    quantity = quantity,
                    category = category,
                    price = price,
                    size = size,
                    location = location,
                    image = image
                )
                itemList.add(item)
            } while (cursor.moveToNext())
        }
        return itemList
    }
}
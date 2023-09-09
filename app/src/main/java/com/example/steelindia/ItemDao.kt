package com.example.steelindia

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import kotlin.Exception

object ItemDAO {

    private lateinit  var  dbHelper : SQLiteDatabase

    fun init(context: Context) {
        synchronized(this) {
            if (!ItemDAO::dbHelper.isInitialized) {
                ItemDAO.dbHelper = ItemDatabaseHelper(context).writableDatabase
            }
        }
    }

    fun getItem(itemId: Long): Item? {
        try {
        val db = dbHelper
        val cursor = db.query(
            ItemDatabaseHelper.TABLE_NAME,
            null,
            "${ItemDatabaseHelper.COL_ID}=?",
            arrayOf(itemId.toString()),
            null, null, null
        )

        return if (cursor.moveToFirst()) {
            val item = cursorToItem(cursor)
            cursor.close()
            item
        } else {
            cursor.close()
            null
        }
    }
        catch(e:Exception){
            e.printStackTrace()
            Log.e("ItemDao","Error In getItem Items")
            return  null
        }
    }

    fun updateItem(item: Item): Boolean? {
        try{
        val db = dbHelper
        val values = contentValuesOfItem(item)

        val rowsAffected = db.update(
            ItemDatabaseHelper.TABLE_NAME,
            values,
            "${ItemDatabaseHelper.COL_ID}=?",
            arrayOf(item.itemId.toString())
        )

        return rowsAffected > 0}
        catch (e:Exception){
            e.printStackTrace()
            Log.e("ItemDao","Error In update Items")
            return  null
        }
    }

    fun deleteItem(itemId: Long): Boolean ?
    {  try
    {

        val db = dbHelper

        val rowsAffected = db.delete(
            ItemDatabaseHelper.TABLE_NAME,
            "${ItemDatabaseHelper.COL_ID}=?",
            arrayOf(itemId.toString())
        )

        return rowsAffected > 0
    }
    catch (e:Exception){
        e.printStackTrace()
        Log.e("ItemDao","Error In Delete  Items")
        return  null
    }
    }

    fun addItem(item: Item): Long {
        val db = dbHelper
        val values = contentValuesOfItem(item)

        return db.insert(ItemDatabaseHelper.TABLE_NAME, null, values)
    }

    fun getAllItems(): List<Item>?
    {
       try {

           val db = dbHelper
           val cursor = db.query(
               ItemDatabaseHelper.TABLE_NAME,
               null, null, null, null, null, null
           )

           val items = mutableListOf<Item>()

           while (cursor.moveToNext()) {
               items.add(cursorToItem(cursor))
           }

           cursor.close()
           return items
       }
       catch (e:Exception){
           e.printStackTrace()
           Log.e("ItemDao","Error In Delete all Items")
            return null
       }

    }

    fun deleteAllItems() {
       try {
           val db = dbHelper
           db.delete(ItemDatabaseHelper.TABLE_NAME, null, null)
       }
       catch (e:Exception) {
           e.printStackTrace()
           Log.e("ItemDao","Error In Delete all Items")
       }

    }


    @SuppressLint("Range")
    private fun cursorToItem(cursor: Cursor): Item {
        val id = cursor.getLong(cursor.getColumnIndex(ItemDatabaseHelper.COL_ID))
        val name = cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_NAME))
        val type = cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_TYPE))
        val price = cursor.getDouble(cursor.getColumnIndex(ItemDatabaseHelper.COL_PRICE))
        val quantity = cursor.getInt(cursor.getColumnIndex(ItemDatabaseHelper.COL_QUANTITY))
        val design = cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_DESIGN))
        val polish = cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_POLISH))

        return Item(id, name, type, price, quantity, design, polish)
    }

    private fun contentValuesOfItem(item: Item): ContentValues {
        val values = ContentValues()
        values.put(ItemDatabaseHelper.COL_ID, item.itemId)
        values.put(ItemDatabaseHelper.COL_NAME, item.itemName)
        values.put(ItemDatabaseHelper.COL_TYPE, item.itemType)
        values.put(ItemDatabaseHelper.COL_PRICE, item.itemPrice)
        values.put(ItemDatabaseHelper.COL_QUANTITY, item.itemQuantity)
        values.put(ItemDatabaseHelper.COL_DESIGN, item.itemDesign)
        values.put(ItemDatabaseHelper.COL_POLISH, item.itemPolish)
        return values
    }
}



data class Item(
    val itemId: Long,
    val itemName: String,
    val itemType: String,
    val itemPrice: Double,
    val itemQuantity: Int,
    val itemDesign: String,
    val itemPolish: String
)
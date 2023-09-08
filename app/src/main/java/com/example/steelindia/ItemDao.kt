package com.example.steelindia

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.steelindia.ItemDatabaseHelper.Companion.COL_ID

object ItemDao {
private lateinit var db : SQLiteDatabase

    fun init(context: Context) {
        synchronized(this) {
            if (!ItemDao::db.isInitialized) {
                ItemDao.db = ItemDatabaseHelper(context).writableDatabase
            }
        }
    }

    fun closeDb() {
       ItemDao.db.close()
    }
    fun addItem(itemId : String , itemName : String , itemType : String ,
                itemPrice : Int , itemQuantity : Int , itemDesign : String , itemPolish : String) : Long{
        val values = ContentValues().apply {
            put(DatabaseHelper.COL_ID, itemId)
            put(ItemDatabaseHelper.COL_NAME, itemName)
            put(ItemDatabaseHelper.COL_TYPE, itemType)
            put(ItemDatabaseHelper.COL_PRICE, itemPrice)
            put(ItemDatabaseHelper.COL_QUANTITY, itemQuantity)
            put(ItemDatabaseHelper.COL_DESIGN, itemDesign)
            put(ItemDatabaseHelper.COL_POLISH, itemPolish)


        }
        return ItemDao.db.insert(ItemDatabaseHelper.TABLE_NAME , null, values)
    }
    fun upadeteItem(itemId : String , itemName : String , itemType : String ,
                itemPrice : Int , itemQuantity : Int , itemDesign : String , itemPolish : String) : Long{

    }
    fun deleteItem(itemId : String , itemName : String , itemType : String ,
                itemPrice : Int , itemQuantity : Int , itemDesign : String , itemPolish : String){
        val deletedRows = db.delete(ItemDatabaseHelper.TABLE_NAME, COL_ID)

        if (deletedRows > 0) {
            // The record was successfully deleted
        } else {
            // Something went wrong or no records matched the condition
        }
    }


    @SuppressLint("Range")
    fun getItem(itemId : String, itemName : String, itemType : String,
                itemPrice : Int, itemQuantity : Int, itemDesign : String, itemPolish : String){
        val query = "SELECT * FROM ${ItemDatabaseHelper.TABLE_NAME} WHERE ${ItemDatabaseHelper.COL_ID} = ?"
        val cursor = ItemDao.db.rawQuery(query, arrayOf(itemId))
        val xyz : ItemDao.Item? = if(cursor.moveToFirst()){
            Item (
                cursor.getLong(cursor.getColumnIndex(ItemDatabaseHelper.COL_ID)),
                cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_NAME)),
                cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_TYPE)),
                cursor.getFloat(cursor.getColumnIndex(ItemDatabaseHelper.COL_PRICE)),
                cursor.getInt(cursor.getColumnIndex(ItemDatabaseHelper.COL_QUANTITY)),
                cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_DESIGN)),
                cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_POLISH))

            )


    }
        else {
            null
        }
        cursor.close()
        return xyz


    }

    @SuppressLint("Range")
    fun getAllItem(itemId : String , itemName : String , itemType : String ,
                itemPrice : Int , itemQuantity : Int , itemDesign : String , itemPolish : String){
        val query = "SELECT * FROM ${ItemDatabaseHelper.TABLE_NAME} "
        val cursor = ItemDao.db.rawQuery(query, arrayOf(itemId))
        val item: ItemDao.Item? = if (cursor.moveToFirst()) {
            Item(
                cursor.getLong(cursor.getColumnIndex(ItemDatabaseHelper.COL_ID)),
                cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_NAME)),
                cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_TYPE)),
                cursor.getFloat(cursor.getColumnIndex(ItemDatabaseHelper.COL_PRICE)),
                cursor.getInt(cursor.getColumnIndex(ItemDatabaseHelper.COL_QUANTITY)),
                cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_DESIGN)),
                cursor.getString(cursor.getColumnIndex(ItemDatabaseHelper.COL_POLISH))

            )
        } else {
            null
        }
        cursor.close()
        return item

    }
    fun deleteAllItem(itemId : String , itemName : String , itemType : String ,
                itemPrice : Float , itemQuantity : Int , itemDesign : String , itemPolish : String){
        val query = "DELETE FROM " + ItemDatabaseHelper.TABLE_NAME


    }

//TODO
//
// datebase helper id ,name , type , price , quantity , design , polish
 data class Item(
    val itemId: Long,
    val itemName: String,
    val itemType: String,
    val itemPrice: Float,
    val itemQuantity: Int,
    val itemDesign: String,
    val itemPolish: String
)
}
package com.example.steelindia

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper



class ItemDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE IF NOT EXISTS $TABLE_NAME (
                $COL_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COL_NAME TEXT,
                $COL_TYPE TEXT,
                $COL_PRICE REAL,
                $COL_QUANTITY INTEGER,
                $COL_DESIGN TEXT,
                $COL_POLISH TEXT
            )
        """.trimIndent()

        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    companion object {
        private const val DATABASE_NAME = "item_database"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "item_table"
        const val COL_ID = "itemId"
        const val COL_NAME = "itemName"
        const val COL_TYPE = "itemType"
        const val COL_PRICE = "itemPrice"
        const val COL_QUANTITY = "itemQuantity"
        const val COL_DESIGN = "itemDesign"
        const val COL_POLISH = "itemPolish"
    }
}

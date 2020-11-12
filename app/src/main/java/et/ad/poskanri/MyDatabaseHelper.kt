package et.ad.poskanri

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDatabaseHelper(
    context: Context
) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "CREATE TABLE " + TABLE_NAME + " (" + COL_PURCHASE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ITEM_NAME + " TEXT, " +
                    COL_PURCHASE_PRICE + " INTEGER, " +
                    COL_ITEM_QTY + " TEXT, " +
                    COL_TAX + " INTEGER, " +
                    COL_COMMENT + " TEXT);"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME");
        onCreate(db)
    }

    companion object {
        const val DATABASE_NAME = "POSKanri.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "purchase"
        const val COL_PURCHASE_ID = "purchase_id"
        const val COL_ITEM_NAME = "item_name"
        const val COL_PURCHASE_PRICE = "purchase_price"
        const val COL_ITEM_QTY = "item_qty"
        const val COL_TAX = "tax"
        const val COL_COMMENT = "comment"

    }

    fun addPurchaseItem(itemName: String, price: Int, qty: Int,comment:String):Long {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_ITEM_NAME, itemName)
        cv.put(COL_PURCHASE_PRICE, price)
        cv.put(COL_ITEM_QTY, qty)
        cv.put(COL_COMMENT, comment)
        return db.insert(TABLE_NAME, null, cv)
    }


    fun readAllData(): Cursor? {
        var cursor: Cursor? = null
        val db = this.readableDatabase
        if (db != null) {
            cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        }
        return cursor
    }

    fun updateData(rowId:String,purchaseItem:String,purchasePrice:Int,purchaseQty:Int):Int{
        val db=this.writableDatabase
        val cv= ContentValues()
        cv.put(COL_ITEM_NAME,purchaseItem)
        cv.put(COL_PURCHASE_PRICE,purchasePrice)
        cv.put(COL_ITEM_QTY,purchaseQty)
        return db.update(TABLE_NAME,cv,"$COL_PURCHASE_ID=?", arrayOf(rowId))
    }
    fun deleteOneRow(rowId:String):Int{
        val db = this.writableDatabase
        return db.delete(TABLE_NAME,"$COL_PURCHASE_ID=?", arrayOf(rowId))

    }

    fun deleteAllData(){
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }
}
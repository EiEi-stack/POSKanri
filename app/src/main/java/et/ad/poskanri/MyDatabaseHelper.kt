package et.ad.poskanri

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import et.ad.poskanri.dbclass.PurchaseDao
import et.ad.poskanri.dbclass.PurchaseEntity

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
                    COL_SIZE + " TEXT, " +
                    COL_ITEM_TYPE + " TEXT, " +
                    COL_ITEM_WEIGHT + " TEXT, " +
                    COL_SALE_PRICE + " TEXT, " +
                    COL_ITEM_PIC + " BLOB, " +
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
        const val COL_SIZE = "size"
        const val COL_ITEM_TYPE = "item_type"
        const val COL_ITEM_WEIGHT = "item_weight"
        const val COL_SALE_PRICE = "sale_price"
        const val COL_ITEM_PIC = "item_pic"
        const val COL_COMMENT = "comment"

    }

    fun addPurchaseItem(purchase: PurchaseEntity): Long {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_ITEM_NAME, purchase.itemName)
        cv.put(COL_PURCHASE_PRICE, purchase.purchasePrice)
        cv.put(COL_ITEM_QTY, purchase.itemQty)
        cv.put(COL_TAX, purchase.tax)
        cv.put(COL_SIZE, purchase.size)
        cv.put(COL_ITEM_TYPE, purchase.itemType)
        cv.put(COL_ITEM_WEIGHT, purchase.itemWeight)
        cv.put(COL_ITEM_PIC, purchase.image)
        cv.put(COL_COMMENT, purchase.comment)
        return db.insert(TABLE_NAME, null, cv)
    }

    fun addSalePrice(itemName:String,salePrice:String): Int {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_ITEM_NAME, salePrice)
        return db.update(TABLE_NAME, cv, "$COL_ITEM_NAME=?", arrayOf(itemName))
    }


    fun readAllData(): MutableList<PurchaseEntity> {
        val db = this.readableDatabase
        val list: MutableList<PurchaseEntity> = ArrayList()
        if (db != null) {
//            val purchaseDao = PurchaseDao()
            val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            if (cursor?.moveToFirst()!!) {
                do {
                    val purchaseItem = PurchaseEntity()
                    purchaseItem.purchaseId = cursor.getInt(cursor.getColumnIndex(COL_PURCHASE_ID))
                    purchaseItem.itemName = cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME))
                    purchaseItem.purchasePrice = cursor.getInt(
                        cursor.getColumnIndex(
                            COL_PURCHASE_PRICE
                        )
                    )
                    purchaseItem.itemQty = cursor.getInt(
                        cursor.getColumnIndex(
                            COL_ITEM_QTY
                        )
                    )
                    purchaseItem.tax = cursor.getInt(
                        cursor.getColumnIndex(
                            COL_TAX
                        )
                    )
                    purchaseItem.size = cursor.getString(
                        cursor.getColumnIndex(
                            COL_SIZE
                        )
                    )
                    purchaseItem.itemType = cursor.getString(
                        cursor.getColumnIndex(
                            COL_ITEM_TYPE
                        )
                    )
                    purchaseItem.itemWeight = cursor.getString(
                        cursor.getColumnIndex(
                            COL_ITEM_WEIGHT
                        )
                    )
                    purchaseItem.comment = cursor.getString(
                        cursor.getColumnIndex(
                            COL_COMMENT
                        )
                    )

                    if (cursor.getBlob(cursor.getColumnIndex(COL_ITEM_PIC)) != null) {
                        purchaseItem.image = cursor.getBlob(cursor.getColumnIndex(COL_ITEM_PIC))
                    }
                    list.add(purchaseItem)
                } while (cursor.moveToNext())
                cursor.close()
            }
        }
        return list
    }

    fun readPrice(itemName:String): Int {
        val db = this.readableDatabase
        var itemPrice: Int =0
        if (db != null) {
//            val purchaseDao = PurchaseDao()
            val query ="SELECT * FROM $TABLE_NAME WHERE $COL_ITEM_NAME=?";
            val cursor = db.rawQuery(query, arrayOf(itemName))
            if (cursor?.moveToFirst()!!) {
                do {
                    itemPrice = cursor.getString(cursor.getColumnIndex(COL_PURCHASE_PRICE)).toInt()

                } while (cursor.moveToNext())
                cursor.close()
            }
        }
        return itemPrice
    }

    fun updateData(rowId: String, purchase: PurchaseEntity): Int {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(COL_ITEM_NAME, purchase.itemName)
        cv.put(COL_PURCHASE_PRICE, purchase.purchasePrice)
        cv.put(COL_ITEM_QTY, purchase.itemQty)
        cv.put(COL_TAX, purchase.tax)
        cv.put(COL_SIZE, purchase.size)
        cv.put(COL_ITEM_TYPE, purchase.itemType)
        cv.put(COL_ITEM_WEIGHT, purchase.itemWeight)
        cv.put(COL_COMMENT, purchase.comment)
        return db.update(TABLE_NAME, cv, "$COL_PURCHASE_ID=?", arrayOf(rowId))
    }

    fun deleteOneRow(rowId: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COL_PURCHASE_ID=?", arrayOf(rowId))

    }

    fun deleteAllData() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }
}
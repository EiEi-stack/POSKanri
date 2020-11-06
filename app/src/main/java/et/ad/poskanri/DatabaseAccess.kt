package et.ad.poskanri

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import et.ad.poskanri.dbclass.*

class DatabaseAccess(context: Context) {
    private val openHelper: SQLiteOpenHelper
    private var database: SQLiteDatabase? = null

    fun open() {
        database = openHelper.writableDatabase

    }

    fun close() {
        if (database != null) {
            database!!.close()
        }
    }

    fun addPurchase(purchase: Purchase): Boolean {
        val cv = ContentValues()
        cv.put(COL_ITEM_NAME, purchase.itemName)
        cv.put(COL_PURCHASE_PRICE, purchase.purchasePrice)
        cv.put(COL_ITEM_QTY, purchase.itemQty)
        cv.put(COL_TAX, purchase.tax)
        cv.put(COL_COMMENT, purchase.comment)
        val result = database?.insert(TABLE_PURCHASE, null, cv)
        return result != (-1).toLong()
    }

    fun getPurchase(): MutableList<Purchase> {
        val list: MutableList<Purchase> = ArrayList()
        val queryResult = database!!.rawQuery("SELECT * from purchase", null)
        if (queryResult.moveToFirst()) {
            do {
                val purchase = Purchase()
                purchase.itemName = queryResult.getString(queryResult.getColumnIndex(COL_ITEM_NAME))
                purchase.purchasePrice = queryResult.getInt(
                    queryResult.getColumnIndex(
                        COL_PURCHASE_PRICE
                    )
                )
                purchase.itemQty = queryResult.getInt(
                    queryResult.getColumnIndex(
                        COL_ITEM_QTY
                    )
                )
                purchase.tax = queryResult.getInt(
                    queryResult.getColumnIndex(
                        COL_TAX
                    )
                )
                purchase.comment = queryResult.getString(
                    queryResult.getColumnIndex(
                        COL_COMMENT
                    )
                )

            } while (queryResult.moveToNext())
            queryResult.close()
        }
        return list
    }

    companion object {
        private var instance: DatabaseAccess? = null

        fun getInstance(context: Context): DatabaseAccess? {
            if (instance == null) {
                instance = DatabaseAccess(context)
            }
            return instance
        }
    }

    init {
        openHelper = DatabaseOpenHelper(context)
    }
}
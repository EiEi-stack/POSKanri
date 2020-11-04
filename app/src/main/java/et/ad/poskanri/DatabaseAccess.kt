package et.ad.poskanri

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import et.ad.poskanri.dbclass.Purchase

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

//    fun addPurchase(purchase: Purchase):Boolean{
////        val cv= ContentValues()
////        cv.put()
////    }

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
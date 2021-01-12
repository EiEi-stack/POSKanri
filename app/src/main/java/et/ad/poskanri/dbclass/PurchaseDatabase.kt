package et.ad.poskanri.dbclass

import android.content.Context
import androidx.room.*

@Database(entities = [PurchaseEntity::class], version = 1)
abstract class PurchaseDatabase : RoomDatabase() {
    abstract fun purchaseDao(): PurchaseDao?

    companion object {
        private var INSTANCE: PurchaseDatabase? = null
        fun getAppDatabase(context: Context): PurchaseDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder<PurchaseDatabase>(
                    context.applicationContext, PurchaseDatabase::class.java, DB_NAME
                ).allowMainThreadQueries().build()
            }
            return INSTANCE
        }

        const val DB_NAME="purchase.db"
    }

}
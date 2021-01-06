package et.ad.poskanri.dbclass

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

interface PurchaseDao {
    @Query("Select * from purchase")
   fun  readAllData():MutableList<PurchaseEntity>

    @Insert
    fun addPurchaseItem(purchase:PurchaseEntity)

    @Update
    fun updateData(rowId: String, purchase:PurchaseEntity):Int

    @Delete
    fun deleteOneRow(purchase:PurchaseEntity):Int
}
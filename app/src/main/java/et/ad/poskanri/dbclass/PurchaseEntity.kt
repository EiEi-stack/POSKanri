package et.ad.poskanri.dbclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchase")
class PurchaseEntity {
    @PrimaryKey(autoGenerate = true)
    var purchaseId = -1
    @ColumnInfo(name="itemName")
    var itemName: String = ""
    @ColumnInfo(name="purchasePrice")
    var purchasePrice = 0
    @ColumnInfo(name="itemQty")
    var itemQty = 0
    @ColumnInfo(name="tax")
    var tax = 0
    @ColumnInfo(name="size")
    var size :String= ""
    @ColumnInfo(name="itemType")
    var itemType :String= ""
    @ColumnInfo(name="itemWeight")
    var itemWeight:String = ""
    @ColumnInfo(name="comment")
    var comment :String= ""
    @ColumnInfo(name="image")
    var image = ByteArray(8192)
}
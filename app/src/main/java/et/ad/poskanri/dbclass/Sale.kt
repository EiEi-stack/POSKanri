package et.ad.poskanri.dbclass

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Sale")
class Sale {
    @PrimaryKey(autoGenerate = true)
    var saleId:Long =-1
    @ColumnInfo(name="itemName")
    var itemName: String = ""
    @ColumnInfo(name="salePrice")
    var salePrice= 0
    @ColumnInfo(name="saleRate")
    var saleRate= 0
    @ColumnInfo(name="discountPrice")
    var discountPrice= 0
}
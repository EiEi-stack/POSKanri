package et.ad.poskanri.dbclass

class Purchase {
    var purchaseId = -1
    var itemName: String = ""
    var purchasePrice = 0
    var itemQty = 0
    var tax = 0
    var size = ""
    var itemType = ""
    var itemWeight = ""
    var itemPic = ""
    var comment = ""
    var image = ByteArray(8192)
}
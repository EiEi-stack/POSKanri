package et.ad.poskanri

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

class Util {
    public fun getBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()

//        val byteBuffer = ByteBuffer.allocate(bitmap.byteCount)
//        bitmap.copyPixelsFromBuffer(byteBuffer)
//        return byteBuffer.array()

    }
    public fun getImage(image:ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray((image),0,image.size)
    }
}

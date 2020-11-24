package et.ad.poskanri

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import java.io.ByteArrayOutputStream

class Util {
    public fun getBytes(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }
    public fun getImage(image:ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray((image),0,image.size)
    }
}

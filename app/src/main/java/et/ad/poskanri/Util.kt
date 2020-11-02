package et.ad.poskanri

import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragmentary(
    fragment: Fragment,
    allowStatusLoss: Boolean = false,
    @IdRes containerViewId: Int
) {
    val ft = supportFragmentManager.beginTransaction().replace(containerViewId, fragment)
    if (!supportFragmentManager.isStateSaved) {
        ft.commit()

    } else if (allowStatusLoss) {
        ft.commitAllowingStateLoss()
    }
}

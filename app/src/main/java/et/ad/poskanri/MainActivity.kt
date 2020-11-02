package et.ad.poskanri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var btnReload: Button
    lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) { // initial transaction should be wrapped like this
            var fragment: Fragment
            fragment = PurchaseFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.frame_content, fragment)
                .commitAllowingStateLoss()
        }
        drawerLayout = findViewById(R.id.drawer)
        setSupportActionBar(findViewById(R.id.toolbar))

        val drawerToggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawer.openDrawer(GravityCompat.START)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val fragment: Fragment
        when (item.itemId) {
            R.id.nav_purchase -> {
                fragment = PurchaseFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_content, fragment)
                    .commitAllowingStateLoss()
            }
            R.id.nav_sale -> {
                fragment = SaleFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_content, fragment)
                    .commitAllowingStateLoss()
            }
            R.id.nav_customer_detail -> {
                fragment = CustomerFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_content, fragment)
                    .commitAllowingStateLoss()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

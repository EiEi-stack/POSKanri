package et.ad.poskanri

import DisplayPurchaseFragment
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var drawerLayout: DrawerLayout
    lateinit var toolbar: Toolbar
    lateinit var navView: NavigationView
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
        title = getString(R.string.purchase)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        drawerLayout = findViewById(R.id.drawer)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(toggle)
        navView.setNavigationItemSelectedListener(this)
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
                title = getString(R.string.purchase)
            }
            R.id.nav_sale -> {
                fragment = SaleFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_content, fragment)
                    .commitAllowingStateLoss()
                title = getString(R.string.sale)
            }
            R.id.nav_customer_detail -> {
                fragment = CustomerFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frame_content, fragment)
                    .commitAllowingStateLoss()
                title = getString(R.string.customer_detail)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}

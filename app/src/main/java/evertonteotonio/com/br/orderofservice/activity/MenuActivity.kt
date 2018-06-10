package evertonteotonio.com.br.orderofservice.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import evertonteotonio.com.br.orderofservice.R
import kotlinx.android.synthetic.main.activity_menu_controller.*


abstract class MenuActivity : AppCompatActivity(), OnNavigationItemSelectedListener{

    protected var navigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getContentViewId());

        navigationView = navigation
        navigationView?.setOnNavigationItemSelectedListener(this);

    }

    private fun updateNavigationBarState() {
        val actionId = getNavigationMenuItemId()
        selectBottomNavigationBarItem(actionId)
    }

    fun selectBottomNavigationBarItem(itemId: Int) {
        val item = navigationView?.getMenu()?.findItem(itemId)
        item?.isChecked = true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        navigationView?.postDelayed({
            val itemId = item.itemId
            if (itemId == R.id.navigation_home) {
                startActivity(Intent(this, OrderListActivity::class.java))
            } else if (itemId == R.id.navigation_new_service) {
                startActivity(Intent(this, OrderServiceActivity::class.java))
            } else if (itemId == R.id.navigation_about) {
                startActivity(Intent(this, AboutActivity::class.java))
            }
            finish()
        }, 100)
        return true
    }

    override fun onStart() {
        super.onStart()
        updateNavigationBarState()
    }

    // Remove inter-activity transition to avoid screen tossing on tapping bottom navigation items
    public override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    internal abstract fun getContentViewId(): Int

    internal abstract fun getNavigationMenuItemId(): Int

}

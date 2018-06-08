package evertonteotonio.com.br.orderofservice.activity

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.BottomNavigationView.OnNavigationItemSelectedListener
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.fragment.AboutFragment
import kotlinx.android.synthetic.main.activity_menu_controller.*
import evertonteotonio.com.br.orderofservice.fragment.CadCliFragment


abstract class MenuActivity : AppCompatActivity(), OnNavigationItemSelectedListener{

    protected var navigationView: BottomNavigationView? = null
    val fragmentMan = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_menu_controller)

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
        }, 300)
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

//    fun createFragmentAbout()
//    {
//        val aboutFragment = AboutFragment()
//        openFragment(aboutFragment)
//    }

//    private fun openFragment(fragment: Fragment) {
//        val transaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragmentholder, fragment)
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }

//    fun clearFragments()
//    {
//        val fragmentManager = supportFragmentManager
//        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
//    }

//    fun createFragmentServiceOrder()
//    {
//        val fragment = CadCliFragment()
//        openFragment(fragment)
//    }
}

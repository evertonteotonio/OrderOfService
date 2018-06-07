package evertonteotonio.com.br.orderofservice.controller

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.fragment.AboutFragment
import kotlinx.android.synthetic.main.activity_menu_controller.*
import evertonteotonio.com.br.orderofservice.fragment.CadCliFragment


class MenuController : AppCompatActivity(), ViewPager.OnPageChangeListener {

    val fragmentMan = supportFragmentManager

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        //Limpando os fragments
        //val fragmentManager = supportFragmentManager
        //fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        clearFragments()

        when (item.itemId) {
            R.id.navigation_home -> {
                //message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_new_service -> {
                //message.setText(R.string.title_dashboard)
                createFragmentServiceOrder()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about -> {
//                message.setText(R.string.title_about)
//                return@OnNavigationItemSelectedListener true

                //val intent = Intent(this@MenuController, AboutController::class.java)
                //startActivity(intent)
                //this@MenuController.finish()

                //message.setText(R.string.title_about)
                createFragmentAbout()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_controller)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    fun createFragmentAbout()
    {
        val aboutFragment = AboutFragment()
        openFragment(aboutFragment)
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun clearFragments()
    {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun createFragmentServiceOrder()
    {
        val fragment = CadCliFragment()
        openFragment(fragment)
    }


//    fun showHideFieldsContact(view: View)
//    {
//        val actionContactFields  = txtShowHideFieldsContact
//    }


    override fun onPageScrollStateChanged(state: Int) {

    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

    }

    override fun onPageSelected(position: Int) {

    }


}

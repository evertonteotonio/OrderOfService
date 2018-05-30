package evertonteotonio.com.br.orderofservice.controller

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.fragment.AboutFragment
import kotlinx.android.synthetic.main.activity_menu_controller.*

class MenuController : AppCompatActivity() {

    val fragmentMan = supportFragmentManager

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        //Limpando os fragments
        //val fragmentManager = supportFragmentManager
        //fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)

        clearFragments()

        when (item.itemId) {
            R.id.navigation_home -> {
                message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                message.setText(R.string.title_dashboard)
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
        val transaction = fragmentMan.beginTransaction()
        val fragment = AboutFragment()
        transaction.replace(R.id.fragmentholder, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun clearFragments()
    {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}

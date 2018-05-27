package evertonteotonio.com.br.orderofservice.controller

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import evertonteotonio.com.br.orderofservice.R
import kotlinx.android.synthetic.main.activity_menu_controller.*

class MenuController : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
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

                val intent = Intent(this@MenuController, AboutController::class.java)
                startActivity(intent)
                this@MenuController.finish()
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_controller)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}

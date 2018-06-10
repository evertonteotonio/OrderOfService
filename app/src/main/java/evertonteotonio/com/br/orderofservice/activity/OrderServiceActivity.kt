package evertonteotonio.com.br.orderofservice.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.view.View
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.fragment.*
import evertonteotonio.com.br.orderofservice.repository.ClientRepository
import kotlinx.android.synthetic.main.activity_order_service.*
import kotlinx.android.synthetic.main.fragment_cad_cli.*
import java.util.*

class OrderServiceActivity : MenuActivity() {


    val fragmentMan = supportFragmentManager

    private val mOnNavigationItemSelectedListener = NavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_menu_cad_cli -> {
                openFragment(CadCliFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu_address -> {
                openFragment(AboutFragment())
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_about -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_order_service)
        openFragment(CadCliFragment())
        //navigation_menu_cad_cli.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigation_menu_cad_cli.setNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    override fun getContentViewId(): Int {
        return R.layout.activity_order_service
    }

    override
    fun getNavigationMenuItemId(): Int {
        return R.id.navigation_new_service
    }

    fun saveDataClient(view: View)
    {
        val nameCli = nameCli
        val emailCli = emailCli
        val cellPhone = cellPhone
        val phoneCli = phoneCli

        ClientRepository(this).create(
                UUID.randomUUID().toString(),
                nameCli.toString(),
                emailCli.toString(),
                cellPhone.toString(),
                phoneCli.toString()
        )

    }

    fun clearFragments()
    {
        val fragmentManager = supportFragmentManager
        fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentholder_order, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}

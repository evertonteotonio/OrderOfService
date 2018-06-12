package evertonteotonio.com.br.orderofservice.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.fragment.*
import evertonteotonio.com.br.orderofservice.repository.ClientRepository
import kotlinx.android.synthetic.main.activity_order_service.*
import kotlinx.android.synthetic.main.fragment_address_cli.*
import kotlinx.android.synthetic.main.fragment_cad_cli.*
import org.jetbrains.anko.toast
import java.util.*

class OrderServiceActivity : MenuActivity() {


    val fragmentMan = supportFragmentManager

    val fragmentCadCli = CadCliFragment()
    val fragmentCliAddress = AddressCliFragment()
    val fragmentTask = TaskFragment()
    val fragmentSave = SaveFragment()


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        when (item.itemId) {
            R.id.navigation_menu_cad_cli -> {
                openFragment(fragmentCadCli)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu_address -> {

                openFragment(fragmentCliAddress)
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_task -> {
                openFragment(fragmentTask)
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_save -> {
                openFragment(fragmentSave)
                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

    fun validateForm()
    {

    }

    fun validateDataCli() : Boolean
    {
        val nameCli = fragmentCadCli.nameCli.text
//        val emailCli = this.emailCli.text
//        val cellPhone = this.cellPhone.text
//        val phoneCli = this.phoneCli.text

        if (nameCli.isNullOrEmpty()) {
            toast("O nome é requerido!")
            return false
        }
        return true
//        else if (emailCli.isNullOrEmpty()){
//            toast("O email é requerido!")
//        } else if (cellPhone.isNullOrEmpty()){
//            toast("O celular é requerido!")
//        } else if (phoneCli.isNullOrEmpty()){
//            toast("O telefone é requerido!")
//        }

    }

    fun validateDataAddress() : Boolean
    {

        if (fragmentCliAddress?.cep == null){
            toast("Você precisa preencher as fazes anteriores!")
            return false
        }

        val cep = fragmentCliAddress.cep.text
        val address = fragmentCliAddress.address.text
        val number = fragmentCliAddress.number.text
        val district = fragmentCliAddress.district.text
        val city = fragmentCliAddress.city.text
        val uf = fragmentCliAddress.uf.text

        if (cep.isNullOrEmpty()){
            toast("O CPF é requerido!")
            return false
        } else if (address.isNullOrEmpty()){
            toast("O endereço é requerido!")
            return false
        } else if (number.isNullOrEmpty()){
            toast("O número é requerido!")
            return false
        } else if (district.isNullOrEmpty()){
            toast("O bairro é requerido!")
            return false
        } else if (city.isNullOrEmpty()){
            toast("A cidade é requerido!")
            return false
        } else if (address.isNullOrEmpty()){
            toast("O estado é requerido!")
            return false
        }
        return true
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        openFragment(fragmentCliAddress)
        openFragment(fragmentCadCli)
        navigation_menu_cad_cli.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
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
        val nameCli = this.nameCli
        val emailCli = this.emailCli
        val cellPhone = this.cellPhone
        val phoneCli = this.phoneCli

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

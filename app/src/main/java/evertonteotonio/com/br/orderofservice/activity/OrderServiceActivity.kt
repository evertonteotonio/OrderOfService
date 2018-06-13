package evertonteotonio.com.br.orderofservice.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import android.widget.LinearLayout
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.fragment.*
import evertonteotonio.com.br.orderofservice.repository.ClientRepository
import kotlinx.android.synthetic.main.activity_order_service.*
import kotlinx.android.synthetic.main.fragment_address_cli.*
import kotlinx.android.synthetic.main.fragment_cad_cli.*
import kotlinx.android.synthetic.main.fragment_save.*
import kotlinx.android.synthetic.main.fragment_task.*
import java.util.*

class OrderServiceActivity : MenuActivity() {


    val fragmentMan = supportFragmentManager

    val fragmentCadCli = CadCliFragment()
    val fragmentCliAddress = AddressCliFragment()
    val fragmentTask = TaskFragment()
    val fragmentSave = SaveFragment()


    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->

        //clearFragments()


        when (item.itemId) {
            R.id.navigation_menu_cad_cli -> {

                fragmentholder_task.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_order.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_address.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_cli.setVisibility(LinearLayout.VISIBLE);

                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_menu_address -> {


                fragmentholder_cli.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_task.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_order.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_address.setVisibility(LinearLayout.VISIBLE);
                //openFragmentAddress(fragmentCliAddress)
                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_task -> {


                fragmentholder_cli.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_order.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_address.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_task.setVisibility(LinearLayout.VISIBLE);

                return@OnNavigationItemSelectedListener true

            }
            R.id.navigation_save -> {

                loadInfoService()
                fragmentholder_cli.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_task.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_address.setVisibility(LinearLayout.INVISIBLE);
                fragmentholder_order.setVisibility(LinearLayout.VISIBLE);

                return@OnNavigationItemSelectedListener true

            }
        }
        false
    }

    fun loadInfoService()
    {
        fragmentSave.tvNameCli.text = "Nome: " + nameCli.text.toString()
        fragmentSave.tvEmailCli.text = "Email: " + emailCli.text.toString()
        fragmentSave.tvCellPhone.text = "Celular: " + cellPhone.text.toString()
        fragmentSave.tvPhone.text = "Telefone: " + phoneCli.text.toString()


        fragmentSave.tvCep.text = "CEP: " + cep.text.toString()
        fragmentSave.tvAddress.text = "Endereço: " + address.text.toString()
        fragmentSave.tvNumber.text = "Número: " + number.text.toString()
        fragmentSave.tvDistrict.text = "Bairro: " + district.text.toString()
        fragmentSave.tvCity.text = "Cidade: " + city.text.toString()
        fragmentSave.tvUf.text = "Estado: " + uf.text.toString()

        fragmentSave.tvDescription.text = "Descrição: " + description.text.toString()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        openFragmentCli(fragmentCadCli)
        openFragmentAddress(fragmentCliAddress)
        openFragmentTask(fragmentTask)
        openFragment(fragmentSave)

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

    fun openFragmentCli(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentholder_cli, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun openFragmentAddress(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentholder_address, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun openFragmentTask(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentholder_task, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


    fun saveOrderService(view: View)
    {

    }


}

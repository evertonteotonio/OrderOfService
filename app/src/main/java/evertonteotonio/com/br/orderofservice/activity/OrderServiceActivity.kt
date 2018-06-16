package evertonteotonio.com.br.orderofservice.activity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.View
import android.widget.LinearLayout
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.fragment.*
import evertonteotonio.com.br.orderofservice.repository.AddressRepository
import evertonteotonio.com.br.orderofservice.repository.ClientRepository
import evertonteotonio.com.br.orderofservice.repository.OrderServiceRepository
import evertonteotonio.com.br.orderofservice.repository.TaskRepository
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
        tvNameCli.text = "Nome: " + nameCli.text.toString()
        tvEmailCli.text = "Email: " + emailCli.text.toString()
        tvCellPhone.text = "Celular: " + cellPhone.text.toString()
        tvPhone.text = "Telefone: " + phoneCli.text.toString()


        tvCep.text = "CEP: " + cep.text.toString()
        tvAddress.text = "Endereço: " + address.text.toString()
        tvNumber.text = "Número: " + number.text.toString()
        tvDistrict.text = "Bairro: " + district.text.toString()
        tvCity.text = "Cidade: " + city.text.toString()
        tvUf.text = "Estado: " + uf.text.toString()

        tvDescription.text = "Descrição: " + description.text.toString()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(savedInstanceState == null){
            openFragmentCli(fragmentCadCli)
            openFragmentAddress(fragmentCliAddress)
            openFragmentTask(fragmentTask)
            openFragment(fragmentSave)
        }

        navigation_menu_cad_cli.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun getContentViewId(): Int {
        return R.layout.activity_order_service
    }

    override
    fun getNavigationMenuItemId(): Int {
        return R.id.navigation_new_service
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

        val clientId = UUID.randomUUID().toString()
        val cli = ClientRepository(this).create(
                clientId,
                this.nameCli.text.toString(),
                this.emailCli.text.toString(),
                this.cellPhone.text.toString(),
                this.phoneCli.text.toString()
        )

        val addressId = UUID.randomUUID().toString()
        val addr = AddressRepository(this).create(
                addressId,
                cep.text.toString(),
                address.text.toString(),
                number.text.toString(),
                district.text.toString(),
                city.text.toString(),
                uf.text.toString()
        )

        val taskId = UUID.randomUUID().toString()
        val task = TaskRepository(this).create(
                taskId,
                description.text.toString()
        )

        if (cli){

            val order = OrderServiceRepository(this).create(
                    "1223",
                    clientId,
                    taskId,
                    addressId,
                    "teste"
            )
            val test = 0

        }

    }


}

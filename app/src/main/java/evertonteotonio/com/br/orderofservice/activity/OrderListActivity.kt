package evertonteotonio.com.br.orderofservice.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.adapter.OrderAdapter
import evertonteotonio.com.br.orderofservice.model.OrderService
import evertonteotonio.com.br.orderofservice.repository.OrderServiceRepository
import android.support.design.widget.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener
import com.karumi.dexter.listener.single.PermissionListener



class OrderListActivity : MenuActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: OrderAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var listOrderService: ArrayList<OrderService>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val dialogPermissionListener = DialogOnDeniedPermissionListener.Builder
                .withContext(this.baseContext)
                .withTitle("Permissão de chamada")
                .withMessage("É necessária a permissão para efetuar essa ligação")
                .withButtonText(android.R.string.ok)
                .withIcon(R.mipmap.ic_contact)
                .build()


        Dexter.withActivity(this@OrderListActivity)
                .withPermission(android.Manifest.permission.CALL_PHONE)
                .withListener(dialogPermissionListener)
                .onSameThread()
                .check();


        viewManager = LinearLayoutManager(this@OrderListActivity)

        listOrderService = OrderServiceRepository(this).getAllOrderServices()

        if (listOrderService!!.size > 0) {
            viewAdapter = OrderAdapter(this, listOrderService)

            recyclerView = findViewById<RecyclerView>(R.id.rv_order_services).apply {
                setHasFixedSize(true)
                layoutManager = viewManager
                adapter = viewAdapter
            }

            recyclerView.setAdapter(viewAdapter);
            recyclerView.setLayoutManager(viewManager);


        }

    }

    override fun getContentViewId(): Int {
        return R.layout.activity_order_list
    }

    override fun getNavigationMenuItemId(): Int {
        return R.id.navigation_home
    }

}

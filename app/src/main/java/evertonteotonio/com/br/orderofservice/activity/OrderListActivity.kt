package evertonteotonio.com.br.orderofservice.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.adapter.OrderAdapter
import evertonteotonio.com.br.orderofservice.model.OrderService
import evertonteotonio.com.br.orderofservice.repository.OrderServiceRepository



class OrderListActivity : MenuActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: OrderAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var listOrderService: ArrayList<OrderService>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

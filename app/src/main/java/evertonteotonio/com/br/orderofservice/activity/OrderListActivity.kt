package evertonteotonio.com.br.orderofservice.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import evertonteotonio.com.br.orderofservice.R
import evertonteotonio.com.br.orderofservice.repository.OrderServiceRepository
import kotlinx.android.synthetic.main.activity_order_list.*

class OrderListActivity : MenuActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val OrderServices = OrderServiceRepository(this).getAllOrderServices()

    }

    override fun getContentViewId(): Int {
        return R.layout.activity_order_list
    }

    override
    fun getNavigationMenuItemId(): Int {
        return R.id.navigation_home
    }

}

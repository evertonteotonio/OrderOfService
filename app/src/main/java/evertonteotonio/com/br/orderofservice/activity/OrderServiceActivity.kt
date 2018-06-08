package evertonteotonio.com.br.orderofservice.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import evertonteotonio.com.br.orderofservice.R

class OrderServiceActivity : MenuActivity() {

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_order_service)
//    }

    override fun getContentViewId(): Int {
        return R.layout.activity_order_service
    }

    override
    fun getNavigationMenuItemId(): Int {
        return R.id.navigation_new_service
    }

}

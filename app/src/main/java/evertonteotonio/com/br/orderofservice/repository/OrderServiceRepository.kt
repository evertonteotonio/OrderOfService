package evertonteotonio.com.br.orderofservice.repository

import android.content.Context
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.Order
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

class OrderServiceRepository(val context: Context)
{

    companion object {
        var totalList: Int? = null
        var orderServiceList: List<Order>? = null
    }

    fun getAllOrderServices(): List<Order>? {
        context.database.use{
//            select(Order.TABLE_NAME).exec {
//                OrderServiceRepository.orderServiceList = parseList(classParser<Order>())
//                OrderServiceRepository.totalList = this.count
//            }

            OrderServiceRepository.orderServiceList =  select(Order.TABLE_NAME).parseList(classParser<Order>())
        }
        return OrderServiceRepository.orderServiceList
    }

    fun create(userId: String, clientId: String,
               taskId: String, addressId: String, type: String, status: Int = 0) : Boolean
    {
        var totalList = context.database.use {
            insert(Order.TABLE_NAME,
                    Order.COLUMN_UUID to UUID.randomUUID().toString(),
                    Order.COLUMN_USER_ID to userId,
                    Order.COLUMN_CLIENT_ID to clientId,
                    Order.COLUMN_TASK_ID to taskId,
                    Order.COLUMN_ADDRESS_ID to addressId,
                    Order.COLUMN_STATUS to status,
                    Order.COLUMN_TYPESERVICEID to type,
                    Order.COLUMN_CREATED_AT to Date().toString(),
                    Order.COLUMN_UPDATED_AT to Date().toString()
            )
        }
        if (totalList == -1L){
            return false
        }
        return true
    }


}
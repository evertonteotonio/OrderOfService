package evertonteotonio.com.br.orderofservice.repository

import android.content.Context
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.Order
import evertonteotonio.com.br.orderofservice.model.OrderService
import evertonteotonio.com.br.orderofservice.model.User
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.util.*

class OrderServiceRepository(val context: Context)
{

    val listOrderService = arrayListOf<OrderService>()

    companion object {
        var totalList: Int? = null
        var orderServiceList: List<Order>? = null
    }


    fun getAllOrder(): List<Order>? {
        context.database.use{
            OrderServiceRepository.orderServiceList =  select(Order.TABLE_NAME)
                    .parseList(classParser<Order>())
        }
        return OrderServiceRepository.orderServiceList
    }

    fun getAllOrderServices(): ArrayList<OrderService> {

        val orderServices = OrderServiceRepository(context).getAllOrder()

        if (orderServices != null) {
            for(order in orderServices){

                val client = ClientRepository(context).findById(order.clientId)
                val address = AddressRepository(context).findById(order.addressId)
                val task = TaskRepository(context).findById(order.taskId)

                listOrderService.add(OrderService(order, client!![0], address!![0], task!![0]))

            }
        }
        return listOrderService
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



    fun getOrderById(uuid: String): List<Order>? {
        context.database.use{
            OrderServiceRepository.orderServiceList =  select(Order.TABLE_NAME)
                    .whereArgs(User.COLUMN_UUID + " = {uuid}", "uuid" to uuid)
                    .parseList(classParser<Order>())
        }
        return OrderServiceRepository.orderServiceList
    }


    fun getOrderServicesById(uuid: String): OrderService? {

        val orderServices = OrderServiceRepository(context).getOrderById(uuid)

        var service: OrderService? = null

        if (orderServices != null) {
            for(order in orderServices){

                val client = ClientRepository(context).findById(order.clientId)
                val address = AddressRepository(context).findById(order.addressId)
                val task = TaskRepository(context).findById(order.taskId)

                service = OrderService(order, client!![0], address!![0], task!![0])

            }
        }
        return service
    }



}
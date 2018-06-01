package evertonteotonio.com.br.orderofservice.model

import evertonteotonio.com.br.orderofservice.database.helper.DatabaseHelper
import java.util.*

data class Order(val uuid: String = UUID.randomUUID().toString(), val typeServiceId: String,
                 val userId: Int, val addressId: Int, val createdAt: String, val updatedAt: String?) {

    companion object {
        val TABLE_NAME = "Order"
        val COLUMN_UUID = "uuid"
        val COLUMN_TYPESERVICEID = "typeServiceId"
        val COLUMN_USERID = "userId"
        val COLUMN_ADDRESS_ID = "addressId"
        val COLUMN_CREATED_AT = "createdAt"
        val COLUMN_UPDATED_AT = "updatedAt"
    }

}
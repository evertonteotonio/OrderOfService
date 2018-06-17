package evertonteotonio.com.br.orderofservice.model

data class Order(val uuid: String,
                 val userId: String,
                 val clientId: String,
                 val taskId: String,
                 val addressId: String,
                 val status: String?,
                 val typeServiceId: String,
                 val createdAt: String,
                 val updatedAt: String?) {

    companion object {
        val TABLE_NAME = "OrderService"
        val COLUMN_UUID = "uuid"
        val COLUMN_USER_ID = "userId"
        val COLUMN_CLIENT_ID = "clientId"
        val COLUMN_TASK_ID = "taskId"
        val COLUMN_ADDRESS_ID = "addressId"
        val COLUMN_STATUS = "status"
        val COLUMN_TYPESERVICEID = "typeServiceId"
        val COLUMN_CREATED_AT = "createdAt"
        val COLUMN_UPDATED_AT = "updatedAt"
    }

}
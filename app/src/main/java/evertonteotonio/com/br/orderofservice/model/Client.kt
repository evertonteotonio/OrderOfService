package evertonteotonio.com.br.orderofservice.model

import java.util.*

data class Client(val uuid: String = UUID.randomUUID().toString(), val name: String, val email: String, val phone: String,
                  val cellPhone: String, val createdAt: String, val UpdatedAt: String?)
{
    companion object {
        val TABLE_NAME = "Client"
        val COLUMN_UUID = "uuid"
        val COLUMN_NAME= "name"
        val COLUMN_EMAIL = "email"
        val COLUMN_PHONE = "phone"
        val COLUMN_CELLPHONE = "cellPhone"
        val COLUMN_CREATED_AT = "createdAt"
        val COLUMN_UPDATED_AT = "updatedAt"
    }
}
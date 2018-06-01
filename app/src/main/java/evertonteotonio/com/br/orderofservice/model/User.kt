package evertonteotonio.com.br.orderofservice.model

import java.util.*

data class User(val uuid: String = UUID.randomUUID().toString(), val name: String, val email: String, val password: String,
                val createdAt: String, val updatedAt: String?)
{
    companion object {
        val TABLE_NAME = "User"
        val COLUMN_UUID = "uuid"
        val COLUMN_NAME = "name"
        val COLUMN_EMAIL = "email"
        val COLUMN_PASSWORD = "password"
        val COLUMN_CREATED_AT = "createdAt"
        val COLUMN_UPDATED_AT = "updatedAt"
    }
}
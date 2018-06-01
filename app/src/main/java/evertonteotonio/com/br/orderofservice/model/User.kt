package evertonteotonio.com.br.orderofservice.model

import java.util.*

data class User(val id: Int?, val name: String, val email: String, val password: String,
                val createdAt: String, val updatedAt: String?)
{
    companion object {
        val TABLE_NAME = "User"
        val COLUMN_ID = "id"
        val COLUMN_NAME = "name"
        val COLUMN_EMAIL = "email"
        val COLUMN_PASSWORD = "password"
        val COLUMN_CREATED_AT = "createdAt"
        val COLUMN_UPDATED_AT = "updatedAt"
    }
}
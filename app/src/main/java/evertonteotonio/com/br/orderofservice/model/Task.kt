package evertonteotonio.com.br.orderofservice.model

import java.util.*

data class Task(val uuid: String, val description: String?,
                val status: String?, val created_at: String?, val updated_at: String?)
{
    companion object {
        val TABLE_NAME = "Task"
        val COLUMN_UUID = "uuid"
        val COLUMN_DESCRIPTION = "description"
        val COLUMN_STATUS = "status"
        val COLUMN_CREATED_AT = "created_at"
        val COLUMN_UPDATED_AT = "updated_at"
    }
}
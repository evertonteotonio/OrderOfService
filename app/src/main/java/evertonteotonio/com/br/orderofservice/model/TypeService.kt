package evertonteotonio.com.br.orderofservice.model

import java.util.*

data class TypeService(val uuid: String = UUID.randomUUID().toString(), val title: String,
                       val description: String, val status: Int,val createdAt: Date)
{

    companion object {
        val TABLE_NAME = "TypeService"
        val COLUMN_UUID = "uuid"
        val COLUMN_TITLE = "title"
        val COLUMN_DESCRIPTION = "description"
        val COLUMN_STATUS = "status"
        val COLUMN_CREATED_AT = "createdAt"
    }

}
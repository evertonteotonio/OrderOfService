package evertonteotonio.com.br.orderofservice.repository

import android.content.Context
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.Client
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import java.util.*

class ClientRepository(val context: Context)
{
    companion object {
        var totalList: Int? = null
        var clientList: List<Client>? = null
    }

    fun create(uuid: String, name: String,
               email: String, cellPhone: String, phone: String) : Boolean{

        var totalList = context.database.use {
            insert(Client.TABLE_NAME,
                    Client.COLUMN_UUID to uuid,
                    Client.COLUMN_NAME to name,
                    Client.COLUMN_EMAIL to email,
                    Client.COLUMN_CELLPHONE to cellPhone,
                    Client.COLUMN_PHONE to phone,
                    Client.COLUMN_CREATED_AT to Date().toString()
            )
        }
        if (totalList == -1L){
            return false
        }
        return true
    }

    fun findById(uuid: String) : List<Client>?
    {
        context.database.use{
            clientList = select(Client.TABLE_NAME)
                    .whereArgs(Client.COLUMN_UUID + " = {uuid}", "uuid" to uuid)
                    .limit(1).parseList(classParser<Client>())

        }
        return clientList
    }

    fun update(uuid: String, name: String,
               email: String, cellPhone: String, phone: String) : Boolean{

        var totalList = context.database.use {
            update(Client.TABLE_NAME,
                    Client.COLUMN_NAME to name,
                    Client.COLUMN_EMAIL to email,
                    Client.COLUMN_CELLPHONE to cellPhone,
                    Client.COLUMN_PHONE to phone,
                    Client.COLUMN_UPDATED_AT to Date().toString()
            ).whereArgs(Client.COLUMN_UUID + " = {uuid}", "uuid" to uuid).exec()
        }
        if (totalList == -1){
            return false
        }
        return true
    }

}
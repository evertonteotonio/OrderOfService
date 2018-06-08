package evertonteotonio.com.br.orderofservice.repository

import android.content.Context
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.Client
import org.jetbrains.anko.db.insert
import java.util.*

class ClientRepository(val context: Context)
{
    companion object {
        var totalList: Int? = null
        var usersList: List<Client>? = null
    }

    fun create(uuid: String, name: String, email: String, cellPhone: String, phone: String){

        context.database.use {
            insert(Client.TABLE_NAME,
                    Client.COLUMN_UUID to uuid,
                    Client.COLUMN_NAME to name,
                    Client.COLUMN_EMAIL to email,
                    Client.COLUMN_CELLPHONE to cellPhone,
                    Client.COLUMN_PHONE to phone,
                    Client.COLUMN_CREATED_AT to Date().toString()
            )
        }
    }


}
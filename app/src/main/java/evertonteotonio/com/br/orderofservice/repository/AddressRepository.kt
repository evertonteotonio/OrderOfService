package evertonteotonio.com.br.orderofservice.repository

import android.content.Context
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.Address
import evertonteotonio.com.br.orderofservice.model.Client
import org.jetbrains.anko.db.insert
import java.util.*

class AddressRepository(val context: Context)
{

    companion object {
        var totalList: Int? = null
        var addressList: List<Address>? = null
    }

    fun create(uuid: UUID, cep: String, address: String, number: String,
               district: String, city: String, uf: String): Boolean
    {

        var totalList = context.database.use {
            insert(Address.TABLE_NAME,
                    Address.COLUMN_UUID to uuid,
                    Address.COLUMN_CEP to cep,
                    Address.COLUMN_ADDRESS to address,
                    Address.COLUMN_NUMBER to number,
                    Address.COLUMN_DISTRICT to district,
                    Address.COLUMN_CITY to city,
                    Address.COLUMN_UF to uf
            )
        }
        if (totalList == -1L){
            return false
        }
        return true
    }

}
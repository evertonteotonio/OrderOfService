package evertonteotonio.com.br.orderofservice.repository

import android.content.Context
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.Address
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update

class AddressRepository(val context: Context)
{
    companion object {
        var totalList: Int? = null
        var addressList: List<Address>? = null
    }

    fun create(uuid: String, cep: String, address: String, number: String,
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


    fun update(uuid: String, cep: String, address: String, number: String,
               district: String, city: String, uf: String): Boolean
    {

        var totalList = context.database.use {

            update(Address.TABLE_NAME,
                Address.COLUMN_CEP to cep,
                    Address.COLUMN_ADDRESS to address,
                    Address.COLUMN_NUMBER to number,
                    Address.COLUMN_DISTRICT to district,
                    Address.COLUMN_CITY to city,
                    Address.COLUMN_UF to uf)
                    .whereArgs(Address.COLUMN_UUID + " = {uuid}", "uuid" to uuid)
                    .exec()

        }
        if (totalList == -1){
            return false
        }
        return true
    }



    fun findById(uuid: String) : List<Address>?
    {
        context.database.use{
            addressList = select(Address.TABLE_NAME)
                    .whereArgs(Address.COLUMN_UUID + " = {uuid}", "uuid" to uuid)
                    .limit(1).parseList(classParser<Address>())

        }
        return addressList
    }


}
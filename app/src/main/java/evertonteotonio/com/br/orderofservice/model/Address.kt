package evertonteotonio.com.br.orderofservice.model

import java.util.*

data class Address(val uuid: String = UUID.randomUUID().toString(), val cep: String, val address: String, val number: Int,
                   val publicPlace: String, val complement: String, val district: String,
                   val city: String, val uf: String, val unity: String?, val ibge: String?,
                   val gia: String?)
{
    companion object {
        val TABLE_NAME = "Address"
        val COLUMN_UUID = "id"
        val COLUMN_CEP = "cep"
        val COLUMN_ADDRESS = "address"
        val COLUMN_NUMBER = "number"
        val COLUMN_PUBLIC_PLACE = "publicPlace"
        val COLUMN_COMPLEMENT = "complement"
        val COLUMN_DISTRICT = "district"
        val COLUMN_CITY = "city"
        val COLUMN_UF = "uf"
        val COLUMN_UNITY = "unity"
        val COLUMN_IBGE = "ibge"
        val COLUMN_GIA = "gia"
    }
}
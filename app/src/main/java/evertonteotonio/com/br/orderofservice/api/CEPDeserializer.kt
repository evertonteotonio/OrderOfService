package evertonteotonio.com.br.orderofservice.api

import android.location.Address
import com.google.gson.*
import java.lang.reflect.Type

class CEPDeserializer : JsonDeserializer<Address> {

    @Throws(JsonParseException::class)
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): Address {

        var element: JsonElement = json.asJsonObject

        if (json.asJsonObject != null) {
            element = json.asJsonObject
        }

        return Gson().fromJson<Address>(element, Address::class.java!!)
    }
}
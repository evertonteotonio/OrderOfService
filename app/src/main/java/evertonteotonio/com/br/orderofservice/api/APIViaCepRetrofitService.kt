package evertonteotonio.com.br.orderofservice.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface APIViaCepRetrofitService{

    companion object {
        val BASE_URL: String = "https://viacep.com.br/ws/";
    }

    @GET("{estado}/{cidade}/{endereco}/json/")
    fun getCEPByCidadeEstadoEndereco(@Path("estado") estado: String,
                                     @Path("cidade") cidade: String,
                                     @Path("endereco") endereco: String): Call<List<CEP>>


    /* Retorna apenas um objeto CEP */
    @GET("{CEP}/json/")
    fun getEnderecoByCEP(@Path("CEP") CEP: String): Call<CEP>


}
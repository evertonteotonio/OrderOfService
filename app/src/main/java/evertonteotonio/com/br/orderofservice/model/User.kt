package evertonteotonio.com.br.orderofservice.model


data class User(val id: Int, val name: String, val email: String, val password: String)
{
    companion object {
        val TABLE_NAME = "User"
        val COLUMN_ID = "id"
        val COLUMN_NAME = "name"
        val COLUMN_EMAIL = "email"
        val COLUMN_PASSWORD = "password"
        var UsersList: List<User>? = null
        var TotalList: Int? = null
    }
}
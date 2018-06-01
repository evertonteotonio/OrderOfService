package evertonteotonio.com.br.orderofservice.repository

import android.content.Context
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.TypeService
import evertonteotonio.com.br.orderofservice.model.User
import org.jetbrains.anko.db.*
import java.util.*

class UserRepository(val context: Context)
{

    companion object {
        var totalList: Int? = null
        var usersList: List<User>? = null
    }

    fun findById(id: Int) : List<User>?
    {
        context.database.use{

            select(User.TABLE_NAME)
                    .whereArgs(User.COLUMN_ID + " = {id}", "id" to id)
                    .exec{
                        usersList = parseList(classParser<User>())
                        totalList = this.count
                    }

        }
        return usersList
    }

    fun findByEmail(email: String) : List<User>?
    {
        var teste = context.database.use{

            select(User.TABLE_NAME)
                    .whereArgs(User.COLUMN_EMAIL + " = {email}", "email" to email)
                    .exec{
                        usersList = parseList(classParser<User>())
                        totalList = this.count
                        return@exec

                    }

        }
        return usersList
    }

    fun create(name: String, email: String, password: String){

        context.database.use {
            insert(User.TABLE_NAME,
                    User.COLUMN_NAME to name,
                    User.COLUMN_EMAIL to email,
                    User.COLUMN_PASSWORD to password,
                    User.COLUMN_CREATED_AT to Date().toString()
            )
        }
    }

    fun update(user: User) = context.database.use {
        val updateResult = update(User.TABLE_NAME,
                User.TABLE_NAME to user.name,
                User.COLUMN_EMAIL to user.email,
                User.COLUMN_PASSWORD to user.password)
                .whereArgs(User.COLUMN_ID + " = {id}", "id" to user.id.toString().toLong())
                .exec()
    }

    fun delete(user: User) = context.database.use {
        delete(TypeService.TABLE_NAME, whereClause = "id = {userId}",
                args = "userId" to user.id.toString().toLong())
    }

}
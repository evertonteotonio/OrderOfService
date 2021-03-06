package evertonteotonio.com.br.orderofservice.repository

import android.content.Context
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.Task
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update
import java.util.*

class TaskRepository(val context: Context)
{
    companion object {
        var totalList: Int? = null
        var taskList: List<Task>? = null
    }

    fun create(uuid: String, description: String, date: String, time: String) : Boolean{

        var totalList = context.database.use {
            insert(Task.TABLE_NAME,
                    Task.COLUMN_UUID to uuid,
                    Task.COLUMN_DESCRIPTION to description,
                    Task.COLUMN_DATE to date,
                    Task.COLUMN_TIME to time,
                    Task.COLUMN_CREATED_AT to Date().toString()
            )
        }
        if (totalList == -1L){
            return false
        }
        return true
    }

    fun update(uuid: String, description: String, date: String, time: String) : Boolean{

        var totalList = context.database.use {
            update(Task.TABLE_NAME,
                    Task.COLUMN_DESCRIPTION to description,
                    Task.COLUMN_DATE to date,
                    Task.COLUMN_TIME to time,
                    Task.COLUMN_CREATED_AT to Date().toString()
            ).whereArgs(Task.COLUMN_UUID + " = {uuid}", "uuid" to uuid).exec()
        }
        if (totalList == -1){
            return false
        }
        return true
    }



    fun findById(uuid: String) : List<Task>?
    {
        context.database.use{
            taskList = select(Task.TABLE_NAME)
                    .whereArgs(Task.COLUMN_UUID + " = {uuid}", "uuid" to uuid)
                    .limit(1).parseList(classParser<Task>())

        }
        return taskList
    }


}
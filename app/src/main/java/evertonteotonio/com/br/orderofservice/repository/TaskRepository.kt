package evertonteotonio.com.br.orderofservice.repository

import android.content.Context
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.Task
import org.jetbrains.anko.db.insert
import java.util.*

class TaskRepository(val context: Context)
{
    companion object {
        var totalList: Int? = null
        var taskList: List<Task>? = null
    }

    fun create(uuid: String, description: String) : Boolean{

        var totalList = context.database.use {
            insert(Task.TABLE_NAME,
                    Task.COLUMN_UUID to uuid,
                    Task.COLUMN_DESCRIPTION to description,
                    Task.COLUMN_CREATED_AT to Date().toString()
            )
        }
        if (totalList == -1L){
            return false
        }
        return true
    }


}
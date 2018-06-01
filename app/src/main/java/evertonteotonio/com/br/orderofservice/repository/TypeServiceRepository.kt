package evertonteotonio.com.br.orderofservice.repository

import android.content.Context
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.TypeService
import org.jetbrains.anko.db.*

class TypeServiceRepository(val context: Context)
{

    companion object {
        var typeServiceList: List<TypeService>? = null
        var totalList: Int? = null
    }

    fun findAll() : List<TypeService>?
    {
        context.database.use{

            select(TypeService.TABLE_NAME)
                    .exec{
                        typeServiceList = parseList(classParser<TypeService>())
                        totalList = this.count

                    }

        }
        return typeServiceList
    }

    fun findById(id: Int) : List<TypeService>?
    {
        context.database.use{

            select(TypeService.TABLE_NAME)
                    .whereArgs(TypeService.COLUMN_ID + " = {id}", "id" to id)
                    .exec{
                    typeServiceList = parseList(classParser<TypeService>())
                    totalList = this.count

            }

        }
        return typeServiceList
    }

    fun create(typeService: TypeService) = context.database.use {

        insert(TypeService.TABLE_NAME,
                TypeService.COLUMN_TITLE to typeService.title,
                TypeService.COLUMN_DESCRIPTION to typeService.description,
                TypeService.COLUMN_STATUS to typeService.status,
                TypeService.COLUMN_CREATED_AT to typeService.createdAt
        )
    }

    fun delete(typeService: TypeService) = context.database.use {
        delete(TypeService.TABLE_NAME, whereClause = "id = {typeServiceId}",
                args = "typeServiceId" to typeService.id)
    }


}
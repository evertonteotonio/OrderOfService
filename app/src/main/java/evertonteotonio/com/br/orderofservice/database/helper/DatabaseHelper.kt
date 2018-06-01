package evertonteotonio.com.br.orderofservice.database.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import evertonteotonio.com.br.orderofservice.model.*
import org.jetbrains.anko.db.*

class DatabaseHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "OrderServiceDatabase", null, 1) {
    companion object {
        private var instance: DatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(ctx.getApplicationContext())
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        // Aqui você cria tabelas
        db.createTable(User.TABLE_NAME, true,
                User.COLUMN_UUID to TEXT + PRIMARY_KEY + UNIQUE,
                User.COLUMN_NAME to TEXT,
                User.COLUMN_EMAIL to TEXT,
                User.COLUMN_PASSWORD to TEXT,
                User.COLUMN_CREATED_AT to TEXT,
                User.COLUMN_UPDATED_AT to TEXT)

        db.createTable(Client.TABLE_NAME, true,
                Client.COLUMN_UUID to TEXT + PRIMARY_KEY + UNIQUE,
                Client.COLUMN_NAME to TEXT,
                Client.COLUMN_EMAIL to TEXT,
                Client.COLUMN_PHONE to TEXT,
                Client.COLUMN_CELLPHONE to TEXT,
                Client.COLUMN_CREATED_AT to TEXT,
                Client.COLUMN_UPDATED_AT to TEXT)

        db.createTable(Address.TABLE_NAME, true,
                Address.COLUMN_UUID to TEXT + PRIMARY_KEY + UNIQUE,
                Address.COLUMN_CEP to TEXT,
                Address.COLUMN_ADDRESS to TEXT,
                Address.COLUMN_NUMBER to INTEGER,
                Address.COLUMN_PUBLIC_PLACE to TEXT,
                Address.COLUMN_COMPLEMENT to TEXT,
                Address.COLUMN_DISTRICT to TEXT,
                Address.COLUMN_CITY to TEXT,
                Address.COLUMN_UF to TEXT,
                Address.COLUMN_UNITY to TEXT,
                Address.COLUMN_IBGE to TEXT,
                Address.COLUMN_GIA to TEXT)

        db.createTable(Order.TABLE_NAME, true,
                Order.COLUMN_UUID to TEXT + PRIMARY_KEY + UNIQUE,
                Order.COLUMN_TYPESERVICEID to TEXT,
                Order.COLUMN_USERID to TEXT,
                Order.COLUMN_ADDRESS_ID to TEXT,
                Order.COLUMN_CREATED_AT to TEXT,
                Order.COLUMN_UPDATED_AT to TEXT)

        db.createTable(TypeService.TABLE_NAME, true,
                TypeService.COLUMN_UUID to TEXT + PRIMARY_KEY + UNIQUE,
                TypeService.COLUMN_TITLE to TEXT,
                TypeService.COLUMN_DESCRIPTION to TEXT,
                TypeService.COLUMN_STATUS to INTEGER,
                TypeService.COLUMN_CREATED_AT to TEXT)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Aqui você pode atualizar tabelas, como de costume
        db.dropTable(User.TABLE_NAME.toString(), true)
        db.dropTable(Client.TABLE_NAME.toString(), true)
    }
}

// Propriedade de acesso para contexto
val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(getApplicationContext())
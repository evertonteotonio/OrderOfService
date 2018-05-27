package evertonteotonio.com.br.orderofservice.database.helper

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import evertonteotonio.com.br.orderofservice.model.User
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
                User.COLUMN_ID to INTEGER + PRIMARY_KEY + UNIQUE,
                User.COLUMN_NAME to TEXT,
                User.COLUMN_EMAIL to TEXT, User.COLUMN_PASSWORD to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Aqui você pode atualizar tabelas, como de costume
        db.dropTable(User.TABLE_NAME.toString(), true)
    }
}

// Propriedade de acesso para contexto
val Context.database: DatabaseHelper
    get() = DatabaseHelper.getInstance(getApplicationContext())
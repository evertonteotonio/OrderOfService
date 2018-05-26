package evertonteotonio.com.br.orderofservice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import evertonteotonio.com.br.orderofservice.controller.LoginActivityController
import evertonteotonio.com.br.orderofservice.database.helper.database
import evertonteotonio.com.br.orderofservice.model.User

import org.jetbrains.anko.db.select

class MainActivity : AppCompatActivity() {

    //Tempo que nosso splashscreen ficará visivel
    private val SPLASH_DISPLAY_LENGTH: Long = 3500

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database.use {
            select(User.TABLE_NAME, User.COLUMN_EMAIL).exec {

                for (i in 1..this.columnCount.toInt()) {
                    Log.i("COLUNA",this.getColumnName(i - 1))
                }


            }
        }

        loadLogin()
    }

    private fun loadLogin() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_main)
        anim.reset()

        //Pegando o nosso objeto criado no layout
        val iv = findViewById<View>(R.id.splash) as ImageView
        //if (iv != null) {
        iv.clearAnimation()
        iv.startAnimation(anim)
        //}


        Handler().postDelayed(Runnable {
            // Após o tempo definido irá executar a próxima tela
            val intent = Intent(this@MainActivity, LoginActivityController::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            this@MainActivity.finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}

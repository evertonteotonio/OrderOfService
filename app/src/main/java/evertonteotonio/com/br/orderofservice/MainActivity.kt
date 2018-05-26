package evertonteotonio.com.br.orderofservice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import evertonteotonio.com.br.orderofservice.controller.LoginController
import evertonteotonio.com.br.orderofservice.controller.MenuController

class MainActivity : AppCompatActivity() {

    //Tempo que nosso splashscreen ficará visivel
    private var waiting_time: Long = 3500

    private var authOrderServiceFirebase = FirebaseAuth.getInstance()

    //modificar para chamar outra activity
    private var currentUser: FirebaseUser? = authOrderServiceFirebase?.getCurrentUser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        database.use {
//            select(User.TABLE_NAME, User.COLUMN_EMAIL).exec {
//
//                for (i in 1..this.columnCount.toInt()) {
//                    Log.i("COLUNA",this.getColumnName(i - 1))
//                }
//
//
//            }
//        }


        if (currentUser != null) {
            val intent = Intent(this@MainActivity, MenuController::class.java)
            startActivity(intent)
            this@MainActivity.finish()
        }
        loadLogin()
    }

    private fun loadLogin() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_main)
        anim.reset()

        //Pegando o nosso objeto criado no layout
        val iv = findViewById<View>(R.id.splash) as ImageView
        iv.clearAnimation()
        iv.startAnimation(anim)

        Handler().postDelayed(Runnable {
            // Após o tempo definido irá executar a próxima tela
            val intent = Intent(this@MainActivity, LoginController::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            this@MainActivity.finish()
        }, waiting_time)
    }
}

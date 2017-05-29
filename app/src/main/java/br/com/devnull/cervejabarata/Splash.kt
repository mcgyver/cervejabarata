package br.com.devnull.cervejabarata

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.startActivity
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.facebook.FacebookSdk

class Splash : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 300L
    override fun onCreate(savedInstanceState: Bundle?) {
        FacebookSdk.sdkInitialize(applicationContext)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed(
                {
                    val accessToken = AccessToken.getCurrentAccessToken()
                    if(accessToken == null) {
                        startActivity<MainActivity>()
                    } else {
                        startActivity<DrawerActivity>()
                    }
                    finish()
                }, SPLASH_TIME_OUT)
    }

}

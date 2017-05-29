package br.com.devnull.cervejabarata

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import org.jetbrains.anko.startActivity

class MainActivity : Activity() {

    private var callbackManager: CallbackManager? = null
    private var info: TextView? = null
    private var loginButton: LoginButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callbackManager = CallbackManager.Factory.create()

        setContentView(R.layout.main_activity)
        info = findViewById(R.id.info) as TextView
        loginButton = findViewById(R.id.login_button) as LoginButton

        loginButton!!.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                startActivity<DrawerActivity>()
                finish()
            }

            override fun onCancel() {
                info!!.text = "Login attempt cancelled."
            }

            override fun onError(e: FacebookException) {
                info!!.text = "Login attempt failed."
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        callbackManager!!.onActivityResult(requestCode, resultCode, data)
    }
}

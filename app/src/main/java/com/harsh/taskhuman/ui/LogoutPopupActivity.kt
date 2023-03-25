package com.harsh.taskhuman.ui

import android.app.AlertDialog
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.harsh.taskhuman.R
import com.harsh.taskhuman.common.sharepref.PrefHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LogoutPopupActivity : AppCompatActivity() {

    @Inject
    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.logout_actiivty)
        val mainLayout: LinearLayout = findViewById(R.id.MainLayout)

        mainLayout.post {
            AlertDialog.Builder(this).setTitle("Logout")
                .setMessage("Your session is no longer valid. Please log in again.")
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    prefHelper.clearSharedPref()
                    /*val j = Intent(MainApplication.instance(), LoginActivity::class.java)
                    j.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                    MainApplication.instance().startActivity(j)*/
                }.setIcon(R.drawable.alert).setCancelable(false).show()
        }
    }
}
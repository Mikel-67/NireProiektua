package com.example.proiektua

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class RedirectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val uri = intent?.data
        if (uri != null && uri.toString().startsWith("miapp://callback")) {
            val code = uri.getQueryParameter("code")
            Log.d("Spotify", "Authorization code desde RedirectActivity: $code")
            // Aquí también puedes enviar el code a tu backend
        }

        finish()
    }
}

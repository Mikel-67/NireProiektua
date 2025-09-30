package com.example.proiektua

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import android.widget.Button

class MainActivity : AppCompatActivity() {

    companion object {
        private const val CLIENT_ID = "fbe179bce885430fa71ea9609a521ff3"
        private const val REDIRECT_URI = "miapp://callback"
        private const val REQUEST_CODE = 1337
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener {
            val request = AuthorizationRequest.Builder(
                CLIENT_ID,
                AuthorizationResponse.Type.CODE,
                REDIRECT_URI
            )
                .setScopes(arrayOf("user-read-email", "streaming"))
                .build()

            AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request)
        }
    }

    // Recibimos la respuesta de login
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, data)
            when (response.type) {
                AuthorizationResponse.Type.CODE -> {
                    Log.d("Spotify", "Código recibido: ${response.code}")
                    // Aquí puedes enviar el code a tu backend o intercambiarlo por token
                }
                AuthorizationResponse.Type.ERROR -> {
                    Log.e("Spotify", "Error: ${response.error}")
                }
                else -> {}
            }
        }
    }
}

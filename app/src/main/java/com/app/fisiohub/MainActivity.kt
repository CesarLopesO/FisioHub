package com.app.fisiohub

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Obter referências dos elementos do layout
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Configurar o evento do botão de login
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Validação básica dos campos
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else {
                // Verificar credenciais (pode ser substituído por autenticação real)
                if (email == "teste@fisiohub.com" && password == "123456") {
                    Toast.makeText(this, "Login bem-sucedido", Toast.LENGTH_SHORT).show()
                    // Navegar para outra tela ou funcionalidade
                } else {
                    Toast.makeText(this, "Credenciais inválidas", Toast.LENGTH_SHORT).show()
                }
            }
        }
        val registerButton = findViewById<Button>(R.id.registerButton)

        // Navegar para a tela de criação de conta
        registerButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }




    }
}

package com.app.fisiohub

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicialização dos campos e botões
        val heightEditText = findViewById<EditText>(R.id.heightEditText)
        val increaseHeightButton = findViewById<Button>(R.id.increaseHeightButton)
        val decreaseHeightButton = findViewById<Button>(R.id.decreaseHeightButton)
        val nameEditText = findViewById<EditText>(R.id.nameEditText)
        val dobEditText = findViewById<EditText>(R.id.dobEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val createAccountButton = findViewById<Button>(R.id.createAccountButton)

        // Funcionalidade do botão de criação de conta
        createAccountButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val dob = dobEditText.text.toString()
            val height = heightEditText.text.toString()
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            // Validação dos campos
            if (name.isEmpty() || dob.isEmpty() || height.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword) {
                Toast.makeText(this, "As senhas não coincidem", Toast.LENGTH_SHORT).show()
            } else {
                // Aqui você pode adicionar a lógica para salvar o novo usuário no banco de dados
                Toast.makeText(this, "Conta criada com sucesso", Toast.LENGTH_SHORT).show()
                finish() // Voltar para a tela de login
            }
        }

        // Lógica de vírgula automática para o campo de altura
        var isFormatting = false
        heightEditText.setText("0")

        heightEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.isNotEmpty() && !isFormatting) {
                    isFormatting = true  // Impede a execução recursiva do TextWatcher

                    // Remover qualquer vírgula existente para evitar erro ao formatar
                    val rawText = s.toString().replace(",", "")

                    // Verificar se temos mais de 2 caracteres e inserir a vírgula após os dois primeiros números
                    if (rawText.length > 2) {
                        val formatted = rawText.substring(0, rawText.length - 2) + "," + rawText.substring(rawText.length - 2)
                        heightEditText.setText(formatted)
                        heightEditText.setSelection(formatted.length)  // Manter o cursor no final
                    }

                    isFormatting = false  // Permite novas modificações
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })


        // Botão de aumentar a altura
        increaseHeightButton.setOnClickListener {
            var currentHeight = heightEditText.text.toString()
            if (currentHeight.isNotEmpty()) {
                var height = currentHeight.replace(",", "").toInt()
                height++
                currentHeight = height.toString()
                if (currentHeight.length > 2) {
                    currentHeight = currentHeight.substring(0, 2) + "," + currentHeight.substring(2)
                }
                heightEditText.setText(currentHeight)
                heightEditText.setSelection(currentHeight.length)
            }
        }

        // Botão de diminuir a altura
        decreaseHeightButton.setOnClickListener {
            var currentHeight = heightEditText.text.toString()
            if (currentHeight.isNotEmpty()) {
                var height = currentHeight.replace(",", "").toInt()
                if (height > 0) {
                    height--
                    currentHeight = height.toString()
                    if (currentHeight.length > 2) {
                        currentHeight = currentHeight.substring(0, 2) + "," + currentHeight.substring(2)
                    }
                    heightEditText.setText(currentHeight)
                    heightEditText.setSelection(currentHeight.length)
                }
            }
        }

        // Abrir o DatePicker para selecionar a data de nascimento
        dobEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                    dobEditText.setText(formattedDate)
                },
                year, month, day
            )
            datePickerDialog.show()
        }
    }
}

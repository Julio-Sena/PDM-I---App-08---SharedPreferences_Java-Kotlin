package com.example.usandosharedpreferences

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var btnSalvar: Button
    private lateinit var txtNome: EditText
    private lateinit var txtResultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtNome = findViewById(R.id.editNome)
        btnSalvar = findViewById(R.id.botaoSalvar)
        txtResultado = findViewById(R.id.textResultado)
        btnSalvar.setOnClickListener {
            val preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0)
            val editor = preferences.edit()
            if (txtNome.text.toString() == "") {
                Toast.makeText(applicationContext, "Preencha seu nome:", Toast.LENGTH_LONG).show()
            } else {
                val nome = txtNome.text.toString()
                editor.putString("nome", nome)
                editor.commit()
                txtResultado.text = "Olá$nome"
            }
        }
        val preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0)
        if (preferences.contains("nome")) {
            val nome = preferences.getString("nome", "\" Olá, usuário não definido!\"")
            txtResultado.text = "Olá$nome"
        } else {
            txtResultado.text = "Olá, usuário não definido!"
        }
    }

    companion object {
        private const val ARQUIVO_PREFERENCIA = "ArquivoPreferencia"
    }
}
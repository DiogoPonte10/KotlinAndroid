package br.com.listacompras

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CadastroActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Atribui as variaveis
        val btnInserir = findViewById<Button>(R.id.btn_inserir)
        val txtProduto = findViewById<EditText>(R.id.txt_produto)

        // Define o Listener do Button
        btnInserir.setOnClickListener {
            // Atribui a variavel
            val produto = txtProduto.text.toString()

            // Verifica se preenchido e Adiciona no Adapter
            if (produto.isNotEmpty()) {
                // Limpa o campo
                txtProduto.text.clear()
            } else {
                txtProduto.error = "Preencha o produto"
            }
        }
    }
}
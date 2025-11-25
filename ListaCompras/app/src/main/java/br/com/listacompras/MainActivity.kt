package br.com.listacompras

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Atribui a variável
        val listViewProdutos = findViewById<ListView>(R.id.list_view_produtos)
        val btnInserir = findViewById<Button>(R.id.btn_inserir)
        val txtProduto = findViewById<EditText>(R.id.txt_produto)

        // Declara o Adapter
        val produtosAdapter = ArrayAdapter<String>(this,
                                                   android.R.layout.simple_list_item_1)

        // Vincula o Adapter ao ListView
        listViewProdutos.adapter = produtosAdapter

        // Define o Listener do Button
        btnInserir.setOnClickListener {
            // Atribui a variavel
            val produto = txtProduto.text.toString()

            // Adiciona no Adapter
            produtosAdapter.add(produto)
        }
    }
}
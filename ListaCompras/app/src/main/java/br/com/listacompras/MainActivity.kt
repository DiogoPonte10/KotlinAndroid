package br.com.listacompras

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
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
        val btnAdicionar = findViewById<Button>(R.id.btn_adicionar)
//        val btnInserir = findViewById<Button>(R.id.btn_inserir)
//        val txtProduto = findViewById<EditText>(R.id.txt_produto)

        // Declara o Adapter
        val produtosAdapter = ArrayAdapter<String>(this,
                                                   android.R.layout.simple_list_item_1)

        // Vincula o Adapter ao ListView
        listViewProdutos.adapter = produtosAdapter

        // Define o Listener do Button
        btnAdicionar.setOnClickListener {
            // Declara o Intent
            val intent = Intent(this,CadastroActivity::class.java)
            // Inicia a Activity
            startActivity(intent)
        }

//        // Define o Listener do Button
//        btnInserir.setOnClickListener {
//            // Atribui a variavel
//            val produto = txtProduto.text.toString()
//
//            // Verifica se preenchido e Adiciona no Adapter
//            if (produto.isNotEmpty()) {
//                produtosAdapter.add(produto)
//                // Limpa o campo
//                txtProduto.text.clear()
//            } else {
//                txtProduto.error = "Preencha o produto"
//            }
//        }

        // Define o Listener para a ListView
        listViewProdutos.setOnItemLongClickListener {
            adapterView: AdapterView<*>, view: View, position: Int, id: Long ->
            // Atribui a variável
            val item = produtosAdapter.getItem(position)
            // Remove o item
            produtosAdapter.remove(item)
            // Retorno do Click
            true
        }
    }
}
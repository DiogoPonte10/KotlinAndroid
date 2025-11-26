package br.com.listacompras

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale


class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Atribui a variável
        val listViewProdutos = findViewById<ListView>(R.id.list_view_produtos)
        val btnAdicionar     = findViewById<Button>(R.id.btn_adicionar)
        val txtTotal = findViewById<TextView>(R.id.txt_total)

        // Formata e Atribui o total
        val f = NumberFormat.getCurrencyInstance(Locale("pt",
                                                                  "BR"))
        txtTotal.text = getString(R.string.total,
            f.format(0))

        // Declara o Adapter
        val produtosAdapter = ProdutoAdapter(this)

        // Vincula o Adapter ao ListView
        listViewProdutos.adapter = produtosAdapter

        // Define o Listener do Button
        btnAdicionar.setOnClickListener {
            // Declara o Intent
            val intent = Intent(this,CadastroActivity::class.java)
            // Inicia a Activity
            startActivity(intent)
        }

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

    override fun onResume() {
        super.onResume()

        // Cria e Atribui as variaveis
        val listViewProdutos = findViewById<ListView>(R.id.list_view_produtos)
        val adapter = listViewProdutos.adapter as ProdutoAdapter
        val txtTotal = findViewById<TextView>(R.id.txt_total)

        // Limpa a lista e Reinclui
        adapter.clear()
        adapter.addAll(produtosGlobal)

        // Calcula o resultado e atribui a formatacao
        val resultado = produtosGlobal.sumOf { it.valor * it.quantidade }
        val f = NumberFormat.getCurrencyInstance(Locale("pt",
                                                                  "BR"))

        // Formata e Atribui o total
        txtTotal.text = getString(R.string.total,
                                  f.format(resultado))
    }
}
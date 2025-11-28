package br.com.calculadorabitcoin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Atribui o componente
        val btnCalcular = findViewById<Button>(R.id.btn_calcular)
        val txtQtdBitcoin = findViewById<TextView>(R.id.txt_qtd_bitcoins)

        // Alimenta o campo
        txtQtdBitcoin.text = "%.8f".format(0.0)

        // Busca a cotacao do Bitcoin
        buscarCotacao()

        // Define o Listener do Button
        btnCalcular.setOnClickListener {
            calcular()
        }
    }

    fun buscarCotacao() {
        // Atribui o componente
        val txtCotacao = findViewById<TextView>(R.id.txt_cotacao)

        // Acessa e Mostra o resultado
        TarefaAssincrona(txtCotacao).execute(API_URL)
    }

    fun calcular() {
        // Atribui os componentes
        val txtValor      = findViewById<EditText>(R.id.txt_valor)
        val txtCotacao    = findViewById<TextView>(R.id.txt_cotacao)
        val txtQtdBitcoin = findViewById<TextView>(R.id.txt_qtd_bitcoins)

        // Cria a formatação
        val f = NumberFormat.getCurrencyInstance(Locale("pt",
                                                                  "BR"))

        // Verifica se informado valor
        if (txtValor.text.isEmpty()) {
            // Se não, retorna solicitando a indicação
            txtValor.error = getString(R.string.campo_vazio)
            return
        } else {
            // Guarda o valor indicado e a cotação
            val valorIndicado = txtValor.text.toString().toDouble()

            // Faz o calculo
            val qtdBitcoin = if (cotacaoBitcoin > 0) valorIndicado / cotacaoBitcoin else 0.0

            // Atribui o resultado
            txtQtdBitcoin.text = "%.8f".format(qtdBitcoin)
        }
    }
}
package br.com.calculadorabitcoin

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import org.json.JSONObject
import java.net.URL
import java.text.NumberFormat
import java.util.Locale

class TarefaAssincrona(private val textViewResult: TextView): AsyncTask<String, Void, String>() {

    override fun onPreExecute() {
        super.onPreExecute()
        val f = NumberFormat.getCurrencyInstance(Locale("pt",
            "BR"))

        // Alimenta o campo
        textViewResult.text = buildString {
            append(" ")
            append(f.format(0.0))
        }
    }

    override fun doInBackground(vararg params: String?): String? {
        // Atribui as variaveis
        val urlString = params[0]
        var retornoUrl: String

        // Atribui o retorno
        try {
            retornoUrl = URL(urlString).readText()
        } catch (e: Exception) {
            Log.e(TAG, "Erro na requisição com readText(): ${e.message}", e)
            retornoUrl = ""
        }
        return retornoUrl
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        // Cria e atribui o retorno
        if (result != null && result.isNotEmpty()) {
            // Atribui a cotação
            cotacaoBitcoin = JSONObject(result).getJSONObject("ticker").getDouble("last")
            // Atribui a formatação
            val f = NumberFormat.getCurrencyInstance(Locale("pt",
                                                                      "BR"))
            // Atribui o retorno
            textViewResult.text = buildString {
                append(" ")
                append(f.format(cotacaoBitcoin))
            }
        }
    }
}
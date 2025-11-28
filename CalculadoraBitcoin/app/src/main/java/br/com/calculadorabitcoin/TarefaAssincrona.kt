package br.com.calculadorabitcoin

import android.os.AsyncTask
import android.util.Log
import android.widget.TextView
import org.json.JSONObject
import java.net.URL
import java.text.Format
import java.text.NumberFormat
import java.util.Locale

class TarefaAssincrona(private val textViewResult: TextView): AsyncTask<String, Void, String>() {

    override fun doInBackground(vararg params: String?): String? {
        val urlString = params[0]

        return try {
            URL(urlString).readText()
        } catch (e: Exception) {
            Log.e(TAG, "Erro na requisição com readText(): ", e)
            "Falha na conexão: ${e.message}"
        }
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)

        // Cria e atribui o retorno
        cotacaoBitcoin = JSONObject(result).getJSONObject("ticker").getDouble("last")
        val f          = NumberFormat.getCurrencyInstance(Locale("pt",
                                                                           "BR"))

        textViewResult.text = buildString {
            append(" ")
            append(f.format(cotacaoBitcoin))
        }
    }
}
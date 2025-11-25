package br.com.calculoaposentadoria

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class MainActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Definindo o layout
        setContentView(R.layout.activity_main)

        // Vinculando componentes visuais as variáveis
        val spn_sexo = findViewById<Spinner>(R.id.spn_sexo)
        val txt_idade = findViewById<EditText>(R.id.txt_idade)
        val btn_calcular = findViewById<Button>(R.id.btn_calcular)
        val txt_resultado = findViewById<TextView>(R.id.txt_resultado)

        // Definindo conteudo do Spinner
        spn_sexo.adapter = ArrayAdapter<String>(this,
                                                android.R.layout.simple_spinner_dropdown_item,
                                                listOf("masculino", "feminino"))

        // Definindo listener do Button
        btn_calcular.setOnClickListener {
            // Reserva nas variáveis
            var resultado = 0
            val sexo = spn_sexo.selectedItem as String
            val idade = txt_idade.text.toString().toInt()

            // Faz o calculo de acordo com o sexo
            if (sexo == "masculino") {
                resultado = 65 - idade
            } else {
                resultado = 60 - idade
            }

            // Mostra o resultado
            txt_resultado.text = "Faltam $resultado anos para você se aposentar"
        }
    }
}
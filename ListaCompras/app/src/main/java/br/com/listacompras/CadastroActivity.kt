package br.com.listacompras

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class CadastroActivity: AppCompatActivity() {

    val COD_IMAGE = 1
    var imageBitMap: Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        // Atribui as variaveis
        val btnInserir = findViewById<Button>(R.id.btn_inserir)
        val txtProduto = findViewById<EditText>(R.id.txt_produto)
        val txtQtd     = findViewById<EditText>(R.id.txt_qtd)
        val txtValor   = findViewById<EditText>(R.id.txt_valor)
        val imgProduto = findViewById<ImageView>(R.id.img_foto_produto)

        // Define o Listener do Button Inserir
        btnInserir.setOnClickListener {
            // Atribui os campos preenchidos
            val produto = txtProduto.text.toString()
            val qtd     = txtQtd.text.toString()
            val valor   = txtValor.text.toString()

            // Verifica se preenchidos e Reserva as informações
            if (produto.isNotEmpty() && qtd.isNotEmpty() && valor.isNotEmpty()) {
                // Cria o produto
                val prod = Produto(produto,
                                   qtd.toInt(),
                                   valor.toDouble(),
                                   imageBitMap)

                // Atribui a variável global
                produtosGlobal.add(prod)

                // Limpa os campos
                txtProduto.text.clear()
                txtQtd.text.clear()
                txtValor.text.clear()
            } else {
                // Indica o campo vázio
                txtProduto.error = if (produto.isEmpty()) getString(R.string.error_campo, "Descrição") else null
                txtQtd.error     = if (qtd.isEmpty()) getString(R.string.error_campo, "Quantidade") else null
                txtValor.error   = if (valor.isEmpty()) getString(R.string.error_campo, "Valor") else null
            }
        }

        // Define o Listener do Button Foto
        imgProduto.setOnClickListener { abrirGaleria() }
    }

    fun abrirGaleria() {

        // Cria a ação
        val intent = Intent(Intent.ACTION_GET_CONTENT)

        // Filtra por imagens
        intent.type = "image/*"

        // Inicializa a Activity
        startActivityForResult(Intent.createChooser(intent,
            "selecione uma imagem"),
            COD_IMAGE)
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, data)

        // Atribui a variável
        val imgProduto = findViewById<ImageView>(R.id.img_foto_produto)

        // Verifica o retorno
        if (requestCode == COD_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                // Lendo a URI
                val inputStream = contentResolver.openInputStream(data.data!!)
                // Atribui o retorno ao BitMap
                imageBitMap = BitmapFactory.decodeStream(inputStream)
                // Atribui a imagem no aplicativo
                imgProduto.setImageBitmap(imageBitMap)
            }
        }
    }
}
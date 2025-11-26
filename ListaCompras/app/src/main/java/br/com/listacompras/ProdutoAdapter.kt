package br.com.listacompras

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.NumberFormat
import java.util.Locale

class ProdutoAdapter(contexto: Context): ArrayAdapter<Produto>(contexto, 0) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // Inicializa a variável
        val v: View

        // Verifica se atribui a View anterior ou Atribui o Inflater
        if (convertView != null) {
            v = convertView
        } else {
            v = LayoutInflater.from(context).inflate(R.layout.list_view_item,
                                                     parent,
                                                     false)
        }

        // Atribui o item
        val item = getItem(position)

        // Atribui as variáveis
        val txtProduto = v.findViewById<TextView>(R.id.txt_item_produto)
        val txtQtd     = v.findViewById<TextView>(R.id.txt_item_qtd)
        val txtValor   = v.findViewById<TextView>(R.id.txt_item_valor)
        val imgProduto = v.findViewById<ImageView>(R.id.img_item_foto)
        val f          = NumberFormat.getCurrencyInstance(Locale("pt",
                                                                           "BR"))

        // Verifica se item não é null e Repassa as informações
        if (item != null) {
            txtProduto.text = item.descricao
            txtQtd.text     = item.quantidade.toString()
            txtValor.text   = f.format(item.valor)
            if (item.foto != null) {
                imgProduto.setImageBitmap(item.foto)
            }
        }

        return v
    }
}
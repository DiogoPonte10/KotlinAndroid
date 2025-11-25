package br.com.listacompras

import android.graphics.Bitmap

data class Produto(val descricao: String, val quantidade: Int, val valor: Double, val foto: Bitmap? = null)

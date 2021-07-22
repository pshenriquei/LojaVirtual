package com.psobrinho.lojavirtual.Fragmentos

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.psobrinho.lojavirtual.Model.Dados
import com.psobrinho.lojavirtual.databinding.ActivityCadastroProdutosBinding
import java.util.*

class CadastroProdutos : AppCompatActivity() {

    private var SelecionarUri: Uri? = null

    private lateinit var binding: ActivityCadastroProdutosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastroProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btSelecionarFoto.setOnClickListener {
            SelecionarFotoDaGaleria()
        }

        binding.btCadatrarProduto.setOnClickListener {
            SalvarDadosNoBD()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 0) {
            SelecionarUri = data?.data

            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, SelecionarUri)
            binding.fotoProduto.setImageBitmap(bitmap)
            binding.btSelecionarFoto.alpha = 0f
        }
    }

    private fun SelecionarFotoDaGaleria() {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)

    }

    private fun SalvarDadosNoBD() {
        val nomeArquivo = UUID.randomUUID().toString()
        val referencia = FirebaseStorage.getInstance().getReference(
            "/imagens/${nomeArquivo}"
        )

        SelecionarUri?.let {
            referencia.putFile(it)
                .addOnSuccessListener {
                    referencia.downloadUrl.addOnSuccessListener {
                        val url = it.toString()
                        val nome = binding.editNome.text.toString()
                        val preco = binding.editPreco.text.toString()
                        val uid = FirebaseAuth.getInstance().uid

                        val Produtos = Dados(url, nome, preco)
                        FirebaseFirestore.getInstance().collection("Produtos")
                            .add(Produtos)
                            .addOnSuccessListener {
                                Toast.makeText(this,"Produto cadastrado com sucesso!",Toast.LENGTH_SHORT)
                                    .show()

                            }.addOnFailureListener{

                            }

                    }
                }
        }

    }
}
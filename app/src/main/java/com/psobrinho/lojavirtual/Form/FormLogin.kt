package com.psobrinho.lojavirtual.Form

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.psobrinho.lojavirtual.R
import com.psobrinho.lojavirtual.TelaPrincipal
import com.psobrinho.lojavirtual.databinding.ActivityFormLoginBinding

class FormLogin : AppCompatActivity() {

    private lateinit var binding: ActivityFormLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        VerificarUsuarioLogado()

        supportActionBar!!.hide()

        binding.textTelaCadastro.setOnClickListener {
            var intent = Intent(this, FormCadastro::class.java)
            startActivity(intent)
        }

        binding.btEntrar.setOnClickListener {
            AutenticarUsario()
        }
    }

    private fun AutenticarUsario() {

        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        if (email.isEmpty() || senha.isEmpty()) {
            var snackbar = Snackbar.make(
                binding.layoutLogin,
                "Coloque um e-mail e uma senha!",
                Snackbar.LENGTH_INDEFINITE
            )
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK)
                .setAction("Ok", View.OnClickListener {

                })
            snackbar.show()
        } else {
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        binding.frameL.visibility = View.VISIBLE
                        Handler().postDelayed({ AbrirTelaPrincipal() }, 3000)

                    }


                }.addOnFailureListener {
                var snackbar = Snackbar.make(
                    binding.layoutLogin,
                    "Erro ao logar o usuário!",
                    Snackbar.LENGTH_INDEFINITE
                )
                    .setBackgroundTint(Color.WHITE)
                    .setTextColor(Color.BLACK)
                    .setAction("Ok", View.OnClickListener {

                    })
                snackbar.show()
            }
        }

    }

    private fun VerificarUsuarioLogado(){
        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if(usuarioAtual != null){
            AbrirTelaPrincipal()
        }else{

        }
    }

    private fun AbrirTelaPrincipal() {
        var intent = Intent(this, TelaPrincipal::class.java)
        startActivity(intent)
        finish()
    }

}
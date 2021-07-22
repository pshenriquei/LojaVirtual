package com.psobrinho.lojavirtual.Form

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.psobrinho.lojavirtual.databinding.ActivityFormCadastroBinding as ActivityFormCadastroBinding1

class FormCadastro : AppCompatActivity() {

    private lateinit var binding: ActivityFormCadastroBinding1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormCadastroBinding1.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.btCadastrar.setOnClickListener {
            CadastrarUsuario()
        }

    }

    private fun CadastrarUsuario() {

        val email = binding.editEmail.text.toString()
        val senha = binding.editSenha.text.toString()

        if(email.isEmpty() || senha.isEmpty() ){

            var snackbar = Snackbar.make(binding.layoutCadastro,"Coloque seu e-mail e sua senha!",Snackbar.LENGTH_INDEFINITE)
                .setBackgroundTint(Color.WHITE)
                .setTextColor(Color.BLACK)
                .setAction("Ok", View.OnClickListener {

                })
            snackbar.show()
        }else{
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener {
                if(it.isSuccessful){

                    var snackbar = Snackbar.make(binding.layoutCadastro,"Cadastro realizado com sucesso!",Snackbar.LENGTH_INDEFINITE)
                        .setBackgroundTint(Color.WHITE)
                        .setTextColor(Color.BLACK)
                        .setAction("Ok", View.OnClickListener {
                            VoltarParaFormLogin()
                        })
                    snackbar.show()

                }
            }.addOnFailureListener{

                var snackbar = Snackbar.make(binding.layoutCadastro,"Erro ao cadastrar o usuário!",Snackbar.LENGTH_INDEFINITE)
                    .setBackgroundTint(Color.WHITE)
                    .setTextColor(Color.BLACK)
                    .setAction("Ok", View.OnClickListener {

                    })
                snackbar.show()

            }
        }

    }

    private fun VoltarParaFormLogin(){
        var intent = Intent(this,FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}
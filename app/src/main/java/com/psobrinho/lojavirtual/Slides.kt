package com.psobrinho.lojavirtual

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide
import com.psobrinho.lojavirtual.Form.FormLogin
import java.text.Normalizer

class Slides : IntroActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_slides)

        isButtonBackVisible = false
        isButtonNextVisible = false

        addSlide(
            SimpleSlide.Builder()
                .background(R.color.purple)
                .image(R.drawable.drawer)
                .backgroundDark(R.color.white)
                .title("Loja Online de Calçados")
                .description("Entre e confira as promoções que preparamos para voce!")
                .build()

        )

        addSlide(
            SimpleSlide.Builder()
                .background(R.color.blue_80)
                .title("Crie uma conta Grátis")
                .canGoBackward(false)
                .description("Cadastra-se agora mesmo!E venha conhecer os nossos produtos!")
                .build()

        )
    }

    override fun onDestroy() {
        super.onDestroy()

        var intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}
package com.example.funnycalculator

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class BannedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banned)

        val closeButton: Button = findViewById(R.id.closeButton)
        closeButton.setOnClickListener {
            finishAffinity() // Encerra o app completamente
        }
    }
}
```

Este código cria um app Android com uma calculadora básica. Se o usuário tentar calcular "1 + 1", a `MainActivity` iniciará a `BannedActivity`, que exibe uma mensagem engraçada de banimento e fecha o aplicativo ao clicar no botão.
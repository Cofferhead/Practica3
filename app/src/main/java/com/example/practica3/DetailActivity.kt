package com.example.practica3

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.content.IntentCompat.getParcelableExtra
import com.example.practica3.ui.theme.Practica3Theme
import com.example.practica3.ui.theme.Shows.showDetail
import data.DataProvided
import data.Show

class DetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prueba: DataProvided = DataProvided(applicationContext)
        //val serie = intent.getParcelableExtra<Show>("SHOW")
        //val serie = getParcelableExtra(intent, "SHOW")
        var serie = intent.getSerializableExtra("SHOW") as Show
        var a = 0

        setContent {
            Practica3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //showColumnas(function = { a++ }, shows = prueba.showList )
                    //if (serie != null) {
                    /*
                    if (serie != null) {
                        Text("Segunda actividad: ${serie.name}")
                    }
                     */
                    val goToMain = {
                        println("DEVOLVER, DEVOLVER, DEVOLVER")
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                    showDetail(serie = serie,
                        goToMain, applicationContext)

                }
            }
        }
    }
}

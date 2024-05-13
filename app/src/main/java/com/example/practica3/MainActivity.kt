package com.example.practica3

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.practica3.ui.theme.Practica3Theme
import com.example.practica3.ui.theme.Shows.ShowColumnas
import data.DataProvided
import data.Show

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prueba:DataProvided = DataProvided(applicationContext)
        var a = 0
        setContent {
            Practica3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val goDetail = {show:Show ->
                        val intent = Intent(this, DetailActivity::class.java)
                        intent.putExtra("SHOW", show)
                        startActivity(intent)
                    }
                    ShowColumnas(
                        shows = prueba.showList,
                        goDetail = goDetail)
                }
            }
        }
    }

    fun goToDetailActivity (show: Show) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("SHOW", show)
        startActivity(intent)
    }

}

@Composable
fun probarData (show: Show)
{
    Column (modifier = Modifier.fillMaxSize()){
        show.name?.let { Text(text = it) }
    }
    
}

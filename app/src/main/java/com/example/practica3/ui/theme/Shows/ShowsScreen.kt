package com.example.practica3.ui.theme.Shows

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.SyncStateContract.Columns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.AsyncImage
import com.example.practica3.DetailActivity
import data.Show


/*
fun goToDetailActivity (show: Show, context : Context)
{
    val intent = Intent(context, DetailActivity::class.java)
    intent.putExtra("SHOW", show)
    context.startActivity(intent)
}
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowItem( serie: Show,
              goDetail:(serie:Show) -> Unit)
{
    Card(
        onClick = {goDetail(serie)},
        modifier = Modifier.height(400.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = RoundedCornerShape(8.dp)
    )
    {
        Column (modifier = Modifier,
             horizontalAlignment = Alignment.Start){

            Box(modifier = Modifier)
            {
                AsyncImage(
                    model = serie.image?.original,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier
                        .height(300.dp)
                        .width(250.dp)
                )
                Badge(contentColor = Color.Black,
                    containerColor = Color.Cyan,
                    modifier = Modifier
                        .padding(10.dp)
                        .height(30.dp)
                        .width(50.dp)
                ) {
                    Text(serie.rating?.average.toString(),
                        color = Color.Black,
                        fontSize = 16.sp
                        )
                }

            }
            Column (
                modifier = Modifier.height(100.dp).padding(8.dp)
            ){


                serie.name?.let {
                    Text(
                        text = it,
                        modifier = Modifier,
                        textAlign = TextAlign.Center,
                        style = TextStyle(fontSize = 20.sp)
                    )
                }
                var generos: String = ""
                for (genre in serie.genres!!) {
                    generos += genre + ", "
                }
                generos = generos.substring(0, generos.length - 2)
                Text(
                    text = generos, style = TextStyle(fontSize = 15.sp),
                    modifier = Modifier
                )
            }
        }

    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowColumnas (shows:List<Show>, goDetail:(serie:Show) -> Unit)
{
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Shows")})
        }
    ){ values ->
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.padding(values) )
        {
            items(shows)
            {
                    showActual -> ShowItem(serie = showActual, goDetail)
            }
        }

    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun showDetail (serie:Show, goBack:() -> Unit, context: Context)
{
    Scaffold (
        modifier = Modifier
            .fillMaxSize()
            .height(50.dp),
        floatingActionButton = {
            FloatingActionButton(onClick = {
                //val intent = Intent(Intent.ACTION_VIEW, Uri.parse(serie.url))
                //context.startActivity(intent)
                val sendIntent = Intent(Intent.ACTION_SEND)
                    .apply {
                        putExtra(Intent.EXTRA_TEXT, serie.url)
                        type = "text/plain"
                    }
                val shareIntent = Intent.createChooser(
                    sendIntent, null
                )
                context.startActivity(shareIntent)
            }) {
                Icon(Icons.Default.Share, contentDescription = "Add")
            }
        },
        topBar = {
               TopAppBar(
                   title = {},
                   navigationIcon = {
                        IconButton(onClick = goBack,
                            modifier = Modifier.clickable { goBack }) {
                            Icon(imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Devuelta en a main")
                        }
                   }
               )

        }
    ) {
        innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .width(450.dp),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ){

            upperDetail(serie = serie)
                descripcion(serie = serie)


        }
    }

}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun upperDetail (serie: Show)
{
    Row(modifier = Modifier.padding(10.dp)) {
        Box(modifier = Modifier.padding(horizontal = 10.dp)
            ) {
        AsyncImage(
            model = serie.image?.medium,
            contentDescription = null,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(220.dp)
                .width(175.dp)
                .clip(shape = RoundedCornerShape(10.dp))
        )
            Badge(contentColor = Color.Black,
                containerColor = Color.Cyan,
                modifier = Modifier
                    .padding(5.dp)
                    .height(40.dp)
                    .width(55.dp)
            ) {
                Text(
                    serie.rating?.average.toString(),
                    color = Color.Black,
                    fontSize = 20.sp

                )
            }
        }
        Spacer(modifier = Modifier.width(20.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .width(150.dp)
                .height(220.dp),
            horizontalAlignment = Alignment.Start
        )
        //Box(modifier = Modifier.height(220.dp).width(150.dp),)
        {
            serie.name?.let {
                Text(
                    text = it,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            var aux = serie.genres.toString()
            aux = aux.substring(1, aux.length - 1)
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                /*"Genres: ${aux}",
                modifier = Modifier,
                fontSize = 15.sp*/
                buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    ) ){
                        append("Genre: ")
                    }
                    append(aux)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                //"Premiered: ${serie.premiered}",
                //fontSize = 15.sp
                buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    ) ){
                        append("Premiered: ")
                    }
                    append(serie.premiered)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                //"Country: ${serie.network?.country?.name}",
                //fontSize = 15.sp
                buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    ) ){
                        append("Country: ")
                    }
                    append(serie.network?.country?.name)
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                //text = "Language: ${serie.language}",
                //fontSize = 15.sp
                buildAnnotatedString {
                    withStyle(style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp
                    ) ){
                        append("Language: ")
                    }
                    append(serie.language)
                }
            )
        }
    }

}
@Composable
fun descripcion (serie:Show)
{
    Column(modifier = Modifier.fillMaxWidth())
    {
        Divider(
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .width(5.dp)
        )
        Text(text = "Summary: ",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
            )
        val sumario = serie.summary?.let { limpiarSumario(it.toCharArray()) }
        serie.summary?.let {
            if (sumario != null) {
                Text(text = sumario)
            }
        }
    }
}
fun limpiarSumario (sumario:CharArray) : String
{
    var salida = ""
    var contador = 0
    for (cad:Char in sumario)
    {
        if (cad != '<'  && contador == 0 )
        {
            if (cad != '>') {
                salida += cad
            }
        }
        else
        {
            contador++
            if (contador == 3)
            {
                contador = 0
            }
        }
    }
    return salida
}
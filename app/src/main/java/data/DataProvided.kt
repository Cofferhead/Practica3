package data

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class DataProvided(contex:Context) {
    var showList: List<Show>  = emptyList()
    init {
        leer(contex)
    }
    private fun leer (contexto: Context)
    {
        val ruta : String = "shows.json"
        val contactosAux : List<Show>?

        val jsonString = contexto.assets.open(ruta).bufferedReader().use {
            it.readText()
        }
        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
        val dataListType = Types.newParameterizedType(List::class.java, Show::class.java)
        val adapter: JsonAdapter<List<Show>> = moshi.adapter(dataListType)
        contactosAux = adapter.fromJson(jsonString)
        if (contactosAux != null) {
            showList = contactosAux
        }
    }
}
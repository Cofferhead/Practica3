package data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Country(
    val code: String?,
    val name: String?,
    val timezone: String?
) : Serializable

package data

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable

data class Network(
    val country: Country?,
    val id: Int,
    val name: String?,
    val officialSite: String?
) : Serializable
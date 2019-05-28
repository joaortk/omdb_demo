package br.com.demo.omdbdemo.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val title: String? = null,
    val year: String? = null,
    val director: String? = null,
    val actors: String? = null,
    val plot: String? = null,
    val poster: String? = null,
    val imdbRating: String? = null,
    val imdbId: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(year)
        parcel.writeString(director)
        parcel.writeString(actors)
        parcel.writeString(plot)
        parcel.writeString(poster)
        parcel.writeString(imdbRating)
        parcel.writeString(imdbId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }

}
package br.com.demo.omdbdemo.domain.model

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    val title: String? = null,
    val year: String? = null,
    val rated: String? = null,
    val released: String? = null,
    val runtime: String? = null,
    val genre: String? = null,
    val director: String? = null,
    val writer: String? = null,
    val actors: String? = null,
    val plot: String? = null,
    val language: String? = null,
    val country: String? = null,
    val awards: String? = null,
    val poster: String? = null,
    val ratings: List<Rating>? = null,
    val metascore: String? = null,
    val imdbRating: String? = null,
    val imdbVotes: String? = null,
    val imdbId: String? = null,
    val type: String? = null,
    val dvd: String? = null,
    val boxOffice: String? = null,
    val production: String? = null,
    val website: String? = null,
    val response: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.createTypedArrayList(Rating),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(year)
        parcel.writeString(rated)
        parcel.writeString(released)
        parcel.writeString(runtime)
        parcel.writeString(genre)
        parcel.writeString(director)
        parcel.writeString(writer)
        parcel.writeString(actors)
        parcel.writeString(plot)
        parcel.writeString(language)
        parcel.writeString(country)
        parcel.writeString(awards)
        parcel.writeString(poster)
        parcel.writeTypedList(ratings)
        parcel.writeString(metascore)
        parcel.writeString(imdbRating)
        parcel.writeString(imdbVotes)
        parcel.writeString(imdbId)
        parcel.writeString(type)
        parcel.writeString(dvd)
        parcel.writeString(boxOffice)
        parcel.writeString(production)
        parcel.writeString(website)
        parcel.writeString(response)
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
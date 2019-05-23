package br.com.demo.omdbdemo.domain.model

import android.os.Parcel
import android.os.Parcelable
import com.google.common.truth.Truth.assertThat

object ParcelableTester {

    /**
     * This test must be run with Roboelectric
     */
    fun <T : Parcelable> assertParcelable(parcelableObject: T, creator: Parcelable.Creator<T>) {
        val parcel = Parcel.obtain()
        parcelableObject.writeToParcel(parcel, parcelableObject.describeContents())
        parcel.setDataPosition(0)

        val parceledObject = creator.createFromParcel(parcel)

        assertThat(parcelableObject).isEqualTo(parceledObject)
    }

}
package cdmp.app.data

import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import cdmp.app.domain.model.UserLocation
import java.io.IOException


class LocationService(
    private val geocoder: Geocoder,
    lastKnownLocation: Location
) : LocationListener {

    val location: UserLocation
        get() = checkNotNull(lastLocation)

    var lastLocation: UserLocation? = null

    init {
        onLocationChanged(lastKnownLocation)
    }

    override fun onLocationChanged(loc: Location) {

        lastLocation = UserLocation(
            longitude = loc.latitude,
            latitude = loc.longitude
        )

        val addresses: List<Address>
        try {
            addresses = geocoder.getFromLocation(
                loc.latitude,
                loc.longitude, 1
            )
            val cityName = when {
                addresses.isNotEmpty() -> addresses[0].locality
                else -> ""
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onProviderDisabled(provider: String) {}

    override fun onProviderEnabled(provider: String) {}

    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
}
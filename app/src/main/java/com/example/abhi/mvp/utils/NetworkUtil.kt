
import android.content.Context
import android.telephony.TelephonyManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import retrofit2.HttpException

/**
 * The type Network util.
 *
 * @author Abhishek Prajapati
 * @version 1.0.0
 * @since 12 /16/17.
 */
object NetworkUtil {

    @Suppress("DEPRECATED_IDENTITY_EQUALS")
            /**
     * Returns true if the Throwable is an instance of RetrofitError with an http status code equals
     * to the given one.
     *
     * @param throwable  the throwable
     * @param statusCode the status code
     * @return the boolean
     */
    fun isHttpStatusCode(throwable: Throwable, statusCode: Int): Boolean {
        return throwable is HttpException && throwable.code() === statusCode
    }

    /**
     * Gets network info.
     *
     * @param context context
     * @return network info
     */
    fun getNetworkInfo(context: Context): NetworkInfo? {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo
    }

    /**
     * Is connected boolean.
     *
     * @param context the context
     * @return the boolean
     */
    fun isConnected(context: Context): Boolean {
        val info = getNetworkInfo(context)
        return info != null && info.isConnected
    }

    /**
     * Is connected wifi boolean.
     *
     * @param context the context
     * @return the boolean
     */
    fun isConnectedWifi(context: Context): Boolean {
        val info = getNetworkInfo(context)
        return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * Is connected mobile boolean.
     *
     * @param context the context
     * @return the boolean
     */
    fun isConnectedMobile(context: Context): Boolean {
        val info = getNetworkInfo(context)
        return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_MOBILE
    }

    /**
     * Is connected fast boolean.
     *
     * @param context the context
     * @return the boolean
     */
    fun isConnectedFast(context: Context): Boolean {
        val info = getNetworkInfo(context)
        return info != null && info.isConnected && isConnectionFast(info.type, info.subtype)
    }

    /**
     * Is connection fast boolean.
     *
     * @param type    the type
     * @param subType the sub type
     * @return the boolean
     */
    fun isConnectionFast(type: Int, subType: Int): Boolean {
        return if (type == ConnectivityManager.TYPE_WIFI) {
            true
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            when (subType) {
                TelephonyManager.NETWORK_TYPE_1xRTT -> false // ~ 50-100 kbps
                TelephonyManager.NETWORK_TYPE_CDMA -> false // ~ 14-64 kbps
                TelephonyManager.NETWORK_TYPE_EDGE -> false // ~ 50-100 kbps
                TelephonyManager.NETWORK_TYPE_EVDO_0 -> true // ~ 400-1000 kbps
                TelephonyManager.NETWORK_TYPE_EVDO_A -> true // ~ 600-1400 kbps
                TelephonyManager.NETWORK_TYPE_GPRS -> false // ~ 100 kbps
                TelephonyManager.NETWORK_TYPE_HSDPA -> true // ~ 2-14 Mbps
                TelephonyManager.NETWORK_TYPE_HSPA -> true // ~ 700-1700 kbps
                TelephonyManager.NETWORK_TYPE_HSUPA -> true // ~ 1-23 Mbps
                TelephonyManager.NETWORK_TYPE_UMTS -> true // ~ 400-7000 kbps
            /*
 * Above API level 7, make sure to set android:targetSdkVersion
 * to appropriate level to use these
 */
                TelephonyManager.NETWORK_TYPE_EHRPD // API level 11
                -> true // ~ 1-2 Mbps
                TelephonyManager.NETWORK_TYPE_EVDO_B // API level 9
                -> true // ~ 5 Mbps
                TelephonyManager.NETWORK_TYPE_HSPAP // API level 13
                -> true // ~ 10-20 Mbps
                TelephonyManager.NETWORK_TYPE_IDEN // API level 8
                -> false // ~25 kbps
                TelephonyManager.NETWORK_TYPE_LTE // API level 11
                -> true // ~ 10+ Mbps
            // Unknown
                TelephonyManager.NETWORK_TYPE_UNKNOWN -> false
                else -> false
            }
        } else {
            false
        }
    }

}
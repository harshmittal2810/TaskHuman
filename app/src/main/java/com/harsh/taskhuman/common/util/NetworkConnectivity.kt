package com.harsh.taskhuman.common.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import com.harsh.taskhuman.libraries.logs.LocalLog
import java.net.NetworkInterface
import java.net.SocketException
import java.util.*


object NetworkConnectivity {

    const val TAG = "NetworkConnectivity"

    /**
     * Get the network info object
     * @param context
     * @return NetworkInfo
     */
    private fun getNetworkInfo(context: Context?): NetworkInfo? {
        return context?.let {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            connectivityManager?.activeNetworkInfo
        }
    }

    /**
     * To check connectivity status
     * @param context
     * @return boolean
     */
    @JvmStatic
    fun isConnectedToNetWork(context: Context?): Boolean {
        return getNetworkInfo(context)?.isConnected == true
    }

    /**
     * Check if connected to a Wifi network
     * @param context
     * @return boolean
     */
    @JvmStatic
    fun isConnectedToWifi(context: Context?): Boolean {
        val info = getNetworkInfo(context)
        return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_WIFI
    }

    /**
     * Check if connected to a mobile network
     * @param context
     * @return boolean
     */
    @JvmStatic
    fun isConnectedToMobile(context: Context?): Boolean {
        val info = getNetworkInfo(context)
        return info != null && info.isConnected && info.type == ConnectivityManager.TYPE_MOBILE
    }

    /**
     * Get device IP Address
     * @param context
     * @return String IP Address
     */
    @JvmStatic
    fun getIpAddress(context: Context?): String {
        val ipAddress = ""
        if (context == null || !isConnectedToNetWork(context)) {
            return ipAddress
        }

        if (isConnectedToWifi(context)) {
            return getWifiIpAddress(context)
        } else if (isConnectedToMobile(context)) {
            return getMobileIpAddress()
        }

        return ipAddress
    }

    /**
     * Get device wifi IP Address
     * @param context
     * @return string IP Address
     */
    private fun getWifiIpAddress(context: Context): String {
        try {
            val wifiManager =
                context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val wifiInfo = wifiManager.connectionInfo
            val ipAddressInt = wifiInfo.ipAddress
            return String.format(
                Locale.getDefault(),
                "%d.%d.%d.%d",
                ipAddressInt and 0xff,
                ipAddressInt shr 8 and 0xff,
                ipAddressInt shr 16 and 0xff,
                ipAddressInt shr 24 and 0xff
            )
        } catch (ex: Exception) {
            LocalLog.error(TAG, ex.message)
        }
        return ""
    }

    /**
     * Get device Mobile network's IP Address
     * @return string IP Address
     */
    private fun getMobileIpAddress(): String {
        var ipAddress = ""
        try {
            val en = NetworkInterface.getNetworkInterfaces()
            while (en.hasMoreElements()) {
                val intf = en.nextElement()
                val enumIpAddress = intf.inetAddresses
                while (enumIpAddress.hasMoreElements()) {
                    val inetAddress = enumIpAddress.nextElement()
                    if (!inetAddress.isLoopbackAddress) {
                        ipAddress = inetAddress.hostAddress
                    }
                }
            }
        } catch (ex: SocketException) {
            LocalLog.error(TAG, "Exception in Get IP Address: $ex")
        }
        return ipAddress
    }

}
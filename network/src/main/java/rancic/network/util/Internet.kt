package rancic.network.util

import android.app.Activity
import android.os.Handler
import android.os.Looper

class Internet(private val activity: Activity) {
    private var network: NetworkUtil
    private var wasOnceOffline = false
    private val networkListener: NetworkListener = object : NetworkListener {
        override fun onOffline() {
            updateStatusInfo(activity.getString(R.string.no_internet_connection), R.color.colorChartOrange)
            wasOnceOffline = true
        }

        override fun onOnline() {
            updateStatusInfo(activity.getString(R.string.online), R.color.colorGreenDark)
        }

        override fun onLost() {
            wasOnceOffline = true
            updateStatusInfo(activity.getString(R.string.connection_is_lost), R.color.colorChartOrange)
        }

        override fun onSwitchedToOnline() {
            if (wasOnceOffline) {
                updateStatusInfo(activity.getString(R.string.online_again), R.color.colorGreenDark)
            }
        }
    }


    init {
        network = NetworkUtil(activity, networkListener)
    }

    fun register() {
        network.register()
    }

    fun unregister() {
        network.unregister()
    }



    private fun updateStatusInfo(message: String, color: Int) {
        val mainHandler = Handler(Looper.getMainLooper())
        mainHandler.post {
            if (activity is NetworkUIChangeListener) {
                (activity as NetworkUIChangeListener).updateNetworkStatus(
                    message,
                    color
                )
            }
        }
    }
}
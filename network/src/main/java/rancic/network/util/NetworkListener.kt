package rancic.network.util

interface NetworkListener {
    fun onOffline()
    fun onOnline()
    fun onLost()
    fun onSwitchedToOnline()
}
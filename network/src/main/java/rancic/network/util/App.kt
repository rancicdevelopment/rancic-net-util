package rancic.network.util

import android.app.Application
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ProcessLifecycleOwner

class App: Application(), LifecycleEventObserver {


    override fun onCreate() {
        super.onCreate()



        ProcessLifecycleOwner.get().lifecycle.addObserver(this)

    }




    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        when (event) {
            Lifecycle.Event.ON_RESUME -> {
                Log.i("","onResume()")
            }
            Lifecycle.Event.ON_PAUSE -> {
                Log.i("","onPause()")
            }
            Lifecycle.Event.ON_DESTROY -> {
                Log.i("","onDestroy()")
            }
            else -> {}
        }
    }
}
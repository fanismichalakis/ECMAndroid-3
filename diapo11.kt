package com.example.premierprojet

import android.content.*
import android.icu.text.CaseMap
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

private const val TAG="MainActivity"

class MainActivity : AppCompatActivity() {

    var mService: BoundService? = null
    var mBound = false
    val br: BroadcastReceiver = MyBroadcastReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG,"i am in onCreate")

        button.setOnClickListener {
            val title: String = resources.getString(R.string.chooser_title)

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "This is my text to send")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, title)
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(shareIntent)
            }
        }

    override fun onStart() {
        super.onStart()
        // Bind to LocalService
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, connection, Context.BIND_AUTO_CREATE)
        Log.d("Main Activity", "onStart")
    }

    /*fun onButtonClick(v: View?) {
        if (mBound) {
            val num: Int = mService!!.getRandomNumber()
            Toast.makeText(this, "number: $num", Toast.LENGTH_SHORT).show()
        }
    }*/

    override fun onDestroy() {
        super.onDestroy()
        unbindService(connection)
        mBound = false

        Log.d("Main Activity", "onDestroy")
        unregisterReceiver(br)
    }

    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(className: ComponentName, service: IBinder?) {
            val binder: BoundService.LocalBinder = service as BoundService.LocalBinder
            mService = binder.getService()
            mBound = true
        }

        override fun onServiceDisconnected(arg0: ComponentName) {
            mBound = false
        }
    }

}

package co.novemberfive.android.skidder.sample

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import co.novemberfive.android.skidder.Skidder
import co.novemberfive.android.skidder.model.LogLevel
import co.novemberfive.android.skidder.service.LogcatLoggingService
import co.novemberfive.android.skidder.service.logDebug
import co.novemberfive.android.skidder.service.logError
import co.novemberfive.android.skidder.service.logException
import java.lang.RuntimeException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (BuildConfig.DEBUG) {
            with(Skidder) {
                addService(LogcatLoggingService(SERVICE_ID, LogLevel.DEBUG))
                environment = "UAT"
                setGlobal("global variable", "test test test")
            }
        }

        findViewById<Button>(R.id.buttonTrace).setOnClickListener {
            Skidder.log(LogLevel.TRACE, TAG, message = "Button Trace was pressed.")
        }

        findViewById<Button>(R.id.buttonDebug).setOnClickListener {
            logDebug(TAG, name = "button-click", message = "Button Debug was pressed.", data = mapOf("id" to "555", "info" to "blablabla"))
        }

        findViewById<Button>(R.id.buttonInfo).setOnClickListener {
            Skidder.log(LogLevel.INFO, TAG, message ="Button Info was pressed.")
        }

        findViewById<Button>(R.id.buttonWarn).setOnClickListener {
            Skidder.log(LogLevel.WARN, TAG, message ="Button Warn was pressed.")
        }

        findViewById<Button>(R.id.buttonError).setOnClickListener {
            logError(TAG, "Button Error was pressed.")
        }

        findViewById<Button>(R.id.buttonFatal).setOnClickListener {
            Skidder.log(LogLevel.FATAL, TAG, message ="Button Fatal was pressed.")
        }

        findViewById<Button>(R.id.buttonException).setOnClickListener {
            logException(TAG, RuntimeException("Something went wrong!"))
        }

        findViewById<Button>(R.id.buttonRemove).setOnClickListener {
            Skidder.removeService(SERVICE_ID)
        }
    }

    companion object {
        private const val TAG = "MainActivity"
        private const val SERVICE_ID  = "logcat"
    }
}

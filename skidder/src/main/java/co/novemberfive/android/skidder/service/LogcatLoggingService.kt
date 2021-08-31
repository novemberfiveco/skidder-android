package co.novemberfive.android.skidder.service

import android.util.Log
import co.novemberfive.android.skidder.model.LogBody
import co.novemberfive.android.skidder.model.LogLevel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.*

/*
 * LogcatLoggingService.java
 *
 * @author    arne@appstrakt.com
 *
 * (c) 2016 November Five BVBA
 *
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 *
 *
 * Send all logs to Logcat, formatted by [FormattedLoggingService]
 */
class LogcatLoggingService( //
    override val id: String, //
    override val level: LogLevel) : ILoggingService {

    override fun log(body: LogBody) {
        val logcatLevel = when (body.level) {
            LogLevel.TRACE -> Log.VERBOSE
            LogLevel.DEBUG -> Log.DEBUG
            LogLevel.INFO -> Log.INFO
            LogLevel.WARN -> Log.WARN
            LogLevel.ERROR -> Log.ERROR
            LogLevel.FATAL -> Log.ERROR
        }

        if (body.message.length > PRINT_STRING_LIMIT_LENGTH) {
            logLongString(logcatLevel, body.tag, body.getFormattedBody())
        } else {
            Log.println(logcatLevel, body.tag, body.getFormattedBody())
        }
    }

    private fun logLongString(logLevel: Int, tag: String?, logString: String?) {
        for (i in 0..logString!!.length / PRINT_STRING_LIMIT_LENGTH) {
            val start = i * PRINT_STRING_LIMIT_LENGTH
            var end = (i + 1) * PRINT_STRING_LIMIT_LENGTH
            end = if (end > logString.length) logString.length else end
            Log.println(logLevel, tag, logString.substring(start, end))
        }
    }

    override fun logException(body: LogBody, throwable: Throwable) {
        this.log(body)
        Log.println(Log.ERROR, body.tag, Log.getStackTraceString(throwable))
    }

    override fun setGlobal(key: String, value: String?) { /* ignore */ }

    companion object {

        private const val PRINT_STRING_LIMIT_LENGTH = 4000
    }
}

private val json = Json { prettyPrint = true }

private fun LogBody.getFormattedBody() = buildString {
    name?.let { append("$name - ") }
    append(message)
    append("\n")
    append(json.encodeToString(this@getFormattedBody))
}
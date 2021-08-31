package co.novemberfive.android.skidder.service

import co.novemberfive.android.skidder.model.LogBody
import co.novemberfive.android.skidder.model.LogLevel
import com.google.firebase.crashlytics.FirebaseCrashlytics
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/*
 * This file is part of lib_android_skidder.
 * 
 * Created by joren.vanlooveren on 22/06/2021
 * 
 * (c) 2021 November Five BVBA
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */class CrashlyticsLoggingService(
    override val id: String, //
    override val level: LogLevel) : ILoggingService {

    private val firebase: FirebaseCrashlytics by lazy { FirebaseCrashlytics.getInstance() }

    override fun log(body: LogBody) {
        firebase.log(body.getFormattedBody())
    }

    override fun logException(body: LogBody, throwable: Throwable) {
        firebase.recordException(throwable)
    }

    override fun setGlobal(key: String, value: String?) {
        firebase.setCustomKey(key, value ?: "")
    }
}

private val json = Json { prettyPrint = true }

fun LogBody.getFormattedBody() = buildString {
    name?.let { append("$name - ") }
    append(message)
    append("\n")
    append(json.encodeToString(this@getFormattedBody))
}
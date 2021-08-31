package co.novemberfive.android.skidder.service

import co.novemberfive.android.skidder.Skidder
import co.novemberfive.android.skidder.model.LogLevel

/*
 * This file is part of lib_android_skidder.
 * 
 * Created by joren.vanlooveren on 08/06/2021
 * 
 * (c) 2021 November Five BVBA
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

fun logTrace(tag: String, message: String, data: Map<String, String>? = null, name: String? = null) =
    Skidder.log(level = LogLevel.TRACE, tag = tag, name = name, message = message, data = data)

fun logDebug(tag: String, message: String, data: Map<String, String>? = null, name: String? = null) =
    Skidder.log(level = LogLevel.DEBUG, tag = tag, name = name, message = message, data = data)

fun logInfo(tag: String, message: String, data: Map<String, String>? = null, name: String? = null) =
    Skidder.log(level = LogLevel.INFO, tag = tag, name = name, message = message, data = data)

fun logWarn(tag: String, message: String, data: Map<String, String>? = null, name: String? = null) =
    Skidder.log(level = LogLevel.WARN, tag = tag, name = name, message = message, data = data)

fun logError(tag: String, message: String, data: Map<String, String>? = null, name: String? = null) =
    Skidder.log(level = LogLevel.ERROR, tag = tag, name = name, message = message, data = data)

fun logFatal(tag: String, message: String, data: Map<String, String>? = null, name: String? = null) =
    Skidder.log(level = LogLevel.FATAL, tag = tag, name = name, message = message, data = data)

fun logException(tag: String, throwable: Throwable) = Skidder.logException(tag, throwable)
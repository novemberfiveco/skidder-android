package co.novemberfive.android.skidder.service

import co.novemberfive.android.skidder.model.LogBody
import co.novemberfive.android.skidder.model.LogLevel

/*
 * This file is part of lib_android_skidder.
 * 
 * Created by joren.vanlooveren on 11/05/2021
 * 
 * (c) 2021 November Five BVBA
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */interface ILoggingService {

    val id: String

    /**
     * Minimal logging level that should be logged for this service
     */
    val level: LogLevel

    fun log(body: LogBody)

    fun logException(body: LogBody, throwable: Throwable)

    fun setGlobal(key: String, value: String?)
}

package co.novemberfive.android.skidder

import co.novemberfive.android.skidder.model.LogBody
import co.novemberfive.android.skidder.model.LogLevel
import co.novemberfive.android.skidder.service.ILoggingService

/*
 * This file is part of lib_android_skidder.
 * 
 * Created by joren.vanlooveren on 11/05/2021
 * 
 * (c) 2021 November Five BVBA
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */object Skidder {

    private val services = mutableMapOf<String, ILoggingService>()

    /**
     * predefined global variables
     */
    var environment: String? = null

    /**
     * custom global variables
     */
    private val globalVariables = mutableMapOf<String, String>()

    fun addService(service: ILoggingService) {
        synchronized(services) {
            services[service.id] = service
            globalVariables.forEach { (key, value) ->
                service.setGlobal(key, value)
            }
        }
    }

    fun removeService(id: String) {
        synchronized(services) {
            services.remove(id)
        }
    }

    fun log(
        level: LogLevel,
        tag: String,
        name: String? = null,
        message: String,
        data: Map<String, String>? = null) {
        synchronized(services) {
            val body = LogBody(
                timeStamp = System.currentTimeMillis(),
                level = level,
                tag = tag,
                name = name,
                message = message,
                environment = environment,
                data = data?.nullIfEmpty(),
                globalVariables = globalVariables.nullIfEmpty())

            services.forEach { (_, service) ->
                if (level >= service.level) {
                    service.log(body)
                }
            }
        }
    }

    fun logException(tag: String, throwable: Throwable) {
        synchronized(services) {
            services.forEach { (_, service) ->
                if (LogLevel.FATAL >= service.level) {
                    val body = LogBody(
                        timeStamp = System.currentTimeMillis(),
                        level = LogLevel.FATAL,
                        tag = tag,
                        name = throwable.javaClass.simpleName,
                        message = throwable.message ?: "",
                        environment = environment,
                        data = null,
                        globalVariables = globalVariables.nullIfEmpty())

                    service.logException(body, throwable)
                }
            }
        }
    }

    fun setGlobal(key: String, value: String?) {
        if (value != null) {
            globalVariables[key] = value
        } else {
            globalVariables.remove(key)
        }

        synchronized(services) {
            services.forEach { (_, service) ->
                service.setGlobal(key, value)
            }
        }
    }
}

private fun <T,U> Map<T, U>.nullIfEmpty() = if (this.isEmpty()) null else this
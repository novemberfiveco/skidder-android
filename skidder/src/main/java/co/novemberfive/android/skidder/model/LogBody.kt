package co.novemberfive.android.skidder.model

import kotlinx.serialization.Serializable

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
@Serializable
data class LogBody(
    val timeStamp: Long,
    val level: LogLevel,
    val tag: String,
    val name: String?,
    val message: String,
    val environment: String?,
    val data: Map<String, String>?,
    val globalVariables: Map<String, String>?)
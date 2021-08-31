package co.novemberfive.android.skidder.model

/*
 * This file is part of lib_android_skidder.
 * 
 * Created by joren.vanlooveren on 11/05/2021
 * 
 * (c) 2021 November Five BVBA
 * 
 * For the full copyright and license information, please view the LICENSE
 * file that was distributed with this source code.
 */

enum class LogLevel {

    /**
     * Very detailed debug information that is only useful in for very specific debugging/investigation.
     * Generally, we will only enable TRACE logging temporarily in case we need it to debug a specific issue.
     */
    TRACE,

    /**
     * Debug information that includes a lot of technical detail
     */
    DEBUG,

    /**
     * General information about the flow of the business logic and high-level technical operations
     */
    INFO,

    /**
     * Unhappy scenario’s/edge cases without impact that don’t warrant further investigation
     */
    WARN,

    /**
     * Individual failures/errors that impact customers or the business in general and
     * that should be investigated/resolved soon (as in next few days)
     */
    ERROR,

    /**
     * Failures/errors that impact many customers or a large part of the business/application,
     * or that will trigger many other subsequent failures. These should be investigated/resolved ASAP.
     * These are very often permanent infrastructure issues that generally are detected before go-live,
     * but they can happen in production in exceptional scenario’s (e.g. someone accidentally removes config file in production)
     */
    FATAL
}
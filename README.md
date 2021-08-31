# Skidder

A small, extensible logger library for Android.

## Usage

Requirement: Kotlin 1.4 or higher

1. Add the dependency in your *app/build.gradle*: 

   ```groovy
   implementation "co.novemberfive.skidder:skidder:$version"
   ```

   **TODO**: add Maven repository

2. add service(s) to Skidder

   ```kotlin
   if (BuildConfig.DEBUG) {
       Skidder.addService(LogcatLoggingService(id = "logcat", level = LogLevel.DEBUG))
   }
   ```

3. set global variables (optional)

   ```kotlin
   Skidder.apply {
   	environment = "UAT"
   	setGlobal("global variable", "Testy McTestface")
   }
   ```

4. start logging

   ```kotlin
   Skidder.log(LogLevel.DEBUG, TAG, message = "Button was pressed.")
   logDebug(TAG, name = "button-click", message = "Button was pressed.") //shorthand method
   
   Skidder.logException(TAG, RuntimeException("Oh ow, something went wrong!"))
   logException(TAG, RuntimeException("It broke, again..."))
   
   //optionally, add extra data
   Skidder.log(LogLevel.DEBUG, TAG, name = "button-click", message = "Button was pressed.", data = mapOf("id" to "555", "info" to "blablabla"))
   ```

## Services

The core library contains one predefined *service*: the `LogcatLoggingService`, which uses Android's `Log` class, but you can write your own services by extending `ILoggingService`.

### LogcatLoggingService

The output of the `LogcatLoggingService` will look like this:

```
co.novemberfive.android.skidder.sample D/MainActivity: button-click - Button Debug was pressed.
    {
        "timeStamp": 1624364291505,
        "level": "DEBUG",
        "tag": "MainActivity",
        "name": "button-click",
        "message": "Button was pressed.",
        "environment": "UAT",
        "data": {
            "id": "555",
            "info": "blablabla"
        },
        "globalVariables": {
            "global variable": "test test test"
        }
    }
```

### Firebase Crashlytics

   ```groovy
   implementation "co.novemberfive.skidder:crashlytics:$version"
   ```

**TODO**: further explain how to use the CrashlyticsLoggingService

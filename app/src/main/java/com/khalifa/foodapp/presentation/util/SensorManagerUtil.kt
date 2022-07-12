package com.khalifa.foodapp.presentation.util

import android.hardware.SensorManager
import android.hardware.Sensor
import android.content.Context

object SensorManagerUtil {

    private val shakeDetector: ShakeDetector by lazy { ShakeDetector() }
    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null

    fun initializeSensorEvent(context: Context, onShake: () -> Unit) {
        registerSensorAccelerometer(context, onShake)
    }

    private fun registerSensorAccelerometer(context: Context, onShake: () -> Unit) {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        shakeDetector.setOnShakeListener(object : ShakeDetector.OnShakeListener {
            override fun onShake() {
                onShake.invoke()
            }
        })
    }

    fun registerEventListener() {
        sensorManager?.registerListener(
            shakeDetector,
            accelerometer,
            SensorManager.SENSOR_DELAY_UI
        )
    }

    fun unRegisterEventListener() {
        sensorManager?.unregisterListener(shakeDetector)
    }

}
package com.khalifa.foodapp.presentation.util

import android.hardware.SensorEventListener
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorManager
import kotlin.math.sqrt

class ShakeDetector : SensorEventListener {
    private var shakeListener: OnShakeListener? = null
    private var shakeTimestamp: Long = 0

    fun setOnShakeListener(shakeListener: OnShakeListener?) {
        this.shakeListener = shakeListener
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // ignore
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (shakeListener != null) {
            val x = event.values[0]
            val y = event.values[1]
            val z = event.values[2]
            val gX = x / SensorManager.GRAVITY_EARTH
            val gY = y / SensorManager.GRAVITY_EARTH
            val gZ = z / SensorManager.GRAVITY_EARTH

            // gForce will be close to 1 when there is no movement.
            val gForce = sqrt((gX * gX + gY * gY + gZ * gZ).toDouble()).toFloat()
            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                val now = System.currentTimeMillis()
                if (shakeTimestamp + SHAKE_SLOP_TIME_MS > now) {
                    return
                }
                shakeTimestamp = now
                shakeListener?.onShake()
            }
        }
    }

    interface OnShakeListener {
        fun onShake()
    }

    companion object {
        /*
     * The gForce that is necessary to register as shake.
     * Must be greater than 1G (one earth gravity unit).
     * You can install "G-Force", by Blake La Pierre
     * from the Google Play Store and run it to see how
     *  many G's it takes to register a shake
     */
        private const val SHAKE_THRESHOLD_GRAVITY = 2f
        private const val SHAKE_SLOP_TIME_MS = 500
    }
}
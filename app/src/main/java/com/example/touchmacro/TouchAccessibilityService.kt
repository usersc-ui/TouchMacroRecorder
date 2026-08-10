package com.example.touchmacro

import android.accessibilityservice.AccessibilityService
import android.graphics.PixelFormat
import android.os.Handler
import android.os.Looper
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import android.widget.*

class TouchAccessibilityService : AccessibilityService() {

    companion object {
        var instance: TouchAccessibilityService? = null
    }

    private var overlay: LinearLayout? = null
    private var windowManager: WindowManager? = null

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }

    override fun onDestroy() {
        overlay?.let {
            windowManager?.removeView(it)
        }

        overlay = null
        instance = null

        super.onDestroy()
    }

    override fun onAccessibilityEvent(event:
        android.view.accessibility.AccessibilityEvent?) {
    }

    override fun onInterrupt() {
    }

    fun showControls() {

        if (overlay != null) return

        windowManager =
            getSystemService(Context.WINDOW_SERVICE) as WindowManager

        val box = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            setPadding(5, 5, 5, 5)
            setBackgroundColor(0xDD202020.toInt())
        }

        fun addButton(
            text: String,
            action: () -> Unit
        ) {
            box.addView(
                Button(this).apply {
                    this.text = text
                    setOnClickListener {
                        action()
                    }
                }
            )
        }

        addButton("REC") {
            Toast.makeText(
                this,
                "Aufnahme gestartet",
                Toast.LENGTH_SHORT
            ).show()
        }

        addButton("STOP") {
            Toast.makeText(
                this,
                "Aufnahme gestoppt",
                Toast.LENGTH_SHORT
            ).show()
        }

        addButton("PLAY") {
            Toast.makeText(
                this,
                "Wiedergabe",
                Toast.LENGTH_SHORT
            ).show()
        }

        addButton("CLEAR") {
            Toast.makeText(
                this,
                "Aufnahme gelöscht",
                Toast.LENGTH_SHORT
            ).show()
        }

        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        )

        params.gravity =
            Gravity.TOP or Gravity.CENTER_HORIZONTAL

        windowManager?.addView(box, params)

        overlay = box
    }
}

package com.example.touchmacro

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(40, 50, 40, 40)
        }

        root.addView(TextView(this).apply {
            text = "Touch Macro Recorder"
            textSize = 26f
        })

        root.addView(TextView(this).apply {
            text = "Record → deine Touch-Aktionen aufnehmen → später wiedergeben."
            textSize = 16f
            setPadding(0, 20, 0, 20)
        })

        root.addView(Button(this).apply {
            text = "Accessibility aktivieren"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
        })

        root.addView(Button(this).apply {
            text = "Overlay erlauben"
            setOnClickListener {
                startActivity(
                    Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    )
                )
            }
        })

        root.addView(Button(this).apply {
            text = "Recorder-Steuerung anzeigen"
            setOnClickListener {
                TouchAccessibilityService.instance?.showControls()
                    ?: Toast.makeText(
                        this@MainActivity,
                        "Bitte zuerst Accessibility aktivieren.",
                        Toast.LENGTH_LONG
                    ).show()
            }
        })

        setContentView(root)
    }
}

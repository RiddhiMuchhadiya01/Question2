package com.example.question2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var sound: Switch
    private lateinit var vib: Switch
    private lateinit var led: Switch
    private lateinit var benner: Switch
    private lateinit var content: Switch
    private lateinit var lockscreen: Switch

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sound = findViewById(R.id.soundlbl)
        vib = findViewById(R.id.viblbl)
        led = findViewById(R.id.showblbl)
        benner = findViewById(R.id.showblbl)
        content = findViewById(R.id.showclbl)
        lockscreen = findViewById(R.id.locklbl)

        val saveButton: Button = findViewById(R.id.saveButton)


        loadPreferences()

        saveButton.setOnClickListener {
            showConfirmationDialog()
        }
    }

    private fun loadPreferences() {
        val sharedPreferences = getSharedPreferences("notification_preferences", MODE_PRIVATE)
        sound.isChecked = sharedPreferences.getBoolean("sound", false)
        vib.isChecked = sharedPreferences.getBoolean("vib", false)
        led.isChecked = sharedPreferences.getBoolean("led", false)
        benner.isChecked = sharedPreferences.getBoolean("Banners", false)
        content.isChecked = sharedPreferences.getBoolean("Content", false)
        lockscreen.isChecked = sharedPreferences.getBoolean("Lockscreen", false)
    }

    private fun savePreferences() {
        val sharedPreferences = getSharedPreferences("notification_preferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("sound", sound.isChecked)
        editor.putBoolean("vibration", vib.isChecked)
        editor.putBoolean("led", led.isChecked)
        editor.putBoolean("Banners", benner.isChecked)
        editor.putBoolean("Content", content.isChecked)
        editor.putBoolean("Lockscreen", lockscreen.isChecked)
        editor.apply()
        Toast.makeText(this, "Preferences saved", Toast.LENGTH_SHORT).show()
    }

    private fun showConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Confirm Save")
            .setMessage("Do you want to save your preferences?")
            .setPositiveButton("Save") { _, _ -> savePreferences() }
            .setNegativeButton("Cancel", null)
            .show()
    }
}

package com.ultrasourov.ai.service

import android.accessibilityservice.AccessibilityService
import android.content.Intent
import android.net.Uri
import android.view.accessibility.AccessibilityEvent

class UltraAccessibilityService : AccessibilityService() {
    companion object {
        var instance: UltraAccessibilityService? = null
            private set
    }

    override fun onServiceConnected() {
        super.onServiceConnected()
        instance = this
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {}
    override fun onInterrupt() {}

    fun openWhatsApp(phoneNumber: String, messageText: String? = null) {
        val clean = phoneNumber.replace("[^0-9]".toRegex(), "")
        val uri = if (messageText.isNullOrBlank()) {
            Uri.parse("https://wa.me/$clean")
        } else {
            Uri.parse("https://wa.me/$clean?text=" + Uri.encode(messageText))
        }
        val intent = Intent(Intent.ACTION_VIEW, uri).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
        startActivity(intent)
    }

    fun makeDirectCall(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:$phoneNumber")).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
        startActivity(intent)
    }

    fun openMessenger() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://m.me/")).apply { flags = Intent.FLAG_ACTIVITY_NEW_TASK }
        startActivity(intent)
    }

    fun triggerGlobalAction(actionType: Int): Boolean = performGlobalAction(actionType)
}

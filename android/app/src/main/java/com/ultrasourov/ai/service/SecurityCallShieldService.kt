package com.ultrasourov.ai.service

import android.telecom.Call
import android.telecom.CallScreeningService

class SecurityCallShieldService : CallScreeningService() {
    override fun onScreenCall(callDetails: Call.Details) {
        val number = callDetails.handle?.schemeSpecificPart ?: ""
        val isScam = listOf("+8801912998811", "+18004190291", "+919820044123").any { number.contains(it) }

        val response = CallResponse.Builder().apply {
            if (isScam) {
                setDisallowCall(true)
                setRejectCall(true)
                setSkipNotification(false)
            } else {
                setDisallowCall(false)
            }
        }.build()
        respondToCall(callDetails, response)
    }
}

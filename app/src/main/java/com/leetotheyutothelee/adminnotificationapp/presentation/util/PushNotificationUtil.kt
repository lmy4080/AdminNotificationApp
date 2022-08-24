package com.leetotheyutothelee.adminnotificationapp.presentation.util

import com.google.firebase.messaging.FirebaseMessaging

object PushNotificationUtil {

    fun subscribeTopic(topic: String) {
        FirebaseMessaging.getInstance().subscribeToTopic(topic)
            .addOnCompleteListener {
            }
            .addOnFailureListener {
            }
    }

    fun unsubscribeTopic(topic: String) {
        FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
            .addOnCompleteListener {
            }
            .addOnFailureListener {
            }
    }
}
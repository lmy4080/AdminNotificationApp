package com.leetotheyutothelee.adminnotificationapp.presentation.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.leetotheyutothelee.adminnotificationapp.R
import com.leetotheyutothelee.adminnotificationapp.constant.BroadcastConstant
import com.leetotheyutothelee.adminnotificationapp.presentation.errorpickingproduct.ui.ErrorPickingProductActivity
import com.leetotheyutothelee.adminnotificationapp.presentation.model.ErrorPickingProductModel
import kotlin.math.abs

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        handleMessage(message)
        sendBroadcast(message)
    }

    @SuppressLint("UnspecifiedImmutableFlag")
    private fun handleMessage(message: RemoteMessage) {
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this, getString(R.string.default_notification_channel_id))

        val intent = Intent(this, ErrorPickingProductActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        notificationBuilder.setSmallIcon(R.drawable.ic_kurly_icon)
            .setContentTitle(getString(R.string.notificationTitle))
            .setColor(ContextCompat.getColor(this, R.color.kurly_200))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.notificationChannel)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(getString(R.string.default_notification_channel_id), name, importance)
            notificationManager.createNotificationChannel(channel)
        }

        with(NotificationManagerCompat.from(this)) {
            notify(1007, notificationBuilder.build())
        }
    }

    private fun sendBroadcast(message: RemoteMessage) {
        with(message.data) {
            val categoryType = this["categoryType"]
            val categoryName = this["categoryName"]
            val productName = this["productName"]
            val errorTime = this["errorTime"]
            val requestBoxNo = this["requestBoxNo"]
            val expectedCount = Integer.parseInt(this["expectedCount"] ?: "0")
            val actualCount = Integer.parseInt(this["actualCount"] ?: "0")
            val isOver = expectedCount < actualCount
            val displayCount = abs(expectedCount - actualCount)

            LocalBroadcastManager.getInstance(this@MyFirebaseMessagingService).sendBroadcast(Intent(BroadcastConstant.EVENT_NOTIFICATION).apply {
                putExtra(
                    "errorPickingProduct",
                    ErrorPickingProductModel(
                        categoryType = categoryType,
                        categoryName = categoryName,
                        productName = productName,
                        errorTime = errorTime,
                        requestBoxNo = requestBoxNo,
                        expectedCount = expectedCount,
                        actualCount = actualCount,
                        isOver = isOver,
                        displayCount = displayCount
                    )
                )
            })
        }
    }
}
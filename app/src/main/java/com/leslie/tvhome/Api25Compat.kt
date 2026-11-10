package com.leslie.tvhome

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.os.Build

/** Runtime gates; no NewApi lint suppression. Pre-28 identity checks still fail closed. */
internal object Api25Compat {
    @Suppress("DEPRECATION")
    fun version(info: PackageInfo): Long =
        if (Build.VERSION.SDK_INT >= 28) info.longVersionCode else info.versionCode.toLong()
    @Suppress("DEPRECATION")
    val signatureFlags: Int get() = if (Build.VERSION.SDK_INT >= 28)
        PackageManager.GET_SIGNING_CERTIFICATES else PackageManager.GET_SIGNATURES
    @Suppress("DEPRECATION")
    fun signers(info: PackageInfo?): Array<android.content.pm.Signature>? =
        if (Build.VERSION.SDK_INT >= 28) info?.signingInfo?.apkContentsSigners?.takeIf { it.isNotEmpty() }
            ?: info?.signatures else info?.signatures
    // API 25 has a firmware-global unknown-sources setting. It must never be changed here.
    fun canRequestInstalls(pm: PackageManager): Boolean =
        Build.VERSION.SDK_INT < 26 || pm.canRequestPackageInstalls()
    @Suppress("DEPRECATION")
    fun notification(c: Context, channel: String, title: String, importance: Int): Notification.Builder {
        if (Build.VERSION.SDK_INT >= 26) {
            c.getSystemService(NotificationManager::class.java)
                .createNotificationChannel(NotificationChannel(channel, title, importance))
            return Notification.Builder(c, channel)
        }
        return Notification.Builder(c)
    }
}

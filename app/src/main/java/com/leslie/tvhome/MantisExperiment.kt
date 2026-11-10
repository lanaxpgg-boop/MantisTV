package com.leslie.tvhome

/** The experiment is a normal app, never a HOME/firmware maintenance tool. */
internal object MantisExperiment {
    val enabled: Boolean get() = BuildConfig.FLAVOR == "mantis"
    fun defaults(ctx: android.content.Context) {
        if (!enabled) return
        // First-run defaults only; keep settings keys and the upstream renderer/layout intact.
        val sp = ctx.getSharedPreferences("tvhome", android.content.Context.MODE_PRIVATE)
        if (!sp.getBoolean("mantis_profile_v1", false)) sp.edit()
            .putString("background", "graphite").putString("wall_quality", "compat")
            .putString("aerial_quality", "compat").putString("cc_style", "glass")
            .putString("focus_style", "tvos").putBoolean("extreme_glass", false)
            .putBoolean("unified_idle", false).putBoolean("frame_screensaver", false)
            .putBoolean("guard", false).putBoolean("keep_adb", false)
            .putBoolean("clean_on_standby", false).putInt("clean_interval", 0)
            .putBoolean("shutdown_on_power", false).putBoolean("welcome_completed", true)
            .putBoolean("mantis_profile_v1", true).apply()
    }
}

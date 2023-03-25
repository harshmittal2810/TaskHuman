package com.harsh.taskhuman.featureconfig

import kotlinx.coroutines.flow.StateFlow

/**
 * Created by Harsh Mittal on 15/07/22
 */

interface FeatureConfig {
    fun getString(key: String, default: String = ""): String
    fun getLong(key: String, default: Long = 0L): Long
    fun getDouble(key: String, default: Double = 0.0): Double
    fun getBoolean(key: String, default: Boolean = false): Boolean
    fun observable(): StateFlow<Boolean>
}
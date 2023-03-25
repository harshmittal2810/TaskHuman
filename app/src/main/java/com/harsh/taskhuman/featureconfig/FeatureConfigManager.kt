package com.harsh.taskhuman.featureconfig

/**
 * Created by Harsh Mittal on 15/07/22
 */

interface FeatureConfigManager {
    fun init()
    fun reset()
    fun notifyObservable()
}
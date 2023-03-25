package buildconfig

/**
 * Created by Harsh Mittal on 01/07/22.
 **/
interface BuildConfigFields {

    fun domain(): String

    fun certificateFingerPrints(): List<String>

    fun appNameToShowInAnalyticsDashboard(): String
}
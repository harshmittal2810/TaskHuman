package buildconfig.default

import buildconfig.BuildConfigFields

/**
 * Created by Harsh Mittal on 01/07/22.
 **/
internal class OtherServerBuildConfigFields : BuildConfigFields {

    override fun domain(): String = "192.168.43.187:4000"

    override fun certificateFingerPrints(): List<String> {
        return listOf(
            "kegOkDaKNRaTsz1n5s/tuH9hJmOQeomFUcOnAu2uSb4="
        )
    }

    override fun appNameToShowInAnalyticsDashboard(): String = "Emplus"
}
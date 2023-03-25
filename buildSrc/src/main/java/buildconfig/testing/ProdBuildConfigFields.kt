package buildconfig.testing

import buildconfig.BuildConfigFields

/**
 * Created by Harsh Mittal on 01/07/22.
 **/
internal class ProdBuildConfigFields : BuildConfigFields {

    override fun domain(): String = "mobile.xoxoday.com/chef"

    override fun certificateFingerPrints(): List<String> {
        return listOf(
            "kegOkDaKNRaTsz1n5s/tuH9hJmOQeomFUcOnAu2uSb4="
        )
    }

    override fun appNameToShowInAnalyticsDashboard(): String = "Emplus"
}
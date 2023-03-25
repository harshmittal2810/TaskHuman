package buildconfig.testing

import buildconfig.BuildConfigFields

/**
 * Created by Harsh Mittal on 01/07/22.
 **/
internal class TestingBuildConfigFields : BuildConfigFields {

    override fun domain(): String = "accounts-qaenv.xoxotest.net/chef"

    override fun certificateFingerPrints(): List<String> {
        return listOf(
            "kegOkDaKNRaTsz1n5s/tuH9hJmOQeomFUcOnAu2uSb4="
        )
    }

    override fun appNameToShowInAnalyticsDashboard(): String = "Emplus(Testing)"
}
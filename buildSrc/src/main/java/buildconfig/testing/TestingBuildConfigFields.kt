package buildconfig.testing

import buildconfig.BuildConfigFields

/**
 * Created by Harsh Mittal on 01/07/22.
 **/
internal class TestingBuildConfigFields : BuildConfigFields {

    override fun domain(): String = "api-devenv.taskhuman.com/v0.8"

    override fun certificateFingerPrints(): List<String> {
        return listOf(
            ""
        )
    }

    override fun appNameToShowInAnalyticsDashboard(): String = "TaskHuman(Testing)"
}
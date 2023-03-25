package buildconfig.testing

import BuildTypes
import buildconfig.BuildConfigFieldFactory
import buildconfig.BuildConfigFields

/**
 * Created by Harsh Mittal on 01/07/22.
 **/
internal class TestingBuildConfigFieldFactory : BuildConfigFieldFactory {

    override fun getAllBuildConfigFields(buildType: String): BuildConfigFields {
        return when (buildType) {
            BuildTypes.DEBUG -> TestingBuildConfigFields()
            BuildTypes.QA -> TestingBuildConfigFields()
            BuildTypes.STAGING -> StagingBuildConfigFields()
            BuildTypes.LOCALHOST -> LocalHostBuildConfigFields()
            BuildTypes.OTHER_SERVER -> OtherServerBuildConfigFields()
            BuildTypes.PROD -> ProdBuildConfigFields()
            BuildTypes.RELEASE -> ProdBuildConfigFields()
            else -> throw IllegalArgumentException("Unknown build type '$buildType' found!")
        }
    }
}
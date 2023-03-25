package buildconfig

import ProductFlavors
import buildconfig.default.DefaultBuildConfigFieldFactory
import buildconfig.testing.TestingBuildConfigFieldFactory

/**
 * Created by Harsh Mittal on 01/07/22.
 **/
internal object BuildConfigFieldFactoryCreator {
    fun createBuildConfigFieldFactory(flavor: String): BuildConfigFieldFactory {
        return when (flavor) {
            ProductFlavors.DEFAULT -> {
                DefaultBuildConfigFieldFactory()
            }
            ProductFlavors.DEV -> {
                TestingBuildConfigFieldFactory()
            }
            else -> {
                throw IllegalArgumentException("Flavor $flavor undefined!")
            }
        }
    }
}
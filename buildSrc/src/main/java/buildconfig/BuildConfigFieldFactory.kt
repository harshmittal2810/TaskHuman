package buildconfig

/**
 * Created by Harsh Mittal on 01/07/22.
 **/
internal interface BuildConfigFieldFactory {

    fun getAllBuildConfigFields(buildType: String): BuildConfigFields
}
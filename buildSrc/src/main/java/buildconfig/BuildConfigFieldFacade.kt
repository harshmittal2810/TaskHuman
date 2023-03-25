package buildconfig

/**
 * Created by Harsh Mittal on 01/07/22.
 **/
object BuildConfigFieldFacade {

    private fun getAllBuildConfigFields(flavor: String, buildType: String): BuildConfigFields {
        return BuildConfigFieldFactoryCreator
            .createBuildConfigFieldFactory(flavor)
            .getAllBuildConfigFields(buildType)
    }

    fun getBuildFields(flavor: String, buildType: String): List<BuildConfigField> {
        val buildVariantSpecificBuildFields = getAllBuildConfigFields(flavor, buildType)
        val buildConfigFields = ArrayList<BuildConfigField>()
        buildConfigFields.add(getDomain(buildVariantSpecificBuildFields))
        buildConfigFields.add(getNetworkBaseUrl(buildVariantSpecificBuildFields))
        buildConfigFields.add(getSSLCertificateFingerprints(buildVariantSpecificBuildFields))
        buildConfigFields.add(getAppNameToShowInAnalyticsDashboard(buildVariantSpecificBuildFields))

        return buildConfigFields
    }

    fun getHostName(buildFields: List<BuildConfigField>): String {
        return buildFields.find { buildConfigField ->
            buildConfigField.key == BuildConstants.NETWORK_BASE_ENDPOINT
        }?.value?.replace("\"", "") ?: ""
    }

    private fun getDomain(buildFields: BuildConfigFields): BuildConfigField {
        return BuildConfigField(
            key = BuildConstants.NETWORK_BASE_ENDPOINT,
            value = "\"${buildFields.domain()}\"",
            dataType = BuildConstants.DATA_TYPE_STRING
        )
    }

    private fun getNetworkBaseUrl(buildFields: BuildConfigFields): BuildConfigField {
        return BuildConfigField(
            key = BuildConstants.NETWORK_BASE_URL,
            value = "\"https://${buildFields.domain()}/\"",
            dataType = BuildConstants.DATA_TYPE_STRING
        )
    }

    private fun getSSLCertificateFingerprints(buildFields: BuildConfigFields): BuildConfigField {
        return BuildConfigField(
            key = BuildConstants.CERTIFICATE_FINGERPRINTS,
            value = buildFields.certificateFingerPrints().joinToString(
                prefix = "{",
                separator = ",",
                postfix = "}",
                transform = { value ->
                    "\"$value\""
                }
            ),
            dataType = BuildConstants.DATA_TYPE_STRING_ARRAY
        )
    }

    private fun getAppNameToShowInAnalyticsDashboard(buildFields: BuildConfigFields): BuildConfigField {
        return BuildConfigField(
            key = BuildConstants.GA_APP_NAME,
            value = "\"${buildFields.appNameToShowInAnalyticsDashboard()}\"",
            dataType = BuildConstants.DATA_TYPE_STRING
        )
    }
}
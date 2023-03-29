package com.harsh.taskhuman.common.constants

import com.harsh.taskhuman.BuildConfig


/**
 * Created by Harsh Mittal on 11/07/22.
 */
object StringConstants {

    const val LOCAL_DATE_FORMAT = "EEE dd MMM, yyyy"
    val WEBSITE_REGEX =
        """^((ftp|http|https):\/\/)?(www.)?(?!.*(ftp|http|https|www.))[a-zA-Z0-9_]+(\.[a-zA-Z]+)+((\/)[\w#\-\.]+)*(\/\w+\?[a-zA-Z0-9_-]+=\w+(&[a-zA-Z0-9_-]+=\w+)*)?\/?${'$'}""".toRegex()

    interface BuildTypes {
        companion object {
            const val DEV = "dev"
            const val DEBUG = "debug"
            const val RELEASE = "release"
            const val PROD = "prod"
            const val STAGING = "staging"
            const val QA = "qa"
        }
    }

    //login constant
    const val AUTHORIZATION_TOKEN =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjQ3MTYsInVzZXJzIjp7InN0YXR1cyI6MCwidHlwZSI6MCwiaXNNb2JpbGVWZXJpZmllZCI6dHJ1ZX0sImlhdCI6MTY3OTU3MzU4N30.gaiGbeN9tWIojmvSj0imKtCWW0wMhLzN-UjmXevzuyk"

    //BuildConfig Constants
    var IDC_API = BuildConfig.NETWORK_BASE_ENDPOINT
    var NETWORK_BASE_URL = BuildConfig.NETWORK_BASE_URL
    var CERTIFICATE_FINGERPRINTS: Array<String> = BuildConfig.CERTIFICATE_FINGERPRINTS

    const val REQUEST_CODE = "requestCode"

    interface PermissionConstant {
        companion object {
            const val PERMISSION_REQUEST_CODE_CONTACTS = 201
            const val PERMISSION_REQUEST_CODE_CAMERA = 101
            const val PERMISSION_REQUEST_CODE_GALLERY = 102
            const val PERMISSION_REQUEST_CODE_DOCUMENT = 103
            const val PERMISSION_REQUEST_CODE_AUDIO = 206
            const val PERMISSION_REQUEST_CODE_CAMERA_MODE = 207
            const val PERMISSION_REQUEST_READ_WRITE_CALENDAR = 208
            const val PERMISSION_REQUEST_READ_WRITE_UPDATE_CALENDAR = 209
            const val PERMISSION_REQUEST_WRITE_EXTERNAL_STORAGE = 211
            const val PERMISSION_REQUEST_READ_WRITE_EXTERNAL_STORAGE = 213
            const val PERMISSION_REQUEST_MULTIPLE_UPLOAD = 214
            const val PERMISSION_REQUEST_IMAGE_UPLOAD = 215
            const val PERMISSION_REQUEST_DOCUMENT_UPLOAD = 216
            const val PERMISSION_REQUEST_SHARED_TEXT_UPLOAD = 217

        }
    }

    //request code and result code
    const val NULL_KEY = -1
    const val TYPE_PDF = 97
    const val TYPE_DOC = 98
    const val TYPE_XLS = 99
    const val TYPE_PPT = 100
    const val TYPE_TXT = 101
    const val IMAGE_TYPE = 102
    const val DOCUMENT_TYPE = 103
}
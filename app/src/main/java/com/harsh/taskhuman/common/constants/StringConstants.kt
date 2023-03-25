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
    const val LOGIN_SAML_TOKEN = "saml_token"
    const val LOGIN_BASE_URL = "base_url"
    const val LOGIN_LINK = "link"

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


    //key constant
    const val QUERY_TAG = "tag"
    const val QUERY = "query"
    const val VARIABLES = "variables"
    const val OFFSET = "offset"
    const val LIMIT = "limit"
    const val PAGE = "page"
    const val VALUE_CARDS = "value_cards"
    const val APPROVER_ID = "approver_id"
    const val APPROVAL_STATUS = "approval_status"
    const val NOMINATED_BY = "nominated_by"
    const val AWARD_IDS = "award_ids"
    const val AWARD_ID = "award_id"
    const val NOMINATION_ID = "nomination_id"
    const val NOMINEE_ID = "nominee_id"
    const val NOMINEE_IDS = "nominee_ids"
    const val NOMINEE = "nominee"
    const val AWARD_KIND_ID = "award_kind_id"
    const val AWARD_TYPE = "award_type"
    const val CITATION_MESSAGE = "citation_message"
    const val APPRECIATION_ID = "appreciation_id"
    const val API_VERSION = "api_version"
    const val APPROVER_MESSAGE = "approver_message"
    const val APPROVER_STATUS = "approver_status"
    const val BUDGET_ID = "budget_id"
    const val RANGE = "range"
    const val SEARCH_TERM = "search_term"
    const val SEARCH_TYPE = "search_type"
    const val FILTER_KEY = "filter_key"
    const val NOT_IN_SEARCH_OPTION = "not_in_search_option"
    const val NOT_ELIGIBLE_REACHED = "not_eligible_reached"
    const val NAME = "name"
    const val WEIGHTAGE = "weightage"
    const val NOMINATOR_CITATION = "nominator_citation"
    const val JURY_CRITERIA_CITATION = "jury_criteria_citation"
    const val POINTS = "points"
    const val CREATE_DATA = "create_data"
    const val REASON = "reason"
    const val ROLLBACK_TYPE = "rollback_type"
    const val APPRECIATION = "appreciation"
    const val RECORD_ID = "record_id"
    const val AUTH_UPLOADS = "auth_uploads"
    const val GROUP_ID = "group_id"
    const val IS_FROM_NEW_POST = "isFromNewPost"
    const val JOIN_DATA = "join_data"
    const val USER_ID = "user_id"
    const val UNIQUE_ID = "unique_id"
    const val BOARD_TYPE = "board_type"
    const val POST_ID = "post_id"
    const val POLL_ID = "poll_id"
    const val FEED_ID = "feed_id"
    const val FEEDID = "feedId"
    const val EVENT_DATE = "event_date"
    const val USER_TENURE = "user_tenure"
    const val MESSAGE = "message"
    const val ANSWERS = "answers"
    const val COMMENT_ID = "comment_id"
    const val CLAP_CHECK = "clapCheck"
    const val LIKE_CHECK = "likeCheck"
    const val GROUP_TYPE = "group_type"
    const val ADD_DATA = "add_data"
    const val URL = "url"
    const val FCM_TOKEN = "fcm_token"

    //variable constant
    const val HOME_MOBILE = "home_mobile"
    const val PROFILE_MOBILE = "profile_mobile"
    const val NOMINATION_MOBILE = "nomination_mobile"
    const val CARDS = "cards"
    const val NOMINATE_V2 = "nominateV2"
    const val ACCESS_ROLE = "accessRole"
    const val ADMIN = "admin"
    const val CONSUMER = "consumer"
    const val FEED = "feed"
    const val FEEDS = "feeds"
    const val FEED_MOBILE = "feed_mobile"
    const val AWARD = "award"
    const val GROUP = "group"
    const val WISHBOARD = "wishBoard"
    const val RIGHTNAV = "rightNav"
    const val NOTIF = "notif"

    interface DialogFragmentTag {
        companion object {
            const val MEMBERS_DIALOG_FRAGMENT = "MEMBERS_DIALOG_FRAGMENT"
            const val EDIT_DIALOG_FRAGMENT = "EDIT_DIALOG_FRAGMENT"
            const val AWARDS_DIALOG_FRAGMENT = "AWARDS_DIALOG_FRAGMENT"
            const val OPTIONS_DIALOG_FRAGMENT = "OPTIONS_DIALOG_FRAGMENT"
            const val NOTIFICATION_DIALOG_FRAGMENT = "NOTIFICATION_DIALOG_FRAGMENT"
            const val APPRECIATE_FILTER_FRAGMENT = "APPRECIATE_FILTER_FRAGMENT"
            const val CORE_FILTER_FRAGMENT = "CORE_FILTER_FRAGMENT"
            const val ACCEPT_NOMINATION_DIALOG_FRAGMENT = "ACCEPT_NOMINATION_DIALOG_FRAGMENT"
            const val REJECT_NOMINATION_DIALOG_FRAGMENT = "REJECT_NOMINATION_DIALOG_FRAGMENT"
            const val APPROVER_FRAGMENT = "APPROVER_FRAGMENT"
            const val BUDGET_FRAGMENT = "BUDGET_FRAGMENT"
        }
    }
}
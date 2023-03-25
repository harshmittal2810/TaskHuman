package com.harsh.taskhuman.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.OpenableColumns
import android.webkit.MimeTypeMap
import com.google.android.datatransport.BuildConfig
import com.harsh.taskhuman.common.constants.StringConstants
import com.harsh.taskhuman.common.util.isNotValidOrEmpty
import com.harsh.taskhuman.libraries.logs.LocalLog
import java.io.File
import java.util.*

/**
 * Created by Harsh Mittal on 11/07/22.
 **/
object FileUtility {

    fun getFileDirectory(context: Context): File {
        val filesDir = context.filesDir.apply { mkdirs() }
        // For Prod builds we will use application internal storage for log files
        return if (BuildConfig.BUILD_TYPE.equals(
                StringConstants.BuildTypes.PROD, ignoreCase = true
            )
        ) {
            filesDir
        } else {
            getApplicationPublicDirectory(context) ?: filesDir
        }
    }

    fun getPicturesDirectory(context: Context): File? {
        return try {
            context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)?.apply {
                mkdirs()
            }
        } catch (e: Throwable) {
            null
        }
    }

    private fun getApplicationPublicDirectory(context: Context): File? {
        return try {
            context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)?.apply {
                mkdirs()
            }
        } catch (e: Throwable) {
            LocalLog.error("FileUtility", "unable to fetch Documents directory", e)
            null
        }
    }

    fun getFileName(context: Context, uri: Uri?): String {
        var result: String? = ""
        if (uri == null) {
            return ""
        }
        if (uri.scheme == "content") {
            val cursor = context.contentResolver.query(uri, null, null, null, null)
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result =
                        cursor.getString(cursor.getColumnIndexOrThrow(OpenableColumns.DISPLAY_NAME))
                }
            } finally {
                cursor?.close()
            }
        }
        if (result.isNotValidOrEmpty()) {
            result = uri.path ?: ""
            val cut = result.lastIndexOf('/')
            if (cut != -1) {
                result = result.substring(cut + 1)
            }
        }
        return result ?: ""
    }

    fun getFileType(path: String): Int {
        if (path.endsWith("xls") || path.endsWith("xlsx")) {
            return StringConstants.TYPE_XLS
        }
        if (path.endsWith("doc") || path.endsWith("docx")) {
            return StringConstants.TYPE_DOC
        }
        if (path.endsWith("ppt") || path.endsWith("pptx")) {
            return StringConstants.TYPE_PPT
        }
        if (path.endsWith("pdf")) {
            return StringConstants.TYPE_PDF
        }
        return if (path.endsWith("txt")) {
            StringConstants.TYPE_TXT
        } else StringConstants.NULL_KEY
    }

    fun getMimeType(context: Context, uri: Uri): String {
        return if (uri.scheme == ContentResolver.SCHEME_CONTENT) {
            val cr = context.contentResolver
            cr.getType(uri) ?: ""
        } else {
            val fileExtension = MimeTypeMap.getFileExtensionFromUrl(uri.toString())
            MimeTypeMap.getSingleton()
                .getMimeTypeFromExtension(fileExtension.lowercase(Locale.getDefault())) ?: ""
        }
    }
}
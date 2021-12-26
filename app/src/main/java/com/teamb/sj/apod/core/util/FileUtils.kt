package com.teamb.sj.apod.core.util

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.webkit.MimeTypeMap
import android.widget.Toast
import java.io.File

object FileUtils {

    fun downloadImage(url: String, current: Context): Long {
        val manager = current.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri: Uri = Uri.parse(url)
        val fileName: String = Constants.FILE_NAME + "." + MimeTypeMap.getFileExtensionFromUrl(url)
        Toast.makeText(current, "Download in Progress... Please Wait... ", Toast.LENGTH_LONG).show()

        if (isFileExists(fileName)) {
            deleteFile(fileName)
        }

        val request = DownloadManager.Request(uri)
            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        return manager.enqueue(request)
    }

    private fun isFileExists(filename: String): Boolean {

        val folder1 = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            ).path + Constants.FILE_SEPARATOR +
                filename
        )
        return folder1.exists()
    }

    private fun deleteFile(filename: String): Boolean {
        val folder1 = File(
            Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DOWNLOADS
            ).path + Constants.FILE_SEPARATOR +
                filename
        )
        return folder1.delete()
    }
}

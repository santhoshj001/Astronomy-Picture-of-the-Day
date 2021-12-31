package com.teamb.sj.apod.feature_home.presentation.picturedetail.components

import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.teamb.sj.apod.core.util.DownloadBroadcastReceiver
import com.teamb.sj.apod.core.util.FileUtils
import com.teamb.sj.apod.core.util.FirebaseUtils
import com.teamb.sj.apod.feature_home.domain.model.PictureDetail

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PictureDetailView(
    picture: PictureDetail,
    favoriteState: Boolean,
    toggleFav: (Boolean) -> Unit,
    sendMessage: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { },
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
        content = {
            Column {
                Image(
                    painter = rememberImagePainter(
                        picture.url,
                        builder = {
                            crossfade(true)
                        }
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(120.dp, 360.dp),
                    alignment = Alignment.Center,
                    contentScale = ContentScale.FillWidth
                )
                PictureDetailBody(
                    picture,
                    favoriteState,
                    toggleFav,
                    sendMessage
                )
            }
        }
    )
}

@Composable
fun PictureDetailBody(
    picture: PictureDetail,
    isFav: Boolean,
    onClick: (Boolean) -> Unit,
    sendMessage: (String) -> Unit,
) {
    var fileID = 0L
    var downloadInProgress = false
    DownloadBroadcastReceiver(DownloadManager.ACTION_DOWNLOAD_COMPLETE) { _, context ->
        downloadInProgress = false
        try {
            val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            val uri = manager.getUriForDownloadedFile(fileID)
            val intent = Intent(Intent.ACTION_ATTACH_DATA)
            intent.setDataAndType(uri, "image/*")
            intent.putExtra("mimeType", "image/*")
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            context.startActivity(Intent.createChooser(intent, "Set as:"))
        } catch (e: Exception) {
            Log.i("team b", "PictureDetailBody: Intent")
        }
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = picture.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "\u00a9 " + picture.copyright,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
        ExpandableText(text = picture.explanation, modifier = Modifier.padding(horizontal = 8.dp))

        Row(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.End
        ) {
            OutlinedButton(onClick = { onClick(!isFav) }, content = {
                val imageVector =
                    if (isFav) Icons.Filled.Favorite
                    else Icons.Outlined.FavoriteBorder
                Icon(
                    imageVector,
                    contentDescription = "save",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
                val label = if (isFav) "Saved" else "Save"
                Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                Text(
                    text = label,
                )
            })
            val visibility = FirebaseUtils.isSetWallpaperEnabled()
            if (visibility && picture.mediaType == "image") {
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedButton(onClick = {
                    if (downloadInProgress) {
                        sendMessage("Already a download in Progress")
                    } else {
                        downloadInProgress = true
                        sendMessage("Downloading... please wait...")
                        fileID = FileUtils.downloadImage(picture.hdUrl, context)
                    }
                }) {
                    Icon(
                        Icons.Outlined.Download,
                        contentDescription = "Download",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                    Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
                    Text(
                        text = "Set Wallpaper"
                    )
                }
            }
        }
    }
}

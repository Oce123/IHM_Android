package com.example.ihm;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;

import androidx.core.app.NotificationCompat;

import android.util.Log;
import android.widget.Toast;

/**
 * A simple example of IntentService that downloads a file from the Internet to
 * the pictures directory.
 * <p>
 * With the changes in API 29, this example will need to be rewritten with I'm guessing
 * a new picker, plus the detectFileUriExposure() will still be a problem as well.
 * likely just convert this example to the download manager service, instead of written our own?
 */

public class FileDownloaderIntentService extends IntentService {

    public FileDownloaderIntentService() {
        super("FileDownloaderIntentService");
    }

    String TAG = "fileDLService";

    @Override
    protected void onHandleIntent(Intent intent) {
        URL url;
        Uri uri = null;
        File file ;
        Toast.makeText(this, "service starting", Toast.LENGTH_SHORT).show();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            File extdir;
            url = (URL) extras.get("URL");
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                extdir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                file = new File(extdir, extras.getString("FILE"));
                Log.wtf(TAG, "file location is (sdcard?) " + file.getAbsolutePath());
                Log.d(TAG, "File:" + file.getAbsolutePath());
            } else {
                uri = (Uri) extras.get("URI");
                Log.d(TAG, "File:" + uri.toString());
                file =  new File(uri.toString());
            }
        } else {
            shoNotification(-1, "Error, No file to download", null);
            return;  //nothing to do.
        }

        try {
            long startTime = System.currentTimeMillis();
            FileOutputStream fos;

            Log.d(TAG, "download url:" + url);

            //Open a connection to that URL.
            HttpURLConnection.setFollowRedirects(true);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //open output file
            if (uri == null) {
                fos = new FileOutputStream(file);
                Log.d(TAG, "downloaded file name:" + file.getAbsolutePath());
            } else {
                ParcelFileDescriptor pfd = getContentResolver().
                        openFileDescriptor(uri, "w");
                fos =  new FileOutputStream(pfd.getFileDescriptor());

            }
            try {
                Log.d(TAG, "download beginning");
                //read in from the connection (which is not a string, so getting bytes) and then
                //write those bytes out to the file.  There is likely a zero write at the end, but
                //doesn't harm anything.
                InputStream is = urlConnection.getInputStream();
                byte[] buffer = new byte[4096];
                int n = -1;
                while ((n = is.read(buffer)) != -1) {
                    Log.wtf(TAG, "Wrote " + n + " bytes");
                    fos.write(buffer, 0, n);
                }
                //note there is a catch at the end to print the error message if there is one.
            } finally {
                urlConnection.disconnect();
                fos.close();
            }

            //new tell the system that the file exists , so it will show up in gallery/photos/whatever
            if (uri == null) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATE_TAKEN, System.currentTimeMillis());
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                values.put(MediaStore.MediaColumns.DATA, file.toString());
                getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            }
            //setup a notification so the user knows it has finished.
            shoNotification(0, "file downloaded in "
                    + ((System.currentTimeMillis() - startTime) / 1000)
                    + " sec", file);

        } catch (IOException e) {
            Log.wtf(TAG, "something died");
            e.printStackTrace();
            shoNotification(-2, "Error: " + e, null);
        }
        //stopSelf();  //no need to end the IntentService. It stop itself if the Intent queue is empty
    }


    public void shoNotification(int val, String message, File file) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder notificationBuilder;
        String title;
        notificationBuilder = new NotificationCompat.Builder(getApplicationContext(), MainActivity.id)
                .setSmallIcon(R.drawable.retour_button)
                .setWhen(System.currentTimeMillis())  //When the event occurred, now, since notificationBuilder are stored by time.
                .setContentText(message)  //message when looking at the notification, second row
                .setAutoCancel(true);   //allow auto cancel when pressed.

        if (val >= 0) { // no error
            Intent notificationIntent = new Intent();
            notificationIntent.setAction(Intent.ACTION_VIEW);
            notificationIntent.setDataAndType(
                    Uri.parse("file://" + file.getAbsolutePath()), "image/*"
            );
            title = "File Download";
            PendingIntent contentIntent = PendingIntent.getActivity(this, 100, notificationIntent, 0);
            notificationBuilder.setContentIntent(contentIntent);  //what activity to open.
        } else { // val < 0: errors?
            title = "DL Error";
        }
        notificationBuilder.setContentTitle(title);   //Title message top row.

        Notification notification = notificationBuilder.build();  //finally build and return a Notification.
        //finally Show the notification
        notificationManager.notify(100, notification);
    }

}

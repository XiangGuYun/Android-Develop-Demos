package com.kotlinlib.other;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.support.v4.content.FileProvider;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UpdateUtils {

//    private static int total;
//
//    public static void installApks(Context ctx, File file) {
//        if (!file.exists())
//            return;
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            Uri contentUri = FileProvider.getUriForFile(ctx, "com.fanghou.app.fileprovider", file);
//            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
//        } else {
//            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        }
//        ctx.startActivity(intent);
//    }
//
//
//    public static void download(Activity activity, URL url, ProgressBar progressBar, TextView tvProgress){
//        new Thread(() -> {
//            HttpURLConnection conn;
//            FileOutputStream fos;
//            Process.setThreadPriority(-21);
//            try {
//                conn = (HttpURLConnection) url.openConnection();
//                if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                    InputStream stream = conn.getInputStream();
//                    progressBar.setMax(conn.getContentLength());
//                    fos = new FileOutputStream(new File(Environment
//                            .getExternalStorageDirectory() + "/new_apk3.apk"));
//                    byte[] b = new byte[1024];
//                    int len;
//                    total = 0;
//                    while ((len = stream.read(b)) != -1) {  //先读到内存
//                        fos.write(b, 0, len);
//                        total = total + len;
//                        activity.runOnUiThread(() -> {
//                            progressBar.setProgress(total);
//                            tvProgress.setText(String.format("%.1f", (total * 1.00f / conn.getContentLength() * 1.00f) * 100)
//                                    + "%");
//                        });
//                    }
//                    fos.flush();
//                }
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                activity.runOnUiThread(() -> {
//                    installApks(activity, new File(Environment
//                            .getExternalStorageDirectory() + "/new_apk3.apk"));
//                    activity.finish();
//                });
//            }
//        }).start();
//    }

}


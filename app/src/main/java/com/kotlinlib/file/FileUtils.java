package com.kotlinlib.file;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileUtils {

    public static String getFromAssets(Context ctx, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStreamReader inputReader = new InputStreamReader(ctx.getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line;
            while ((line = bufReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

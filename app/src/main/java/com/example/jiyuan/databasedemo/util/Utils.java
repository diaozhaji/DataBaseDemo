package com.example.jiyuan.databasedemo.util;

import android.content.Context;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.example.jiyuan.databasedemo.model.Chapters;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Utils {

    public static String getFromAssets(Context context, String fileName, String charset) {
        String result = "";
        String encode = "UTF-8";
        if (!TextUtils.isEmpty(charset)) {
            encode = charset;
        }
        try {
            InputStreamReader inputReader =
                new InputStreamReader(context.getResources().getAssets().open(fileName), encode);
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) result += line;
            return result;
        } catch (Exception e) {
        }

        return result;
    }

    public static Chapters parseChapter(String json) {
        return JSON.parseObject(json, Chapters.class);
    }
}

package com.example.xplorecity.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileIO {

    public static void saveIMG(Context context, Bitmap bitmapImage, String path, String fileName) {
        File file = new File(context.getFilesDir(), path + fileName);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            if(bitmapImage.compress(Bitmap.CompressFormat.JPEG,100, fileOutputStream))
            {
                //Toast saved = Toast.makeText(context, "Image saved.", Toast.LENGTH_SHORT);
                //saved.show();
            }
            else{
                //Toast unsaved = Toast.makeText(context, "Image not save.", Toast.LENGTH_SHORT);
                //unsaved.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Bitmap loadIMG(Context context, String directoryName, String fileName) {
        File file = new File(context.getFilesDir(), directoryName + fileName);

        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void test (Context ctx, String fileName) {
        File directory;
        if (fileName.isEmpty()) {
            directory = ctx.getFilesDir();
        }
        else {
            directory = ctx.getDir(fileName, ctx.MODE_PRIVATE);
        }
        File[] files = directory.listFiles();
        String[] dir = directory.list();
        for(File f:files) {
            System.out.println(f.getAbsoluteFile());
        }
    }

    public static String loadFile(Context ctx, String path, String fileName) {

        String line = null;

        try {
            FileInputStream fileInputStream = new FileInputStream (new File(path + fileName));
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();

            while ( (line = bufferedReader.readLine()) != null )
            {
                stringBuilder.append(line + System.getProperty("line.separator"));
            }
            fileInputStream.close();
            line = stringBuilder.toString();

            bufferedReader.close();
        }
        catch(Exception ex) {
            Log.d("Error", ex.getMessage());
        }
        return line;
    }

    public static void saveToFile(Context ctx, String path, String fileName, String data)  {
        File file = new File(ctx.getFilesDir(), path + fileName);

        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static File createFile(Context context, String directoryName, String fileName) {

        File directory = context.getDir(directoryName, Context.MODE_PRIVATE);

        if(!directory.exists() && !directory.mkdirs()){
            Log.e("ImageSaver","Error creating directory " + directory);
        }

        return new File(directory, fileName);
    }


}

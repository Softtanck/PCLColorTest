package com.softtanck.tangce.pclcolortest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.softtanck.tangce.pclcolortest.usbprinter.PCLPrinter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new test().execute();

    }

    private class test extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            String path = Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "test_new.png";
            File file = new File(path);
            FileInputStream fis = null;
            try {

                Bitmap bitmap = BitmapFactory.decodeFile(path);
                bitmap = resizeImage(bitmap, 400, 600);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(path));
                fis = new FileInputStream(path);
                PCLPrinter pclPrinter = new PCLPrinter(fis);
                byte[] bytes = pclPrinter.print();
//                Socket socket = new Socket("10.50.40.100", 9100);
//                OutputStream outputStream = socket.getOutputStream();
//                outputStream.write(bytes);
//                outputStream.flush();
//                File file1 = new File(Environment.getExternalStorageDirectory() + File.separator + "Android" + File.separator + "cqm.pcl");
//                FileOutputStream fos = new FileOutputStream(file1);
//                fos.write(bytes);
//                Log.d("Tanck", "over");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public Bitmap resizeImage(Bitmap bitmap, int newWidth, int newHeight) {

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        // 计算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片
        Bitmap newbm = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newbm;

    }
}

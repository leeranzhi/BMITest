//package com.demo.bmitest.util;
//
//import android.Manifest;
//import android.app.Activity;
//import android.content.Context;
//import android.content.pm.PackageManager;
//import android.media.MediaPlayer;
//import android.os.Environment;
//import android.support.v4.app.ActivityCompat;
//import android.support.v4.content.ContextCompat;
//
//import java.io.File;
//import java.io.IOException;
//
//
//public class Utility {
//    private MediaPlayer mediaPlayer=new MediaPlayer();
//    public static void CheckPersmission(Context mcontext){
//        if(ContextCompat.checkSelfPermission(mcontext, Manifest.permission.
//                WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions((Activity) mcontext,new String[]{
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
//
//        }else {
//            initMeidaPlayer();//初始化MediaPlayer
//
//        }
//    }
//
//    private void initMeidaPlayer() {
//        try{
//            File file=new File(Environment.getExternalStorageDirectory(),"music.mp3");
//            mediaPlayer.setDataSource(file.getPath());//指定文件路径
//            mediaPlayer.prepare();//准备状态
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

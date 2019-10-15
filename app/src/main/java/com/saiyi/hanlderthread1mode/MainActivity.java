package com.saiyi.hanlderthread1mode;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private HandlerThread handlerThread1;
    private Handler iohandler;

    @SuppressLint("HandlerLeak")
    private Handler mainHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0:
                    Log.e("handleMessage",Thread.currentThread().getName()+"=======mainHandler");
                    iohandler.sendEmptyMessageDelayed(0,4000);
                    break;
                default:
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlerThread1 = new HandlerThread("handler1");
        handlerThread1.start();

        iohandler = new Handler(handlerThread1.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Log.e("handleMessage",Thread.currentThread().getName()+"=======iohandler");
                        mainHandler.sendEmptyMessageDelayed(0,4000);
                        break;
                    default:
                        break;
                }
                super.handleMessage(msg);
            }
        };
        mainHandler.sendEmptyMessageDelayed(0,4000);
//        Log.e("onCreate",Thread.currentThread().getName()+"=======MainThread");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.e("Thread",Thread.currentThread().getName()+"=======");
//            }
//        }).start();

    }
}

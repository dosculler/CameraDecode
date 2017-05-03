package com.nexgo.cameradecode;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.nexgo.cameradecode.aidl.*;

public class MainActivity extends AppCompatActivity {
    private CameraDecode mCameraDecode;
    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DecodeResult.SUCCESS:
                    Toast.makeText(MainActivity.this, "扫码内容:" + (String) msg.obj, Toast.LENGTH_SHORT).show();
                    break;
                case DecodeResult.TIMEOUT:
                    Toast.makeText(MainActivity.this, "扫码超时", Toast.LENGTH_SHORT).show();
                    break;
                case DecodeResult.CANCEL:
                    Toast.makeText(MainActivity.this, "用户取消", Toast.LENGTH_SHORT).show();
                    break;
                case DecodeResult.PARAM_INVALID:
                    Toast.makeText(MainActivity.this, "扫码参数取消", Toast.LENGTH_SHORT).show();
                    break;
                case DecodeResult.DEVICE_BUSY:
                    Toast.makeText(MainActivity.this, "扫码异常", Toast.LENGTH_SHORT).show();
                    postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            bindService();
                        }
                    }, 1000);
                    break;
                default:
                    Toast.makeText(MainActivity.this, "扫码失败", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindService();
        Button scanner = (Button) findViewById(R.id.button);
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(DecodeConfig.IS_AUTO_FOCUS, true);
                    bundle.putBoolean(DecodeConfig.IS_BLUK_MODE, false);
                    bundle.putBoolean(DecodeConfig.IS_USE_FRONT_CCD, false);
                    bundle.putBoolean(DecodeConfig.IS_BEEP, true);
                    bundle.putString(DecodeConfig.FLASH_MODE, "off"); //Supported flash mode values[off, auto, on, torch]:
                    bundle.putString(DecodeConfig.INTERVAL, "0");
                    bundle.putBoolean(DecodeConfig.IS_USE_DECODE_INT25, true);    //enable single decode
                    mCameraDecode.initScanner(bundle);
                    int result = mCameraDecode.startScan(3600, new OnDecodeListener.Stub() {//break the scan after 3600s timeout
                        @Override
                        public void onDecodeResult(int retCode, String data) throws RemoteException {

                            Message msg = new Message();
                            msg.what = retCode;
                            msg.obj = data;
                            myHandler.sendMessage(msg);
                        }
                    });
                    if (result != DecodeResult.SUCCESS) {
                        myHandler.sendEmptyMessage(result);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindService() {
        Intent intent = new Intent();
        intent.setAction("com.nexgo.cameradecode.service");
        intent.setPackage("com.nexgo.cameradecode.service");
        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mCameraDecode = CameraDecode.Stub.asInterface(iBinder);
                IBinder.DeathRecipient deathRecipient = new IBinder.DeathRecipient() {
                    @Override
                    public void binderDied() {
                        myHandler.sendEmptyMessage(DecodeResult.DEVICE_BUSY);
                    }
                };
                try {
                    iBinder.linkToDeath(deathRecipient, 0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                myHandler.sendEmptyMessage(DecodeResult.FAIL);
            }
        };
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }
}

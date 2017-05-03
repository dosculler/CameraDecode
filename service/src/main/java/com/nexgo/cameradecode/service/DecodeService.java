package com.nexgo.cameradecode.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.os.OperationCanceledException;
import android.os.RemoteException;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import net.sourceforge.zbar.android.CaptureActivity;
import net.sourceforge.zbar.android.PreferencesActivity;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.subjects.BehaviorSubject;

/**
 * Created by xiaox on 2016/12/6.
 */

public class DecodeService extends Service {
    private Context mContext;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        mContext = this;
        return new CameraDecodeBinder();
    }

    private class CameraDecodeBinder extends CameraDecode.Stub {
        private BehaviorSubject<String> subject;

        private final BroadcastReceiver mReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (CaptureActivity.ACTION_RESULT_SCANNER.equals(action)) {
                    subject.onNext(intent.getExtras().getString("data"));
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    boolean isConttinue = prefs.getBoolean(PreferencesActivity.KEY_BULK_MODE, false);
                    if (!isConttinue) {
                        subject.onCompleted();
                    }
                }
                if (CaptureActivity.ACTION_CLOSE_SCANNER.equals(action)) {
                    subject.onError(new OperationCanceledException());
                }
            }
        };

        @Override
        public void initScanner(Bundle bundle) throws RemoteException {
            if (bundle != null) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
                editor.putBoolean(PreferencesActivity.KEY_USED_FRONT_CCD, bundle.getBoolean(DecodeConfig.IS_USE_FRONT_CCD, false));
                editor.putBoolean(PreferencesActivity.KEY_BULK_MODE, bundle.getBoolean(DecodeConfig.IS_BLUK_MODE, false));
                editor.putString(PreferencesActivity.KEY_BULK_MODE_PERIOD, String.valueOf(bundle.getInt(DecodeConfig.INTERVAL, 0)));
                editor.putBoolean(PreferencesActivity.KEY_AUTO_FOCUS, bundle.getBoolean(DecodeConfig.IS_AUTO_FOCUS, true));
                editor.putBoolean(PreferencesActivity.KEY_BEEP, bundle.getBoolean(DecodeConfig.IS_BEEP, true));
                editor.putString(PreferencesActivity.KEY_FLASH_MODE, String.valueOf(bundle.getString(DecodeConfig.FLASH_MODE, "off")));

                /**********whole newtologic decode and mask frequently used decode like QR**********/
                //editor.putBoolean(PreferencesActivity.KEY_USE_RARE_DECODE, bundle.getBoolean(DecodeConfig.IS_USE_RARE_DECODE, false));//Note: this is a generate mask of whole decode.

                editor.putBoolean(PreferencesActivity.KEY_DECODE_AZTEC, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_AZTEC, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_CODE11, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_CODE11, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_CODE49, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_CODE49, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_DATAMATRIX, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_DATAMATRIX, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_INT25, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_INT25, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_MAXICODE, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_MAXICODE, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_MICROPDF, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_MICROPDF, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_OCR, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_OCR, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_POSTNET, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_POSTNET, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_ISBT, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_ISBT, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_BPO, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_BPO, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_CANPOST, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_CANPOST, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_AUSPOST, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_AUSPOST, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_IATA25, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_IATA25, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_CODABLOCK, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_CODABLOCK, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_JAPOST, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_JAPOST, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_PLANET, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_PLANET, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_DUTCHPOST, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_DUTCHPOST, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_MSI, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_MSI, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_TLCODE39, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_TLCODE39, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_TRIOPTIC, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_TRIOPTIC, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_CODE32, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_CODE32, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_STRT25, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_STRT25, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_MATRIX25, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_MATRIX25, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_PLESSEY, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_PLESSEY, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_CHINAPOST, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_CHINAPOST, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_KOREAPOST, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_KOREAPOST, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_TELEPEN, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_TELEPEN, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_CODE16K, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_CODE16K, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_POSICODE, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_POSICODE, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_COUPONCODE, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_COUPONCODE, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_USPS4CB, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_USPS4CB, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_IDTAG, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_IDTAG, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_LABEL, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_LABEL, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_GS1_128, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_GS1_128, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_HANXIN, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_HANXIN, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_GRIDMATRIX, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_GRIDMATRIX, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_POSTALS, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_POSTALS, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_POSTALS1, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_POSTALS1, false));
                editor.putBoolean(PreferencesActivity.KEY_DECODE_BOLOGIES, bundle.getBoolean(DecodeConfig.IS_USE_DECODE_BOLOGIES, false));

                editor.apply();
            }
        }

        @Override
        public int startScan(int timeout, final OnDecodeListener listener) throws RemoteException {
            if (listener == null || timeout < 0) return DecodeResult.PARAM_INVALID;
            //重复调用
            if (subject != null && subject.hasObservers()) {
                listener.onDecodeResult(DecodeResult.DEVICE_BUSY, null);
                return DecodeResult.DEVICE_BUSY;
            }
            subject = BehaviorSubject.create();
            Observable<String> observable = subject.asObservable();
            if (timeout > 0) {
                observable = observable.timeout(timeout, TimeUnit.SECONDS);
            }
            observable.doOnSubscribe(new Action0() {
                @Override
                public void call() {
                    IntentFilter filter = new IntentFilter();
                    filter.addAction(CaptureActivity.ACTION_RESULT_SCANNER);
                    filter.addAction(CaptureActivity.ACTION_CLOSE_SCANNER);
                    mContext.registerReceiver(mReceiver, filter);
                    Intent intent = new Intent();
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setClass(mContext, CaptureActivity.class);
                    mContext.startActivity(intent);
                }
            })
                    .doOnError(new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            Intent intent = new Intent();
                            intent.setAction(CaptureActivity.ACTION_CLOSE_SCANNER);
                            mContext.sendBroadcast(intent);// 把收到的消息以广播的形式发送出去
                        }
                    })
                    .doAfterTerminate(new Action0() {
                        @Override
                        public void call() {
                            try {
                                mContext.unregisterReceiver(mReceiver);
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            }
                        }
                    })
                    .subscribe(new Action1<String>() {
                        @Override
                        public void call(String s) {
                            try {
                                listener.onDecodeResult(DecodeResult.SUCESS, s);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Action1<Throwable>() {
                        @Override
                        public void call(Throwable throwable) {
                            int result;
                            if (throwable instanceof OperationCanceledException) {
                                result = DecodeResult.CANCEL;
                            } else if (throwable instanceof TimeoutException) {
                                result = DecodeResult.TIMEOUT;
                            } else {
                                result = DecodeResult.FAIL;
                            }
                            try {
                                listener.onDecodeResult(result, null);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            return DecodeResult.SUCESS;
        }

        @Override
        public void stopScan() throws RemoteException {
            if (subject != null) {
                subject.onError(new OperationCanceledException());
            }
        }
    }
}

package net.sourceforge.zbar.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.srt.decoder.DecodeResult;
import com.srt.decoder.Decoder;
import com.srt.decoder.DecoderConfigValues.SymbologyID;

/**
 * Created by xiaox on 2016/12/1.
 */

public class NewtologicDecodeModule implements DecodeModule {
    private final DecodeResult mDecodeResult;
    private final Decoder mDecoder;
    private boolean isInit = false;
    private final Context context;

    NewtologicDecodeModule(Context context) {
        this.context = context;
        mDecodeResult = new DecodeResult();
        mDecoder = new Decoder();
    }

    @Override
    public String decode(byte[] data, int width, int height) {
        if (!isInit) {
            int ret = mDecoder.InitDecoderEngine(width, height);
            if (ret != 1) return null;
            isInit = true;
            mDecoder.setDecodeSearchLimit(200);
            mDecoder.setDecodeAttemptLimit(400);


            /**********enable frequently used decode***************/
            mDecoder.enableSymbology(SymbologyID.EAN8);
            mDecoder.enableSymbology(SymbologyID.UPCE0);
            mDecoder.enableSymbology(SymbologyID.UPCE1);
            mDecoder.enableSymbology(SymbologyID.UPCA);
            mDecoder.enableSymbology(SymbologyID.EAN13);
            mDecoder.enableSymbology(SymbologyID.COMPOSITE);
            //mDecoder.enableSymbology(SymbologyID.INT25);
            mDecoder.enableSymbology(SymbologyID.RSS);//COMPOSITE=GS1_DATABAR=RSS.
            //mDecoder.enableSymbology(SymbologyID.RSS);//DEC_CODE_ID_GS1_DATABAR_EXP. though only enable RSS, but codeID will return different value of RSS/RSS_EXP/RSS_LIMITED
            mDecoder.enableSymbology(SymbologyID.CODABAR);
            mDecoder.enableSymbology(SymbologyID.CODE39);
            mDecoder.enableSymbology(SymbologyID.CODE93);
            mDecoder.enableSymbology(SymbologyID.CODE128);
            mDecoder.enableSymbology(SymbologyID.PDF417);
            mDecoder.enableSymbology(SymbologyID.QR);


            /**********enable rarely used decode***************/
            /**********whole newtologic decode and mask frequently used decode like QR**********/
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);

            //if(prefs.getBoolean(PreferencesActivity.KEY_USE_RARE_DECODE, true)) {//Note: this is a generate mask of whole decode.
            //    Log.d(Thread.currentThread().getStackTrace()[2].getMethodName(), "DDX# KEY_USE_RARE_DECODE");

                //enable single decode after set IS_USE_RARE_DECODE true.
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_AZTEC	 	, true))             mDecoder.enableSymbology(SymbologyID.AZTEC);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_CODE11	 	, true))             mDecoder.enableSymbology(SymbologyID.CODE11);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_CODE49		, true))             mDecoder.enableSymbology(SymbologyID.CODE49);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_DATAMATRIX	, true))             mDecoder.enableSymbology(SymbologyID.DATAMATRIX);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_INT25		, true))             mDecoder.enableSymbology(SymbologyID.INT25);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_MAXICODE	, true))             mDecoder.enableSymbology(SymbologyID.MAXICODE);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_MICROPDF	, true))             mDecoder.enableSymbology(SymbologyID.MICROPDF);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_OCR		, true))             mDecoder.enableSymbology(SymbologyID.OCR);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_POSTNET	, true))             mDecoder.enableSymbology(SymbologyID.POSTNET);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_ISBT		, true))             mDecoder.enableSymbology(SymbologyID.ISBT);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_BPO		, true))             mDecoder.enableSymbology(SymbologyID.BPO);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_CANPOST	, true))             mDecoder.enableSymbology(SymbologyID.CANPOST);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_AUSPOST	, true))             mDecoder.enableSymbology(SymbologyID.AUSPOST);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_IATA25		, true))             mDecoder.enableSymbology(SymbologyID.IATA25);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_CODABLOCK	, true))             mDecoder.enableSymbology(SymbologyID.CODABLOCK);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_JAPOST		, true))             mDecoder.enableSymbology(SymbologyID.JAPOST);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_PLANET		, true))             mDecoder.enableSymbology(SymbologyID.PLANET);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_DUTCHPOST	, true))             mDecoder.enableSymbology(SymbologyID.DUTCHPOST);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_MSI		, true))             mDecoder.enableSymbology(SymbologyID.MSI);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_TLCODE39	, true))             mDecoder.enableSymbology(SymbologyID.TLCODE39);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_TRIOPTIC	, true))             mDecoder.enableSymbology(SymbologyID.TRIOPTIC);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_CODE32		, true))             mDecoder.enableSymbology(SymbologyID.CODE32);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_STRT25		, true))             mDecoder.enableSymbology(SymbologyID.STRT25);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_MATRIX25	, true))             mDecoder.enableSymbology(SymbologyID.MATRIX25);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_PLESSEY	, true))             mDecoder.enableSymbology(SymbologyID.PLESSEY);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_CHINAPOST	, true))             mDecoder.enableSymbology(SymbologyID.CHINAPOST);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_KOREAPOST	, true))             mDecoder.enableSymbology(SymbologyID.KOREAPOST);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_TELEPEN	, true))             mDecoder.enableSymbology(SymbologyID.TELEPEN);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_CODE16K	, true))             mDecoder.enableSymbology(SymbologyID.CODE16K);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_POSICODE	, true))             mDecoder.enableSymbology(SymbologyID.POSICODE);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_COUPONCODE	, true))             mDecoder.enableSymbology(SymbologyID.COUPONCODE);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_USPS4CB	, true))             mDecoder.enableSymbology(SymbologyID.USPS4CB);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_IDTAG		, true))             mDecoder.enableSymbology(SymbologyID.IDTAG);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_LABEL		, true))             mDecoder.enableSymbology(SymbologyID.LABEL);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_GS1_128	, true))             mDecoder.enableSymbology(SymbologyID.GS1_128);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_HANXIN		, true))             mDecoder.enableSymbology(SymbologyID.HANXIN);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_GRIDMATRIX	, true))             mDecoder.enableSymbology(SymbologyID.GRIDMATRIX);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_POSTALS	, true))             mDecoder.enableSymbology(SymbologyID.POSTALS);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_POSTALS1	, true))             mDecoder.enableSymbology(SymbologyID.POSTALS1);
                if(prefs.getBoolean(PreferencesActivity.KEY_DECODE_BOLOGIES	, true))             mDecoder.enableSymbology(SymbologyID.BOLOGIES);
            //}
        }
        mDecoder.RunDecodeImage(data, mDecodeResult, width, height);
        return mDecodeResult.length > 0 ? new String(mDecodeResult.byteBarcodeData, 0, mDecodeResult.length) : null;
    }

    @Override
    public void close() {
        mDecoder.disconnectFromDecoder();
        isInit = false;
    }
}

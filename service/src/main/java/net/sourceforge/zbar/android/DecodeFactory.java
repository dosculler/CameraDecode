package net.sourceforge.zbar.android;

import android.content.Context;

import com.srt.decoder.Decoder;

/**
 * Created by xiaox on 2016/12/1.
 */

public class DecodeFactory {
    public static DecodeModule getDecodeModule(Context context) {
        //只有这一个方法来区别，根据初始化成功或是失败来区别
        Decoder decoder = new Decoder();
        int width = 1920;
        int height = 1080;
        int ret = decoder.InitDecoderEngine(width, height);
        decoder.disconnectFromDecoder();
        if (ret != 1) {
            return new ZbarDecodeModule();
        } else {
            return new NewtologicDecodeModule(context);
        }
    }
}

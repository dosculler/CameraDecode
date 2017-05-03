package net.sourceforge.zbar.android;

/**
 * Created by xiaox on 2016/12/1.
 */

public interface DecodeModule {
    public String decode(byte[] data, int width, int height);

    public void close();
}

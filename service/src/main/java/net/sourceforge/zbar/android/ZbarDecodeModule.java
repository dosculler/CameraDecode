package net.sourceforge.zbar.android;

import net.sourceforge.zbar.Config;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Symbol;

/**
 * Created by xiaox on 2016/12/1.
 */

public class ZbarDecodeModule implements DecodeModule {
    private ImageScanner scanner;

    ZbarDecodeModule() {
        scanner = new ImageScanner();
//        scanner.setConfig(0, Config.X_DENSITY, 3);
//        scanner.setConfig(0, Config.Y_DENSITY, 3);
        scanner.setConfig(0, Config.ENABLE, 1);
    }

    @Override
    public String decode(byte[] data, int width, int height) {
        Image barcode = new Image(width, height, "Y800");
        barcode.setData(data);
        int result = scanner.scanImage(barcode);
        barcode.destroy();
        if (result != 0) {
            String content = null;
            for (Symbol sym : scanner.getResults()) {
                content = sym.getData();
                if (sym.getData() != null) break;
            }
            return content;
        } else {
            return null;
        }
    }

    @Override
    public void close() {

    }
}

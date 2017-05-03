// CameraDecode.aidl
package com.nexgo.cameradecode.service;

// Declare any non-default types here with import statements
import com.nexgo.cameradecode.service.OnDecodeListener;
interface CameraDecode {

    void initScanner(in Bundle bundle);

    int startScan(int timeout, in OnDecodeListener listener);

    void stopScan();
}

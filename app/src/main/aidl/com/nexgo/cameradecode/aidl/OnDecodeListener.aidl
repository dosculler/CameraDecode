// OnDecodeListener.aidl
package com.nexgo.cameradecode.aidl;

// Declare any non-default types here with import statements

interface OnDecodeListener {
    void onDecodeResult(int retCode, in String data);
}

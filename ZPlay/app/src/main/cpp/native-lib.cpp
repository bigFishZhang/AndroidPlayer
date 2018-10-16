#include <jni.h>
#include <string>
#include "FFDemux.h"
#include "ZLog.h"
#include "IDecode.h"
#include "FFDecode.h"


class TestObs:public IObserver
{
public:
    void Update(ZData d)
    {
//        ZLOGI("TestObs update data size is %d ",d.size);
    }

};



extern "C" JNIEXPORT jstring

JNICALL
Java_zplay_zplay_MainActivity_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";

    TestObs *tobs = new TestObs();


    IDemux *de = new FFDemux();
    de->AddObs(tobs);
    de->Open("/sdcard/test.mp4");

    IDecode *vdecode = new FFDecode();
    vdecode->Open(de->GetVPara());

    de->Start();
    ZSleep(3000);
    de->Stop();
//    for (; ; ) {
//        ZData d = de->Read();
//        ZLOGI("read data size %d",d.size);
//    }


    return env->NewStringUTF(hello.c_str());
}

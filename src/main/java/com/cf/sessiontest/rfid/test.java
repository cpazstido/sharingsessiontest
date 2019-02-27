package com.cf.sessiontest.rfid;

import com.sun.jna.*;

interface CLibrary extends Library {
    CLibrary sdtapi = (CLibrary)
            Native.loadLibrary((Platform.isWindows() ? "D:\\project\\idea\\test\\src\\main\\resources\\dll\\sdtapi" : "c"), CLibrary.class);

    int InitComm(int port);

    int CloseComm();

    int Authenticate();

    int GetSAMIDToStr(byte[] samid);

    int ReadBaseInfos(byte[] Name, byte[] Gender, byte[] Folk, byte[] BirthDay, byte[] Code, byte[] Address, byte[] Agency, byte[] ExpireStart, byte[] ExpireEnd);

    int ReadBaseInfosPhoto(byte[] Name, byte[] Gender, byte[] Folk, byte[] BirthDay, byte[] Code, byte[] Address, byte[] Agency, byte[] ExpireStart, byte[] ExpireEnd, String Dir);

    int HID_BeepLED(boolean BeepON, boolean LEDON, int duration);

    int ReadBaseMsg(byte[] pMsg, int len);

    int IsFingerPrintDevice();

    int Routon_Mute(boolean isMute);
}


public class test {

    public static void main(String[] args) throws Exception {

        byte sid[] = new byte[37];
        byte name[] = new byte[31];
        byte sex[] = new byte[3];
        byte folk[] = new byte[10];
        byte birth[] = new byte[9];
        byte code[] = new byte[19];
        byte add[] = new byte[71];
        byte agency[] = new byte[31];
        byte expirestart[] = new byte[9];
        byte expireend[] = new byte[9];
        int ret = 0;
        int len = 0;

        byte pMsg[] = new byte[192];

        if (CLibrary.sdtapi.InitComm(1001) == 1) {
            System.out.println("connect OK! 请按Ctrl+C 退出程序");
            ret = CLibrary.sdtapi.GetSAMIDToStr(sid);
            if (ret != 1) {
                System.out.println("GetSAMIDToStr=" + ret);
                return;
            }
            System.out.println(new String(sid));
            ret = CLibrary.sdtapi.Routon_Mute(false);
            System.out.println(ret);

//            while (true) {
            ret = CLibrary.sdtapi.Authenticate();
            if (ret == 1) {
                System.out.println("======================================");

                //ret=CLibrary.sdtapi.ReadBaseInfosPhoto(name,sex,folk,birth,code,add,agency,expirestart,expireend,"d:\\photo");
                ret = CLibrary.sdtapi.ReadBaseInfos(name, sex, folk, birth, code, add, agency, expirestart, expireend);
                //ret=CLibrary.sdtapi.ReadBaseMsg(pMsg,len);
//                    ret = CLibrary.sdtapi.IsFingerPrintDevice();

                if (ret != 1) {
                    System.out.println("ReadBaseInfosPhoto=" + ret);
                    return;
                }
                //CLibrary.sdtapi.HID_BeepLED(true,true,50);
                //CLibrary.sdtapi.HID_BeepLED(false,false,50);
                //CLibrary.sdtapi.HID_BeepLED(true,true,50);

                System.out.println(new String(name, "gbk"));
                System.out.println(new String(sex, "gbk"));
                System.out.println(new String(folk, "gbk"));
                System.out.println(new String(birth, "gbk"));
                System.out.println(new String(code, "gbk"));
                System.out.println(new String(add, "gbk"));
                System.out.println(new String(agency, "gbk"));
                System.out.println(new String(expirestart, "gbk"));
                System.out.println(new String(expireend, "gbk"));
            }
            CLibrary.sdtapi.CloseComm();
//            }

        } else {
            System.out.println("connect error from java!");

        }
    }


}

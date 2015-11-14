package com.example.xie.talking;

/**
 * Created by xie on 2015/11/14.
 */
public final class   MsgType {
    public static final int SENDMSG = 0;
    public static final int NORMALMSG=100000;
    public static final int LINKMSG=200000;
    public static final int NEWSMSG=302000;
    public static final int TRAINMSG=305000;
    public static final int FLIGHTMSG=306000;
    public static final int COOKMSG=308000;

}
/*public enum  MsgType {
    SENDMSG(0),
    NORMALMSG(100000),
    LINKMSG(200000),
    NEWSMSG(302000),
    TRAINMSG(305000),
    FLIGHTMSG(306000),
    COOKMSG(308000);
    private int _value;

    private MsgType(int value)
    {
        _value = value;
    }

    public int value()
    {
        return _value;
    }
}*/

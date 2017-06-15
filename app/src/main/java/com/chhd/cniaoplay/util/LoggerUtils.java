package com.chhd.cniaoplay.util;

import com.chhd.cniaoplay.global.Config;
import com.chhd.cniaoplay.global.Constant;
import com.orhanobut.logger.Logger;

/**
 * Created by CWQ on 2016/11/2.
 */
public class LoggerUtils implements Constant {

    private static boolean isDebug = Config.isDebug;

    private LoggerUtils() {

    }

    public static void v(String message) {
        if (isDebug) {
            Logger.v(message);
        }
    }

    public static void d(String message) {
        if (isDebug) {
            Logger.d(message);
        }
    }

    public static void i(String message) {
        if (true) {
            Logger.i(message);
        }
    }

    public static void w(String message) {
        if (true) {
            Logger.w(message);
        }
    }

    public static void e(Throwable throwable) {
        if (true) {
            Logger.e(throwable, "error");
        }
    }

    public static void e(String message) {
        if (isDebug) {
            Logger.e(message);
        }
    }


}

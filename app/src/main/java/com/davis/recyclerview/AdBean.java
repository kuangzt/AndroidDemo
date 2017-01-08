package com.davis.recyclerview;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AdBean {
    public static final int STYLE1 = 1;
    public static final int STYLE2 = 2;
    @IntDef({STYLE1, STYLE2 })
    @Retention(RetentionPolicy.SOURCE)
    public @interface Style{

    }
    @Style
    public int style = STYLE1;
    public String title;
    public String content;
}

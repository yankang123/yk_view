package com.example.my;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

public class Viewdemo extends View {

    public Viewdemo(Context context){
        super(context);
    }
    public Viewdemo(Context context, AttributeSet attrs){
        super(context,attrs);
        TypedArray typedArray=context.obtainStyledAttributes(attrs,R.styleable.MyView);
        int colors=typedArray.getColor(R.styleable.MyView_yangkang_color,0xffff0000);
        setBackgroundColor(colors);
        typedArray.recycle();//typearray释放回收，用于后面复用
    }

}

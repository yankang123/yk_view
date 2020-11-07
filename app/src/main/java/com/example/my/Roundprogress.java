package com.example.my;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import java.util.jar.Attributes;


import kotlin.jvm.Synchronized;

public class Roundprogress extends View {
    private Paint paint;
    private  int roundColor;//圆环颜色
    private int roundProgressColor;//圆环进度颜色
    private  int textcolor ;//中间进度百分比的字符串颜色；
    private float textSize;//字体
    private float roundwidth;//圆环宽度
    private int max;//最大进度
    private int progress;//当前进度
    private boolean textisdisplay;//是否显示中间的进度
    private int style;
    public static final int STROKE=0;
    public  static final int FILL=1;

    public Roundprogress(Context context){
        this(context,null);
    }
    public Roundprogress(Context context, AttributeSet attrs){
        this(context,attrs,0);
    }
    public Roundprogress(Context context,AttributeSet attrs,int defStyle){
        super(context,attrs,defStyle);
     paint=new Paint();

        TypedArray  mTypeArray=context.obtainStyledAttributes(attrs,R.styleable.Roundattrs);
        roundColor=mTypeArray.getColor(R.styleable.Roundattrs_roundColor, Color.BLACK);
        roundProgressColor=mTypeArray.getColor(R.styleable.Roundattrs_roundColor,Color.GREEN);
        roundwidth=mTypeArray.getDimension(R.styleable.Roundattrs_roundWidth,60);
        textcolor=mTypeArray.getColor(R.styleable.Roundattrs_textColor,Color.BLACK);
        textSize=mTypeArray.getDimension(R.styleable.Roundattrs_textSize,100);
  max=  mTypeArray.getInteger(R.styleable.Roundattrs_max,100);
  textisdisplay=mTypeArray.getBoolean(R.styleable.Roundattrs_textIsDisplayable,true);
  style=mTypeArray.getInt(R.styleable.Roundattrs_style,0);
  mTypeArray.recycle();


    }
    public synchronized void setProgress(int progress){
        if(progress<0){
                throw new IllegalArgumentException("profress not less than 0");////////////////////////////请求测试
        }
        if(progress>max) {
            this.progress=max;
        }
        if(progress<=max){
            this.progress=progress;
            postInvalidate();//会重新绘制调用onDraw（）
        }

    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int center=getWidth()/2;
        int radius=(int)(center-roundwidth*2);

        paint.setStrokeWidth(roundwidth);
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);//设置空心/////////////请求测试
        paint.setAntiAlias(true);//消除锯齿
        canvas.drawCircle(center,center,radius,paint);

        paint.setStrokeWidth(0);
        paint.setTextSize(textSize);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(textcolor);
        paint.setTypeface(Typeface.DEFAULT_BOLD);//设置字体
        int percent = (int)(((float)progress / (float)max) * 100);
        float textwidth = paint.measureText(percent+"%");
        if(textisdisplay&&percent!=0&&style==STROKE){
            canvas.drawText(percent + "%",center-textwidth/2,center+textSize/2,paint);
        }

        paint.setStrokeWidth(roundwidth);
        paint.setColor(roundProgressColor);
        RectF oval  =new RectF(center - radius, center - radius, center+ radius, center + radius);
        switch (style){
            case STROKE:{
                paint.setStyle(Paint.Style.STROKE);
                paint.setStrokeWidth(18);
                paint.setColor(roundProgressColor);
                canvas.drawArc(oval, 0, 360 * progress / max, false, paint);  //根据进度画圆弧

                break;
            }
            case FILL:{
                paint.setStyle(Paint.Style.FILL_AND_STROKE);
                paint.setStrokeWidth(18);
                paint.setColor(Color.RED);

                if(progress !=0)

                    canvas.drawArc(oval, 0, 360 * progress / max, true, paint);  //根据进度画圆弧


                break;
            }
        }

    }


}

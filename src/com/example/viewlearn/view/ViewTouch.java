package com.example.viewlearn.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.FontMetricsInt;
import android.graphics.Rect;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.MeasureSpec;
import android.view.MotionEvent;
import android.view.View;

public class ViewTouch extends View implements OnGestureListener{
	public static String TAG="ViewTouch";
	private Context context;
	private GestureDetectorCompat detector;
	private boolean onTouchEvent;
	private Paint mPaint;
	private Rect bounds;
	public ViewTouch(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public ViewTouch(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		this.context=context;
		detector = new GestureDetectorCompat(context, this);

	}

	public ViewTouch(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		int rw = MeasureSpec.getSize(widthMeasureSpec);
		  int rh = MeasureSpec.getSize(heightMeasureSpec);
		 
		  Log.i(TAG,"onMeasure");
		  mPaint = new Paint();
		    mPaint.setAntiAlias(true);
		    mPaint.setColor(Color.parseColor("#1E88E5"));
		    mPaint.setTextSize(36);
		     bounds = new Rect();
		     String testString ="测试Touch事件测试";
		    mPaint.getTextBounds(testString, 0, testString.length(), bounds);
		    FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
		    
		    setMeasuredDimension(rw,fontMetrics.bottom-fontMetrics.top);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		Log.i(TAG,"onDraw");
		
		 String testString ="测试Touch事件";
		FontMetricsInt fontMetrics = mPaint.getFontMetricsInt();
		int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
		canvas.drawText(testString,getMeasuredWidth() / 2 - bounds.width() / 2, baseline, mPaint);
		Log.i(TAG,"getMeasuredHeight(): "+getMeasuredHeight()+"fontMetrics.leading: "+fontMetrics.leading+
				"fontMetrics.bottom: "+fontMetrics.bottom+"fontMetrics.top: "+fontMetrics.top
				+"fontMetrics.ascent: "+fontMetrics.ascent+"fontMetrics.descent: "+fontMetrics.descent
				);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		if(detector==null)
			detector = new GestureDetectorCompat(context, this);
		detector.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Log.i(TAG,"onDown");
		return false;
	}

	@Override
	public boolean onFling(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		Log.i(TAG,"onFling");
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Log.i(TAG,"onLongPress");
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		Log.i(TAG,"onScroll");
		return false;
		
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Log.i(TAG,"onShowPress");
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		Log.i(TAG,"onSingleTapUp");
		return false;
	}

}

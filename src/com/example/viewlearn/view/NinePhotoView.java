package com.example.viewlearn.view;

import java.util.ArrayList;

import com.example.viewlearn.R;
import com.example.viewlearn.R.drawable;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.support.v4.view.GestureDetectorCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class NinePhotoView extends ViewGroup {
	public static String TAG="NinePhotoView";
	private Context context;
	// horizontal space among children views
	int hSpace = 10;
	// vertical space among children views
	int vSpace = 10;

	// every child view width and height.
	int childWidth = 0;
	int childHeight = 0;
	int marigedp=8;
	// store images res id
	ArrayList<Integer> mImageResArrayList = new ArrayList<Integer>(9);
	private View addPhotoView;
    // 图片数组  
    private static final int[] ARR_IMAGES = {   
        R.drawable.sample_0,   
        R.drawable.sample_1,   
        R.drawable.sample_2,   
        R.drawable.sample_3,   
        R.drawable.sample_4,   
        R.drawable.sample_5,  
        R.drawable.sample_6,  
        R.drawable.sample_7,  
    };  
	public NinePhotoView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public NinePhotoView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.context=context;
		  addPhotoView = new View(context);
		  addView(addPhotoView);
	}
	public NinePhotoView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		   int count = getChildCount();
	        View child;
	        Log.i(TAG, count + "");       
	        //根据每个子View的设定大小，设置每一个子View的位置和大小
	        for(int i = 0 ;i < count;i++) {
	            child = getChildAt(i);
	            int left=(i%3)*(child.getMeasuredWidth()+marigedp);
	            int top=(i/3)*(child.getMeasuredHeight()+marigedp);
	            //设置每一个childview的具体位置，左边距，上边距，右边距，底部边距
	            child.layout(left,
	            		top,
	            		left+child.getMeasuredWidth(),
	            		top+child.getMeasuredHeight());
	            //当是最后一个view时，默认设置为“+号”的view，并添加监听
	            if(i==count-1&&count<ARR_IMAGES.length){
	            	child.setBackgroundResource(R.drawable.add);
	            	child.setOnClickListener(new OnClickListener() {					
						@Override
						public void onClick(View arg0) {
							// TODO Auto-generated method stub
							final CharSequence[] items = { "Take Photo", "Photo from gallery" };
							AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
							  builder.setItems(items, new DialogInterface.OnClickListener() {
							      @Override
							      public void onClick(DialogInterface arg0, int arg1) {
							    	  //添加新的view，要求父类view重绘，自身重绘，自身重绘时当前view的位置放置新的图片，添加的新 view显示“+号”图
							    	  View newChild = new View(getContext());
							    	  //在addView的实现中会调用requestLayout();invalidate();
							          addView(newChild);
							      }
							  });
							  builder.show();
						}
					});
	            }
	            else{
	            	child.setBackgroundResource(ARR_IMAGES[i]);
	            }	            
	        }
	}

	@Override
	protected void measureChild(View child, int parentWidthMeasureSpec,
			int parentHeightMeasureSpec) {
		// TODO Auto-generated method stub
		super.measureChild(child, parentWidthMeasureSpec, parentHeightMeasureSpec);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		//在布局文件中设定，width：match_parent，height：wrap_content
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		//获取父view的最宽大显示限制
		int rw = MeasureSpec.getSize(widthMeasureSpec);
		int rh = MeasureSpec.getSize(heightMeasureSpec);
		//这这两个参数的单位都是px，如果xml中设置的是dp，这里会进行换算。
		Log.i(TAG,"由于是wrap_content，参数HiegetMeasureSpec是AT_MOST加上父view最大剩余空间: rh： "+ rh+ " rw: "+rw);
		//设置间隙为两个10 
		int childwith=(rw-20)/3;
		int childheight=childwith;
		int childCount = this.getChildCount();
		for (int i = 0; i < childCount; i++) {
			View child = this.getChildAt(i);
			this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
			LayoutParams lParams =(LayoutParams)child.getLayoutParams();
		    lParams.height=childheight;
		    lParams.width=childwith;
		    
		}
		setMeasuredDimension(rw,((childCount-1)/3+1)*(childheight+marigedp));
	}
}

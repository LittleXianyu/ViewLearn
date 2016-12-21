package com.example.viewlearn.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class FourButtonViewGroup extends ViewGroup{
public static String TAG="FourButtonViewGroup";
	public FourButtonViewGroup(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public FourButtonViewGroup(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		
	}

	public FourButtonViewGroup(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
	       int height = 0;
	        int count = getChildCount();
	        View child;
	        
	        //根据每个子View的设定大小，设置每一个子View的位置和大小
	        for(int i = 0 ;i < count;i++) {
	            child = getChildAt(i);
	            //疑问点？这个getMeasuredWidth获取的数据是这个view的自定义长宽+padding值？
	            Log.i(TAG,"child.getMeasuredWidth(): "+child.getMeasuredWidth()+"child.getWidth()  "+child.getWidth());
	            child.layout(0, height, child.getMeasuredWidth(),height +  child.getMeasuredHeight());
	            height += child.getMeasuredHeight();
	        }
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int rw = MeasureSpec.getSize(widthMeasureSpec);
		  int rh = MeasureSpec.getSize(heightMeasureSpec);
		  int mode = MeasureSpec.getMode(heightMeasureSpec);
		  switch (mode){
		  case MeasureSpec.AT_MOST:
			  break;
		  case MeasureSpec.EXACTLY:
			  break;
		  case MeasureSpec.UNSPECIFIED:
			  break;
		  }
		  //绘制分为两部分，第一部分测绘自身的view容器大小
		setMeasuredDimension(rw,100);
		//第二部分是，测量每个子View的大小。
		//this.measureChildren(widthMeasureSpec, heightMeasureSpec);这段代码等价于下面的遍历
		int childCount = this.getChildCount();
		  for (int i = 0; i < childCount; i++) {
		      View child = this.getChildAt(i);
		      //测量的作用？给哪些值赋值？和getMeasuredWidth是否有关，为什么把父类的容器大小参数传入？？
		      //答：按照步骤，measure，layout，draw，父viewgroup的测量除了自身大小测量中，会调用调用子类的测量。
		      //实现代码：child.measure(childWidthMeasureSpec, childHeightMeasureSpec);
		      this.measureChild(child, widthMeasureSpec, heightMeasureSpec);
		      //直接设定子控件的测量后的长和宽。
		      Log.i(TAG,"child.getMeasuredWidth()======: "+child.getMeasuredWidth());
		      LayoutParams lParams = (LayoutParams) child.getLayoutParams();
		      lParams.height=20;
		      lParams.width=50;
		  }
	}
	



}

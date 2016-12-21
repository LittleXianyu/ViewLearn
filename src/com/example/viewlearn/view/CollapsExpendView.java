package com.example.viewlearn.view;

import com.example.viewlearn.R;
import com.example.viewlearn.R.drawable;

import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/*
 * 自定义折叠View，添加标题栏，折叠显示图片，在点击右边折叠按钮会
 */
@SuppressLint("NewApi")
public class CollapsExpendView extends ViewGroup{
	private static String TAG="CollapsExpendView";
    // 图片数组  
    private static final int[] ARR_IMAGES = {   
        R.drawable.sample_0,   
        R.drawable.sample_1,   
        R.drawable.sample_2,   
    };
    private TextView textview[];
    private ImageButton button[];
    private ImageView imageView[];
    private Context context;
    private Boolean ifexpend=false;
    private int linearLayout_height,linearLayout_width;
    private static final String[] names={
    	"狗111111",
    	"狗222222",
    	"狗333333"
    };
    private LinearLayout linearLayout[];
	public CollapsExpendView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.context=context;
		animatorSet = new AnimatorSet(); 
		addViewFirst();
	}

	public CollapsExpendView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
		this.context=context;
	}

	public CollapsExpendView(Context context) {
		this(context,null);
		this.context=context;
		// TODO Auto-generated constructor stub
	}
	public void addViewFirst(){
		Log.i(TAG,"addViewFirst");
		linearLayout = new LinearLayout[3];
		textview=new TextView[3];
		button= new ImageButton[3];
		imageView=new ImageView[3];
		for(int i=0;i<3;i++){
			linearLayout[i]=new LinearLayout(context);
			imageView[i]=new ImageView(context);
			//设置宽高大小,这个时候只是在构造函数中执行，还没有到View的Measure阶段，调用get方法获取到的高和宽都是0；
			LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
			linearLayout[i].setOrientation(LinearLayout.HORIZONTAL);
			//给横向的LinearLayout添加三个view
			setContextView(linearLayout[i],i);
			linearLayout[i].setLayoutParams(params);
			imageView[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
			//整体的viewGroup添加纵向的三个view
			this.addView(linearLayout[i]);
			this.addView(imageView[i]);
			this.addView(getDividerView());
		}	
	}
	public View getDividerView(){
		ImageView divider=new ImageView(context);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		divider.setLayoutParams(params);
		divider.setBackgroundResource(R.drawable.category_list_divider);
		return divider;
	}
	@Override
	protected void onLayout(boolean arg0, int arg1, int arg2, int arg3, int arg4) {
		// TODO Auto-generated method stub
		int count = getChildCount();     
		int sumHeight=0;
        //根据每个子View的设定大小，设置每一个子View的位置
        for(int i = 0 ;i < count;i++) {
        	View child= getChildAt(i);
        	child.layout(0, sumHeight,child.getMeasuredWidth(), sumHeight+child.getMeasuredHeight());
        	sumHeight+=child.getMeasuredHeight();
        }
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
	}
/*
 * 父视图可能在它的子视图上调用一次以上的measure(int,int)方法。
 * 例如，父视图可以使用unspecified dimensions来将它的每个子视图都测量一次来算出它们到底需要多大尺寸，
 * 如果所有这些子视图没被限制的尺寸的和太大或太小，那么它会用精确数值再次调用measure()
 * （也就是说，如果子视图不满意它们获得的区域大小，那么父视图将会干涉并设置第二次测量规则）。
 * @see android.view.View#onMeasure(int, int)
 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		Log.i(TAG,"call -?onMeasure");
		//获取父view的最宽大显示限制
		int rw = MeasureSpec.getSize(widthMeasureSpec);
		int rh = MeasureSpec.getSize(heightMeasureSpec);
		Log.i(TAG,"rw: "+rw+"rh: "+rh);
		int count=this.getChildCount();
		int sumHeight=0;
		View child=null;
		
		for (int i = 0; i < count; i++) {
			child = this.getChildAt(i);
			Log.i(TAG,"child.getWidth();》》》》》  "+child.getWidth()+" "+child.getLayoutParams().width+" "+child.getMeasuredWidth());
			this.measureChild(child, widthMeasureSpec, heightMeasureSpec); 
			//在第一次测量中，会使用UNSPECIFIED测量子View期望的size,所以在自定义viewgroup中，
			//调用Measurechild之后才会有getMeasureHeight和getMeasureWidth的值
			Log.i(TAG,"child.getWidth();》》》》》  "+child.getWidth()+" "+child.getLayoutParams().width+" "+child.getMeasuredWidth());
//			Log.i(TAG,"child.getHeight();=====  "+child.getHeight()+" "+child.getLayoutParams().height+" "+child.getMeasuredHeight());
//		    View v=child.getChildAt(0);
//		    Log.i(TAG,"v.getWidth();》》》》》  "+v.getWidth()+" "+v.getLayoutParams().width+" "+v.getMeasuredWidth());
			sumHeight+=child.getMeasuredHeight();
		}
		setMeasuredDimension(rw, sumHeight);
	}
	public void setContextView(LinearLayout layout,int i){
		textview[i] =new TextView(context);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,88);
		textview[i].setLayoutParams(params);
		textview[i].setText(names[i]);
		textview[i].setTextSize(32);
		layout.addView(textview[i],params);
		
	
		LayoutParams params2= new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
		layout.addView(new View(context),params2);
		
//		int height = textview.getLayoutParams().height;
//		int width = textview.getLayoutParams().width;
//		Log.i(TAG,"height: "+height+"width: "+width);
		button[i]=new ImageButton(context);
		LayoutParams params_button = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
		button[i].setLayoutParams(params_button);
		button[i].setBackgroundResource(R.drawable.btn_alarms_more);	
		button[i].setOnClickListener(getButtonOnclickListener(button[i],i));
		layout.addView(button[i]);	
	}
	private AnimatorSet animatorSet; 
	
	private OnClickListener getButtonOnclickListener(final ImageButton button, final int i) {
		// TODO Auto-generated method stub
		return new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if(ifexpend==false){//表示是收缩的，点击后需要展开
					button.setBackgroundResource(R.drawable.btn_alarms_up);	
					ifexpend=true;
					imageView[i].setBackgroundResource(ARR_IMAGES[i]);
					CollapsExpendView.this.requestLayout();
					CollapsExpendView.this.invalidate();
				}
				else{
					button.setBackgroundResource(R.drawable.btn_alarms_more);	
					ifexpend=false;
					imageView[i].setBackground(null);
					CollapsExpendView.this.requestLayout();
					CollapsExpendView.this.invalidate();
				}
					
			}
		};
	}
	



}

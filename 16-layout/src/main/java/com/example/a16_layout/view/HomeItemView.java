package com.example.a16_layout.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.a16_layout.CVUtilsKt;
import com.example.a16_layout.R;

import static com.example.a16_layout.CVUtilsKt.dp2px;

public class HomeItemView extends LinearLayout {
    private TextView tv;
    private View view;
    private ImageView icon;
    private Context context;
    private int WINDOW_WIDTH;

    public HomeItemView(Context context) {
        super(context);
        WINDOW_WIDTH = CVUtilsKt.getWINDOW_WIDTH();
        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.fragment_home_relist_part, this, true);
        icon = view.findViewById(R.id.fh_icon);
        tv = view.findViewById(R.id.fh_name);
//        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) tv.getLayoutParams();
//        params.width = WINDOW_WIDTH/5;
//        params.height = WINDOW_WIDTH/5;
//        tv.setLayoutParams(params);
        tv.setText("卡卡罗特");
    }

    /**
     * 只要这个layout不是滑动的重写这个方法返回false，能提高 ACTION_DOWN 的反应；
     * 因为 View 在处理 ACTION_DOWN 的时候会判断是否在滑动控件内，如果在 就会延迟处理100ms，主要是为了区分用户接下来是在滑动还是点击
     * @return
     */
    @Override
    public boolean shouldDelayChildPressedState() {
        return false;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        setMeasuredDimension(resolveSizeAndState(WINDOW_WIDTH/5,widthMeasureSpec,0),
//                resolveSizeAndState(WINDOW_WIDTH/5,heightMeasureSpec,0));
//        setMeasuredDimension(WINDOW_WIDTH/5,WINDOW_WIDTH/5);
        Log.e("itemWidth:",MeasureSpec.getSize(widthMeasureSpec)+"");
//        for (int i = 0; i < getChildCount(); i++) {
//            getChildAt(i).measure(widthMeasureSpec, heightMeasureSpec);
//        }
    }
//
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//        super.onLayout(changed, l, t, r, b);
//        Log.e("HomeItemView","width:"+getWidth()+"\nheight:"+getHeight());
//    }
}

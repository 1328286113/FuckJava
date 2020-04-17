package com.example.a16_layout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.example.a16_layout.CVUtilsKt;

import java.util.ArrayList;
import java.util.List;


public class UICollectionLayout extends ViewGroup {
    private static final String TAG = "UICollectionLayout";
    private static final int WINDOW_WIDTH = CVUtilsKt.getWINDOW_WIDTH();
    private Context context;
    private List<Rect> childbounds = new ArrayList<>();
    private int LINEMAX = 2;
    private int LINECOUNT = 5;

    public UICollectionLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
//        TypedArray typedArray = context.obtainStyledAttributes(attrs, new int[]{R.attr.Rows, R.attr.Columns});
//        LINEMAX = typedArray.getInteger(0, 2);
//        LINECOUNT = typedArray.getInteger(1, 5);
//        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int heightUsed = 0;
        int widthUsed = 0;
        int lineheightUsed = 0;
        int linewidthUsed = 0;
        int childWidth = WINDOW_WIDTH/LINECOUNT;
        for (int i = 0; i < getChildCount(); i++) {
            View childview = getChildAt(i);
            measureChildWithMargins(childview, childWidth, 0, heightMeasureSpec, heightUsed);
            Rect childbound;
            if (childbounds.size() <= i) {
                childbound = new Rect();
                childbounds.add(childbound);
            } else {
                childbound = childbounds.get(i);
            }
            if (i < LINEMAX * LINECOUNT) {
                linewidthUsed = childWidth * (i % LINECOUNT);
                lineheightUsed = childview.getMeasuredHeight() * (i / LINECOUNT);
                widthUsed = i < LINECOUNT ? childWidth * (i + 1) : WINDOW_WIDTH;
                heightUsed = (i / LINECOUNT + 1) * childview.getMeasuredHeight();
            } else {
                linewidthUsed = i % (LINEMAX * LINECOUNT) / LINEMAX * childWidth + WINDOW_WIDTH;
                lineheightUsed = i % (LINEMAX * LINECOUNT) % LINEMAX * childview.getMeasuredHeight();
                widthUsed = (i + 1) / (LINEMAX * LINECOUNT) * WINDOW_WIDTH +
                        (i + 1) % (LINEMAX * LINECOUNT) / LINEMAX * childWidth;
            }
            childbound.set(linewidthUsed, lineheightUsed,
                    linewidthUsed + childWidth, lineheightUsed + childview.getMeasuredHeight());
//            childview.measure(childWidth, heightMeasureSpec);
        }
        setMeasuredDimension(widthUsed, heightUsed);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        for (int i = 0; i < getChildCount(); i++) {
            Rect childbound = childbounds.get(i);
            getChildAt(i).layout(childbound.left, childbound.top, childbound.right, childbound.bottom);
        }
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(context, attrs);
    }

    @Override
    public void addView(View child, int index) {
        if (child == null) {
            throw new IllegalArgumentException("Cannot add a null child view to a ViewGroup");
        }
        LayoutParams params = getLayoutParams();
        if (params == null) {
            params = generateDefaultLayoutParams();
            if (params == null) {
                throw new IllegalArgumentException("generateDefaultLayoutParams() cannot return null");
            }
        }
        addView(child, index, params);
    }
}

package com.shenyong.aabills.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.sddy.utils.DimenUtils;
import com.shenyong.aabills.R;

public class CircleStatisticView extends View {

    public CircleStatisticView(Context context) {
        this(context, null);
    }

    public CircleStatisticView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleStatisticView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private int mViewSize;
    private Paint mPaint;
    private int mStrokeWidth;

    private void init(Context context) {
        mViewSize = DimenUtils.dp2px(90);
        Resources res = context.getResources();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokeWidth = res.getDimensionPixelSize(R.dimen.margin_large);
        mPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mViewSize, mViewSize);
    }

    private int[] colors = {0xFF2A82E4, 0xFF54BFC0, 0xFF5EBE67, 0xFFF4CC49, 0xFFE05667};
    private double[] amounts = {60, 20, 20};

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        int offset = mStrokeWidth / 2;
        double[] percents = getPercents(amounts);
        float startAngle = -90;
        for (int i = 0; i < percents.length; i++) {
            mPaint.setColor(colors[i]);
            float sweepAngle = (float) (360 * percents[i]);
            canvas.drawArc(offset, offset, getWidth() - offset, getHeight() - offset, startAngle, sweepAngle-2, false, mPaint);
            startAngle += sweepAngle;
        }
    }

    private double[] getPercents(double[] amounts) {
        double total = 0;
        for (Double d : amounts) {
            total += d;
        }
        double[] percents = new double[amounts.length];
        for (int i = 0; i < amounts.length; i++) {
            percents[i] = amounts[i] / total;
        }
        return percents;
    }
}

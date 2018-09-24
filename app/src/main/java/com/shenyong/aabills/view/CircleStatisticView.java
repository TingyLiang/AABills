package com.shenyong.aabills.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.text.TextUtils;
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
    private TextPaint mTextPaint;
    private int mStrokeWidth;
    private String mTotal;
    // 0 ~ 1.0
    public double[] mPercents;

    private void init(Context context) {
        mViewSize = DimenUtils.dp2px(90);
        Resources res = context.getResources();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mStrokeWidth = res.getDimensionPixelSize(R.dimen.margin_large);
        mPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(res.getColor(R.color.text_color_black));
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(res.getDimensionPixelSize(R.dimen.text_size_default));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(mViewSize, mViewSize);
    }

    private int[] colors = {0xFF2A82E4, 0xFF54BFC0, 0xFF5EBE67, 0xFFF4CC49, 0xFFE05667};
    private double[] mAmounts = {60, 20, 20};

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mPercents == null) {
            return;
        }
        int offset = mStrokeWidth / 2;
        float startAngle = -90;
        mPaint.setStrokeWidth(mStrokeWidth);
        for (int i = 0; i < mPercents.length; i++) {
            mPaint.setColor(colors[i % colors.length]);
            float sweepAngle = (float) (360 * mPercents[i]);
            canvas.drawArc(offset, offset, getWidth() - offset, getHeight() - offset, startAngle, sweepAngle-2, false, mPaint);
            startAngle += sweepAngle;
        }
        if (!TextUtils.isEmpty(mTotal)) {
            int cx = getWidth() / 2;
            Paint.FontMetrics fm = mTextPaint.getFontMetrics();
            float h = fm.descent - fm.ascent;
            int cy = (int) (getHeight() / 2 + h / 2);
            canvas.drawText(mTotal, cx, cy, mTextPaint);
        }
        mPaint.setStrokeWidth(4);
        canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, mPaint);
    }

    public void setCenterText(String text) {
        mTotal = text;
        invalidate();
    }

    public void setData(@NonNull double[] percents) {
        mPercents = percents;
        invalidate();
    }
}

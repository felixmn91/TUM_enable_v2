package enable.tum.tum_enable_app;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * TODO: document your custom view class.
 */
public class FeedbackBarView extends View
{
    private static final String TAG = "FeedbackBarView";
    private Paint paint;

    private float width;
    private float height;
    private float contentWidth;
    private float contentHeight;

    private float paddingLeft;
    private float paddingTop;
    private float paddingRight;
    private float paddingBottom;

    private float barWidthLeft;
    private float barWidthRight;

    private float barHeightTop;
    private float barHeightBottom;
    private float barHeightThresholdValue;
    private float barHeightActualValue;
    private float barHeightMiddleValue;

    private float spectrumMaxValue;
    private float spectrumMiddleValue;
    private float spectrumThresholdValue;
    private float spectrumActualValue;

    private float scale;


    public FeedbackBarView(Context context)
    {
        super(context);

        paint = new Paint();
    }

    public FeedbackBarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);

        paint = new Paint();

        scale = getResources().getDisplayMetrics().density;
        spectrumActualValue = 0f;
    }

    public void calculateValues()
    {

    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        // Initialize measueres to draw all elements
        width = getWidth();
        height = getHeight();
        paddingLeft = getPaddingLeft();
        paddingTop = getPaddingTop();
        paddingRight = getPaddingRight();
        paddingBottom = getPaddingBottom();
        contentWidth = width - paddingRight - paddingLeft;
        contentHeight = height - paddingTop - paddingBottom;

        barWidthLeft = paddingLeft + contentWidth / 4f;
        barWidthRight = width - paddingRight - (contentWidth / 4f);
        barHeightTop = 0f + paddingTop;
        barHeightBottom = height - paddingBottom;
        barHeightThresholdValue = barHeightBottom - (contentHeight * spectrumThresholdValue / spectrumMaxValue);
        barHeightActualValue = barHeightBottom - (contentHeight * spectrumActualValue / spectrumMaxValue);
        barHeightMiddleValue = barHeightBottom - (contentHeight * spectrumMiddleValue/ spectrumMaxValue);

        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(barWidthLeft, barHeightTop, barWidthRight, barHeightBottom, paint);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2f);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(barWidthLeft, barHeightTop, barWidthRight, barHeightBottom, paint);

        if (spectrumActualValue <= spectrumThresholdValue)
        {
            paint.setColor(ContextCompat.getColor(getContext(), R.color.darkgreen));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(barWidthLeft, barHeightActualValue, barWidthRight, barHeightBottom, paint);


        } else if (spectrumActualValue > spectrumThresholdValue && spectrumActualValue <= spectrumMiddleValue){

            paint.setColor(ContextCompat.getColor(getContext(), R.color.darkgreen));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(barWidthLeft, barHeightThresholdValue, barWidthRight, barHeightBottom, paint);

            paint.setColor(ContextCompat.getColor(getContext(), R.color.orange));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(barWidthLeft, barHeightActualValue, barWidthRight, barHeightThresholdValue, paint);


        }
        else if (spectrumActualValue > spectrumMiddleValue){

            paint.setColor(ContextCompat.getColor(getContext(), R.color.darkgreen));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(barWidthLeft, barHeightThresholdValue, barWidthRight, barHeightBottom, paint);

            paint.setColor(ContextCompat.getColor(getContext(), R.color.orange));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(barWidthLeft, barHeightMiddleValue, barWidthRight, barHeightThresholdValue, paint);

            paint.setColor(ContextCompat.getColor(getContext(), R.color.darkred));
            paint.setStyle(Paint.Style.FILL);
            canvas.drawRect(barWidthLeft, barHeightActualValue, barWidthRight, barHeightMiddleValue, paint);
        }

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2f);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(barWidthLeft, barHeightActualValue, barWidthRight, barHeightBottom, paint);

        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(2f);
        canvas.drawLine(3 * (width / 16f), barHeightThresholdValue, width - 3 * (width / 16f), barHeightThresholdValue, paint);

        paint.setStrokeWidth(1f);
        float strokeHeight75PercentLine = barHeightTop + (contentHeight / 4f);
        canvas.drawLine(barWidthLeft, strokeHeight75PercentLine, barWidthRight, strokeHeight75PercentLine, paint);

        float strokeHeight50PercentLine = barHeightBottom - (contentHeight / 2f);
        canvas.drawLine(barWidthLeft, strokeHeight50PercentLine, barWidthRight, strokeHeight50PercentLine, paint);

        float strokeHeight25PercentLine = barHeightBottom - (contentHeight / 4f);
        canvas.drawLine(barWidthLeft, strokeHeight25PercentLine, barWidthRight, strokeHeight25PercentLine, paint);


        TextPaint textPaint = new TextPaint();
        textPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.myFontSize));
        textPaint.setColor(Color.BLACK);
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();

        String yourLimit = "Goal";
        String labelThresholdValue = "" + (int) spectrumThresholdValue;
        float textWidthValue = textPaint.measureText(labelThresholdValue);
        float textWidthText = textPaint.measureText(yourLimit);
        float textHeight = (-1) * (fontMetrics.ascent);
        float offsetToLeftSideOfBar = (int) (5f * scale + 0.5f);
        canvas.drawText(labelThresholdValue, 3 * (width / 16f) - textWidthValue - offsetToLeftSideOfBar, barHeightThresholdValue + textHeight / 2f, textPaint);
        canvas.drawText(yourLimit, 3 * (width / 16f) - textWidthValue - offsetToLeftSideOfBar, barHeightThresholdValue - textHeight / 2f, textPaint);

        String label0PercentLine = "0";
        textWidthValue = textPaint.measureText(label0PercentLine);
        canvas.drawText(label0PercentLine, barWidthLeft - textWidthValue - offsetToLeftSideOfBar, barHeightBottom - textHeight / 3f, textPaint);

//        String label25PercentLine = "" + (int) (0.25 * spectrumMaxValue);
//        textWidth = textPaint.measureText(label25PercentLine);
//        canvas.drawText(label25PercentLine, barWidthLeft - textWidth - offsetToLeftSideOfBar, strokeHeight25PercentLine + textHeight / 2f, textPaint);
//
//        String label50PercentLine = "" + (int) (0.5 * spectrumMaxValue);
//        textWidth = textPaint.measureText(label50PercentLine);
//        canvas.drawText(label50PercentLine, barWidthLeft - textWidth - offsetToLeftSideOfBar, strokeHeight50PercentLine + textHeight / 2f, textPaint);
//
//        String label75PercentLine = "" + (int) (0.75 * spectrumMaxValue);
//        textWidth = textPaint.measureText(label75PercentLine);
//        canvas.drawText(label75PercentLine, barWidthLeft - textWidth - offsetToLeftSideOfBar, strokeHeight75PercentLine + textHeight / 2f, textPaint);

        String label100PercentLine = "" + (int) spectrumMaxValue;
        textWidthValue = textPaint.measureText(label100PercentLine);
        canvas.drawText(label100PercentLine, barWidthLeft - textWidthValue - offsetToLeftSideOfBar, barHeightTop + textHeight / 2f, textPaint);
    }

    public void setSpectrumThresholdValue(int spectrumThresholdValue)
    {
        this.spectrumThresholdValue = spectrumThresholdValue;
    }

    public void setSpectrumMaxValue(int spectrumMaxValue)
    {
        this.spectrumMaxValue = spectrumMaxValue;
    }

    public void setSpectrumMiddleValue (int spectrumMiddleValue){
        this.spectrumMiddleValue = spectrumMiddleValue;
    }

    public void setSpectrumActualValue(float spectrumActualValue)
    {
        if (spectrumActualValue > spectrumMaxValue)
        {
            this.spectrumActualValue = spectrumMaxValue;
        } else if (spectrumActualValue >= 0 && spectrumActualValue <= spectrumMaxValue)
        {
            this.spectrumActualValue = spectrumActualValue;
        }
        invalidate();
    }
}

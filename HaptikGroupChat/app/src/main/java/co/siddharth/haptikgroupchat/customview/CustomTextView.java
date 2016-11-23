package co.siddharth.haptikgroupchat.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by siddharth on 22/11/16.
 */

public class CustomTextView extends AppCompatTextView {


    public CustomTextView(Context context) {
        super(context);

        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto/Medium.ttf");
        this.setTypeface(face);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        Typeface face = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto/Regular.ttf");
        Typeface typeface = getTypeface();
        if (typeface != null) {
            if (typeface.getStyle() == Typeface.BOLD) {
                face = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto/Medium.ttf");
            } else if (typeface.getStyle() == Typeface.NORMAL) {
                face = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto/Regular.ttf");
            }
        }

        this.setTypeface(face);
    }

    public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }
}

package widget;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;


/**
 * Created by 大灯泡 on 2015/10/30.
 * 一个自动缩放字体的textView
 */
public class SelfAdaptionTextView extends TextView {
    private Paint textPaint;//字体笔刷
    private float maxTextSize;//可完整显示字体的最大字号

    public SelfAdaptionTextView(Context context) {
        this(context, null);
    }

    public SelfAdaptionTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelfAdaptionTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setLines(1);
        this.setSingleLine();
        textPaint = new Paint();
        textPaint.set(this.getPaint());
    }

    /** 用于计算可以显示所有文字的适应字号 */
    private void calculateTextSze(String text, int textWidth) {
        if (text != null && textWidth > 0) {
            maxTextSize = getTextSize();
            // 文字的宽度总和
            float characterWidthSum = 0;
            // 获取字体显示的范围
            int charactersWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();
           /* // 计算最大的字号
            maxTextSize=charactersWidth/text.length();
            */
            // 计算所有字体的准确宽度
            char[] textChars = text.toCharArray();
            characterWidthSum = textPaint.measureText(textChars, 0, textChars.length);
            while (characterWidthSum > textWidth && maxTextSize > 0) {
                // 超过
                maxTextSize--;
                this.setTextSize(maxTextSize);
                textPaint.set(this.getPaint());
                characterWidthSum = textPaint.measureText(textChars, 0, textChars.length);
            }
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        calculateTextSze(text.toString(), this.getWidth());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w != oldw) calculateTextSze(this.getText().toString(), w);
    }
}




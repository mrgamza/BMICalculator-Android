package mrgamza.util;

import mrgamza.BMICalculator.R;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

public class ViewUtil
{
	public static View getInflateTextView(Context context, int resId, int textId, String text)
	{
		final View xView = View.inflate(context, resId, null);
		TextView xTextView = (TextView)xView.findViewById(R.id.text);
		xTextView.setText(text);
		
		return xView;
	}
	
	public static View getInflateTextView(Context context, int resId, int textId, int text)
	{
		return getInflateTextView(context, resId, textId, context.getResources().getString(text));
	}
}

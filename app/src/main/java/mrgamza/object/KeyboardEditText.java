package mrgamza.object;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

public class KeyboardEditText extends EditText
{
	public KeyboardEditText(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		
		initialization(context);
	}
	
	public KeyboardEditText(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		initialization(context);
	}
	
	public KeyboardEditText(Context context)
	{
		super(context);
		
		initialization(context);
	}
	
	private void initialization(Context context)
	{
		;;;
	}
	
	@Override
	public boolean onKeyPreIme(int keyCode, KeyEvent event)
	{
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN)
		{
			return true;
		}
		
		return super.onKeyPreIme(keyCode, event);
	}
}

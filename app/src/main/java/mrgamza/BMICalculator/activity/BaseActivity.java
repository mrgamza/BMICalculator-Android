package mrgamza.BMICalculator.activity;

import android.app.Activity;

public class BaseActivity extends Activity
{
	protected String[] getStringArray(int id)
	{
		return getResources().getStringArray(id);
	}
}

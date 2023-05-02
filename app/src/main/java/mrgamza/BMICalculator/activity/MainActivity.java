package mrgamza.BMICalculator.activity;

import mrgamza.BMICalculator.R;
import mrgamza.BMICalculator.bean.BMIBean;
import mrgamza.object.KeyboardEditText;
import mrgamza.util.ViewUtil;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener, View.OnKeyListener
{
	private static final int EDITTEXT_HEIGHT = R.id.height;
	private static final int EDITTEXT_WEIGHT = R.id.weight;
	private static final int BUTTON_COMPUTE = R.id.compute;
	private static final float BMIs[] = {0.0f, 18.5f, 23.0f, 25.0f, 30.0f}; 
	
	private KeyboardEditText xEditText_Height;
	private KeyboardEditText xEditText_Weight;
	private Button xButton_Compute;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);
		
		xEditText_Height = (KeyboardEditText)findViewById(EDITTEXT_HEIGHT);
		xEditText_Weight = (KeyboardEditText)findViewById(EDITTEXT_WEIGHT);
		xButton_Compute = (Button)findViewById(BUTTON_COMPUTE);
		
		xEditText_Height.setOnKeyListener(this);
		xEditText_Weight.setOnKeyListener(this);
		xButton_Compute.setOnClickListener(this);
	}

	@Override
	public void onClick(final View v)
	{
		switch(v.getId())
		{
			case BUTTON_COMPUTE :
			{
				final String height = xEditText_Height.getText().toString().trim();
				final String weight = xEditText_Weight.getText().toString().trim();
				if(TextUtils.isEmpty(height) || TextUtils.isEmpty(weight))
				{
					Toast.makeText(getBaseContext(), R.string.main_empty_message, Toast.LENGTH_SHORT).show();
					return;
				}
				
				showComputeDialog(computeBMI());
				
				break;
			}
		}
	}
	
	private BMIBean computeBMI()
	{
		BMIBean bean = new BMIBean();
		float height = Float.parseFloat(xEditText_Height.getText().toString()) / 100;
		float weight = Float.parseFloat(xEditText_Weight.getText().toString());
		bean.bmi = weight / (height * height);
		
		int length = BMIs.length;
		for(int i=0; i<length; i++)
		{
			if(BMIs[i] > bean.bmi)
			{
				bean.position = (i > 0) ? i - 1 : 0; 
				return bean;
			}
		}
		
		bean.position = length - 1; 
		return bean;
	}
	
	private void showComputeDialog(BMIBean bean)
	{
		AlertDialog.Builder xBuilder = new Builder(this);
		xBuilder.setTitle(R.string.dialog_compute_title);
		final String message = String.format(getString(R.string.dialog_compute_format), bean.bmi, getStringArray(R.array.main_texts)[bean.position]);
		xBuilder.setView(ViewUtil.getInflateTextView(this, R.layout.dialog_inflate, R.id.text, message));
		xBuilder.setPositiveButton(R.string.dialog_compute_positive_button, null);
		xBuilder.show();
	}
	
	private void showExitDialog()
	{
		AlertDialog.Builder xBuilder = new Builder(this);
		xBuilder.setTitle(R.string.dialog_exit_title);
		xBuilder.setView(ViewUtil.getInflateTextView(this, R.layout.dialog_inflate, R.id.text, R.string.dialog_exit_message));
		xBuilder.setPositiveButton(R.string.dialog_exit_positive_button, new DialogInterface.OnClickListener()
		{
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				finish();
			}
		});
		xBuilder.setNegativeButton(R.string.dialog_exit_negative_button, null);
		xBuilder.show();
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event)
	{
		switch(keyCode)
		{
			case KeyEvent.KEYCODE_BACK :
			{
				showExitDialog();
				
				return true;
			}
		}
		
		return false;
	}
}

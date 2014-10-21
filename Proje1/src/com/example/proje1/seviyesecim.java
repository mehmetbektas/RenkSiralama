package com.example.proje1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


public class seviyesecim extends Activity {

    public static int SeviyeId,AltSeviyeId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seviye_secim);
		Toast.makeText(seviyesecim.this, "Lütfen seviye ve altseviye seçin", Toast.LENGTH_SHORT).show();
		
		Button btnIleri = (Button)findViewById(R.id.btnSeviyeSecim);
		final RadioGroup rgSeviye = (RadioGroup)findViewById(R.id.radioGroup1);
		final RadioButton rbSeviye1 = (RadioButton)findViewById(R.id.seviye1);
		final RadioButton rbSeviye2 = (RadioButton)findViewById(R.id.seviye2);
		final RadioButton rbSeviye3 = (RadioButton)findViewById(R.id.seviye3);
		
		final RadioGroup rgAltSeviye = (RadioGroup)findViewById(R.id.radioGroup2);
		final RadioButton rbAltSeviye1 = (RadioButton)findViewById(R.id.altseviye1);
		final RadioButton rbAltSeviye2 = (RadioButton)findViewById(R.id.altseviye2);
		final RadioButton rbAltSeviye3 = (RadioButton)findViewById(R.id.altseviye3);
		final RadioButton rbAltSeviye4 = (RadioButton)findViewById(R.id.altseviye4);
		
		
		btnIleri.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(rbSeviye1.isChecked()) SeviyeId=1;
				if(rbSeviye2.isChecked()) SeviyeId=2;
				if(rbSeviye3.isChecked()) SeviyeId=3;
				
				if(rbAltSeviye1.isChecked()) AltSeviyeId=1;
				if(rbAltSeviye2.isChecked()) AltSeviyeId=2;
				if(rbAltSeviye3.isChecked()) AltSeviyeId=3;
				if(rbAltSeviye4.isChecked()) AltSeviyeId=4;
				
				Intent intResimYukleme = new Intent("com.example.proje1.RESIMYUKLEME");
				startActivity(intResimYukleme);
				
				/*
				if (rgSeviye.isPressed() && rgAltSeviye.isPressed()) {
					
				} else {
					Toast.makeText(seviyesecim.this, "Devam etmek için seviye ve altseviye seçin", Toast.LENGTH_LONG).show();
					
				}
				*/
				
				
			}
		});
		
		
		
	}
	
	
}

package com.example.proje1;

import java.util.ArrayList;

import algoritmalar.MedianCutAlg;
import algoritmalar.ScalarQuanAlg;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class algoritmasecim extends Activity {

	public static Bitmap bitmap;
	public static ArrayList<String> alRenk = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.algoritma_secim);
		Toast.makeText(algoritmasecim.this, "Lütfen algortimayý seçin",	Toast.LENGTH_LONG).show();
		final RadioGroup rgAlgoritma = (RadioGroup)findViewById(R.id.rgAlgoritma);
		final RadioButton rbMedianCut = (RadioButton)findViewById(R.id.median);
		final RadioButton rbScalarQuan = (RadioButton)findViewById(R.id.scalar);
		
		resimyukleme ry = new resimyukleme();
		this.bitmap=ry.resim; //burada sorun yok
		
		
		Button btnIleri = (Button) findViewById(R.id.btnAlgoritmaSec);
		btnIleri.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			//	final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
				
				
				if (rbMedianCut.isChecked()) {
					MedianCutAlg mca = new MedianCutAlg(bitmap);
					alRenk=mca.renklerson;
					Intent i = new Intent("com.example.proje1.GRAFIKARAYUZU");
					startActivity(i);
				} else if(rbScalarQuan.isChecked()){
					ScalarQuanAlg sqa = new ScalarQuanAlg(bitmap);
					alRenk=sqa.renklerson;
					Intent i = new Intent("com.example.proje1.GRAFIKARAYUZU");
					startActivity(i);
				}else{
					Toast.makeText(algoritmasecim.this, "Devam etmek için algortimayý seçin",	Toast.LENGTH_LONG).show();
					
				}
				
				
				
				
			}
		});
	}

}
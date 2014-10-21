package com.example.proje1;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class GrafikArayuzu extends Activity implements OnTouchListener{
	CizimSinifi cs;
	private float ekranX;
	private float ekranY;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);       
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Display dsp = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
	    ekranX=dsp.getWidth();
	    ekranY=dsp.getHeight();
		cs = new CizimSinifi(this);
		cs.setOnTouchListener(this);
		setContentView(cs);
		cs.requestFocus();
		
		Toast.makeText(GrafikArayuzu.this, "1'den baþlayarak koyudan açýða sýralayýnýz", Toast.LENGTH_LONG).show();
		
	}
	
	@Override
    protected void onResume() 
	{
		// TODO Auto-generated method stub
		super.onResume();
    	cs.onResumeMySurfaceView();
    }
    
    @Override
    protected void onPause() 
    {
    	// TODO Auto-generated method stub
    	super.onPause();
    	cs.onPauseMySurfaceView();
    }
	
	@Override
	public boolean onTouch(View v, MotionEvent me) {
		
		if( me.getX() > cs.btnTamam.x && me.getX() <cs.btnTamam.x + cs.btnTamam.boyut_x/2 &&
			me.getY() > cs.btnTamam.y && me.getY() <cs.btnTamam.y + cs.btnTamam.boyut_y )
		{
			cs.Yerlestirme_Dogru_mu=false;
			Toast.makeText(GrafikArayuzu.this, " Sýralý Deðil \nKontrol Ediniz", Toast.LENGTH_SHORT).show();
		}
		if( me.getX() > cs.btnTamam.x + cs.btnTamam.boyut_x/2 && me.getX() <cs.btnTamam.x + cs.btnTamam.boyut_x &&
				me.getY() > cs.btnTamam.y  && me.getY() <cs.btnTamam.y + cs.btnTamam.boyut_y )
		{
			cs.Yerlestirme_Dogru_mu=true;
			
			
	        final CharSequence[] items = { "Oyunu "+(int)(cs.saniye/30)+" saniyede bitirdiniz.\n\n"
	        		+ "Seviye: "+cs.seviye+"\t\t\t\t\t\t\t+"+cs.seviye*100+"\n"
	        				+ "Alt Seviye: "+cs.altSeviye+"\t\t\t\t\t+"+cs.altSeviye*20+"\n"
	        						+ "Süre: "+(int)(cs.saniye/30)+"\t\t\t\t\t\t\t\t-"+(int)(cs.saniye/30)+"\n\n\n"
	        								+ "SKOR: "+(cs.seviye*100+cs.altSeviye*20-(int)(cs.saniye/30)+"\n\n\n"
	        										) , "Yeni Oyun Ýçin Týklayýnýz" };
	        AlertDialog.Builder builder = new AlertDialog.Builder(GrafikArayuzu.this);
	        builder.setTitle("TEBRÝKLER");
	        builder.setItems(items, new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int item) {
	            	
	            	 if (items[item].equals("Yeni Oyun Ýçin Týklayýnýz") ){
	            		 startActivity(new Intent("com.example.proje1.SEVIYESECIM"));
	            	 }
	            	
	            	
	            }
	        });
	        builder.show();
	        
	        onPause();
			
		}
		
/*		if( me.getX() > cs.btnTamam.x && me.getX() <cs.btnTamam.x + cs.btnTamam.boyut_x &&
				me.getY() > cs.btnTamam.y && me.getY() <cs.btnTamam.y + cs.btnTamam.boyut_y )
			{
			if (cs.yerlestirme_kontrol()) {
				Toast.makeText(GrafikArayuzu.this, " Sýralý", Toast.LENGTH_SHORT).show();
			}else{
				Toast.makeText(GrafikArayuzu.this, " Sýralý Deðil \nKontrol Ediniz", Toast.LENGTH_SHORT).show();
			}
		}
*/		
		
		switch(me.getAction()){
		
		case MotionEvent.ACTION_MOVE:
			for (int i = 0; i < cs.renk.length ; i++) {
				if (me.getX() > cs.renk[i].x-cs.renk[i].boyut/2 && me.getX() < cs.renk[i].x+ cs.renk[i].boyut/2 && 
					me.getY() > cs.renk[i].y-cs.renk[i].boyut/2 && me.getY() < cs.renk[i].y+ cs.renk[i].boyut/2 ){
					
					cs.renk[i].x=me.getX();
					cs.renk[i].y=me.getY();
					
					break;
				}
			}
			break;
		}
		
		return true;
	}
}

	
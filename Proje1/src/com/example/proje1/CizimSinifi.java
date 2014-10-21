package com.example.proje1;

import java.util.ArrayList;
import java.util.List;

import algoritmalar.MedianCutAlg;
import algoritmalar.ScalarQuanAlg;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.webkit.WebSettings.TextSize;
import android.widget.Toast;



public class CizimSinifi extends SurfaceView implements Runnable {

	seviyesecim ss;
	Thread tired = null;
	SurfaceHolder holder;
	Bitmap bitmap;
	Paint paint = new Paint();
	public  int seviye, altSeviye;
	boolean isitOK = true;
	public float x, y;
	public Renk[] renk = null;
	public Hucre[] hucre = null;
	public Buton btnTamam;
	public Buton btn; /************************************************************************************/
	public ArrayList<String> alRenkKodlari = new ArrayList<String>();
	public int saniye;
	public boolean Yerlestirme_Dogru_mu;
	
	public CizimSinifi(Context context){
		super(context);
		holder = getHolder();
		saniye=0;
		seviye = ss.SeviyeId;
		altSeviye = ss.AltSeviyeId;

		algoritmasecim as = new algoritmasecim();
		this.alRenkKodlari = as.alRenk; // Clear

		olustur_renkler(seviye, altSeviye, alRenkKodlari);
		olustur_butonlar();
		
		

	}

	public void onResumeMySurfaceView(){
		isitOK = true;
		tired = new Thread(this);
		tired.start();
		
	}
	 
	public void onPauseMySurfaceView(){
		boolean retry = true;
		isitOK = false;
		while(retry)
		{
			try 
			{
				tired.join();
			    retry = false;
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
			//break;
		}
	//	tired = null;
	}

	@Override
	public void run(){
		while(isitOK)
		{
			
			if(!holder.getSurface().isValid()) continue;
			Canvas c =  holder.lockCanvas();
			onDraw(c);
			holder.unlockCanvasAndPost(c);
			saniye++;
		}
	}
	
	@Override
	protected void onDraw(Canvas canvas){
		canvas.drawARGB(255,255,255,255);
		ciz_renkler(canvas);
		paint.setColor(Color.RED);
		ciz_hucreler(canvas);
		ciz_butonlar(canvas);
		merkeze_tuttur();	
	}
	
	private void olustur_renkler(int seviye, int altSeviye ,ArrayList<String> arlist){
		
		float x,y;
	
		switch (seviye) {
		case 1: {
			switch (altSeviye) {
			case 1: {
				renk = new Renk[4];
				hucre = new Hucre[4];
				x=80; y=40;
				for(int i=0;i<renk.length;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    renk[i].renkkodu=arlist.get(i);
				    renk[i].x=x; renk[i].y=y;
				    x+=90;
				}
				
				hucre[0].x=80;  	hucre[0].y=280; 	hucre[0].id=1;
				hucre[1].x=160; 	hucre[1].y=280; 	hucre[1].id=2;
				hucre[2].x=240; 	hucre[2].y=280; 	hucre[2].id=3;
				hucre[3].x=320; 	hucre[3].y=280; 	hucre[3].id=4;
				
				
				break;
			}
			case 2: {
				renk = new Renk[5];
				hucre = new Hucre[5];
				x=60; y=40;
				
				for(int i=0;i<5;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    
				    renk[i].renkkodu = arlist.get(i);
				    
				    renk[i].x=x; renk[i].y=y;
				    x+=90;
				}
				
				
				hucre[0].x=80;  	hucre[0].y=360; 	hucre[0].id=1;
				hucre[1].x=160; 	hucre[1].y=360; 	hucre[1].id=2;
				hucre[2].x=240; 	hucre[2].y=360; 	hucre[2].id=3;
				hucre[3].x=320; 	hucre[3].y=360; 	hucre[3].id=4;
				hucre[4].x=320; 	hucre[4].y=280; 	hucre[4].id=5;
				
				break;
			}
			case 3: {
				renk = new Renk[5];
				hucre = new Hucre[5];
				x=60; y=40;
				
				for(int i=0;i<renk.length;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    renk[i].renkkodu=arlist.get(i);
				    
				    renk[i].x=x; renk[i].y=y; 
				    x+=90;
				}
				
				hucre[0].x=80;  	hucre[0].y=360; 	hucre[0].id=1;
				hucre[1].x=80; 		hucre[1].y=280; 	hucre[1].id=2;
				hucre[2].x=160; 	hucre[2].y=360; 	hucre[2].id=3;
				hucre[3].x=240; 	hucre[3].y=360; 	hucre[3].id=4;
				hucre[4].x=320; 	hucre[4].y=360; 	hucre[4].id=5;
				

				break;
			}
			case 4: {
				renk = new Renk[6];
				hucre = new Hucre[6];
				x=60; y=40;
				
				for(int i=0;i<renk.length;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    renk[i].renkkodu=arlist.get(i);
				    
				    if(i==5 || i==10){
				    	y+=90;
				    	x=60;
				    }
				    renk[i].x=x; renk[i].y=y; 
				    x+=90;
				}
				
				hucre[0].x=80;  	hucre[0].y=360; 	hucre[0].id=1;
				hucre[1].x=160; 	hucre[1].y=280; 	hucre[1].id=2;
				hucre[2].x=160; 	hucre[2].y=360; 	hucre[2].id=3;
				hucre[3].x=240; 	hucre[3].y=360; 	hucre[3].id=4;
				hucre[4].x=320; 	hucre[4].y=360; 	hucre[4].id=5;
				hucre[5].x=320; 	hucre[5].y=440; 	hucre[5].id=6;
				
				break;
			}
			}
		}
			break;
		// 2.Seviye
		case 2: {
			switch (altSeviye) {
			case 1: {
				renk = new Renk[7];
				hucre = new Hucre[7];
				x=60; y=40;
				
				for(int i=0;i<renk.length;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    renk[i].renkkodu=arlist.get(i);
				    
				    if(i==5 || i==10){
				    	y+=90;
				    	x=60;
				    }
				    renk[i].x=x; renk[i].y=y; 
				    x+=90;
				}
				
				hucre[0].x=40;  	hucre[0].y=280; 	hucre[0].id=1;
				hucre[1].x=40; 		hucre[1].y=360; 	hucre[1].id=2;
				hucre[2].x=120; 	hucre[2].y=360; 	hucre[2].id=3;
				hucre[3].x=200; 	hucre[3].y=360; 	hucre[3].id=4;
				hucre[4].x=280; 	hucre[4].y=360; 	hucre[4].id=5;
				hucre[5].x=360; 	hucre[5].y=360; 	hucre[5].id=6;
				hucre[6].x=360; 	hucre[6].y=440; 	hucre[6].id=7;

			}
			case 2: {
				renk = new Renk[8];
				hucre = new Hucre[8];
				x=60; y=40;
				
				for(int i=0;i<renk.length;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    renk[i].renkkodu=arlist.get(i);
				    
				    if(i==5 || i==10){
				    	y+=90;
				    	x=60;
				    }
				    renk[i].x=x; renk[i].y=y; 
				    x+=90;
				}
				
				hucre[0].x=40;  	hucre[0].y=360; 	hucre[0].id=1;
				hucre[1].x=40; 		hucre[1].y=280; 	hucre[1].id=2;
				hucre[2].x=120; 	hucre[2].y=280; 	hucre[2].id=3;
				hucre[3].x=200; 	hucre[3].y=280; 	hucre[3].id=4;
				hucre[4].x=280; 	hucre[4].y=280; 	hucre[4].id=5;
				hucre[5].x=360; 	hucre[5].y=280; 	hucre[5].id=6;
				hucre[6].x=360; 	hucre[6].y=360; 	hucre[6].id=7;
				hucre[7].x=360; 	hucre[7].y=440; 	hucre[7].id=8;

			}
			case 3: {
				renk = new Renk[9];
				hucre = new Hucre[9];
				x=60; y=40;
				
				for(int i=0;i<renk.length;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    renk[i].renkkodu=arlist.get(i);
				    
				    if(i==5 || i==10){
				    	y+=90;
				    	x=60;
				    }
				    renk[i].x=x; renk[i].y=y; 
				    x+=90;
				}
				
				hucre[0].x=40;  	hucre[0].y=360; 	hucre[0].id=1;
				hucre[1].x=120; 	hucre[1].y=360; 	hucre[1].id=2;
				hucre[2].x=120; 	hucre[2].y=280; 	hucre[2].id=3;
				hucre[3].x=200; 	hucre[3].y=280; 	hucre[3].id=4;
				hucre[4].x=280; 	hucre[4].y=280; 	hucre[4].id=5;
				hucre[5].x=360; 	hucre[5].y=280; 	hucre[5].id=6;
				hucre[6].x=360; 	hucre[6].y=360; 	hucre[6].id=7;
				hucre[7].x=360; 	hucre[7].y=440; 	hucre[7].id=8;
				hucre[8].x=280; 	hucre[8].y=520; 	hucre[8].id=9;

				break;
			}
			case 4: {
				renk = new Renk[10];
				hucre = new Hucre[10];
				x=60; y=40;
				
				for(int i=0;i<renk.length;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    renk[i].renkkodu=arlist.get(i);
				    
				    if(i==5 || i==10){
				    	y+=90;
				    	x=60;
				    }
				    renk[i].x=x; renk[i].y=y; 
				    x+=90;
				}
				
				hucre[0].x=40;  	hucre[0].y=440; 	hucre[0].id=1;
				hucre[1].x=120; 	hucre[1].y=440; 	hucre[1].id=2;
				hucre[2].x=120; 	hucre[2].y=360; 	hucre[2].id=3;
				hucre[3].x=120; 	hucre[3].y=280; 	hucre[3].id=4;
				hucre[4].x=200; 	hucre[4].y=280; 	hucre[4].id=5;
				hucre[5].x=280; 	hucre[5].y=280; 	hucre[5].id=6;
				hucre[6].x=360; 	hucre[6].y=280; 	hucre[6].id=7;
				hucre[7].x=360; 	hucre[7].y=360; 	hucre[7].id=8;
				hucre[8].x=360; 	hucre[8].y=440; 	hucre[8].id=9;
				hucre[9].x=280; 	hucre[9].y=440; 	hucre[9].id=10;

			}
			}
		}
		// 3.Seviye
		case 3: {
			switch (altSeviye) {
			case 1: {
				renk = new Renk[11];
				hucre = new Hucre[11];
				x=60; y=40;
				
				for(int i=0;i<renk.length;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    renk[i].renkkodu=arlist.get(i);
				    
				    if(i==5 || i==10){
				    	y+=90;
				    	x=60;
				    }
				    renk[i].x=x; renk[i].y=y; 
				    x+=90;
				}
				
				hucre[0].x=40;  	hucre[0].y=520; 	hucre[0].id=1;
				hucre[1].x=40; 		hucre[1].y=440; 	hucre[1].id=2;
				hucre[2].x=40; 		hucre[2].y=360; 	hucre[2].id=3;
				hucre[3].x=40; 		hucre[3].y=280; 	hucre[3].id=4;
				hucre[4].x=120; 	hucre[4].y=280; 	hucre[4].id=5;
				hucre[5].x=200; 	hucre[5].y=280; 	hucre[5].id=6;
				hucre[6].x=280; 	hucre[6].y=280; 	hucre[6].id=7;
				hucre[7].x=360; 	hucre[7].y=280; 	hucre[7].id=8;
				hucre[8].x=360; 	hucre[8].y=360; 	hucre[8].id=9;
				hucre[9].x=360; 	hucre[9].y=440; 	hucre[9].id=10;			
				hucre[10].x=280; 	hucre[10].y=440; 	hucre[10].id=11;	
				break;
			}
			case 2: {
				renk = new Renk[12];
				hucre = new Hucre[12];
				x=60; y=40;
				
				for(int i=0;i<renk.length;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    renk[i].renkkodu=arlist.get(i);
				    
				    if(i==5 || i==10){
				    	y+=90;
				    	x=60;
				    }
				    renk[i].x=x; renk[i].y=y; 
				    x+=90;
				}
				
				hucre[0].x=120;  	hucre[0].y=520; 	hucre[0].id=1;
				hucre[1].x=120; 	hucre[1].y=440; 	hucre[1].id=2;
				hucre[2].x=120; 	hucre[2].y=360; 	hucre[2].id=3;
				hucre[3].x=40; 		hucre[3].y=360; 	hucre[3].id=4;
				hucre[4].x=40; 		hucre[4].y=280; 	hucre[4].id=5;
				hucre[5].x=120; 	hucre[5].y=280; 	hucre[5].id=6;
				hucre[6].x=200; 	hucre[6].y=280; 	hucre[6].id=7;
				hucre[7].x=280; 	hucre[7].y=280; 	hucre[7].id=8;
				hucre[8].x=360; 	hucre[8].y=280; 	hucre[8].id=9;
				hucre[9].x=360; 	hucre[9].y=360; 	hucre[9].id=10;			
				hucre[10].x=360; 	hucre[10].y=440; 	hucre[10].id=11;			
				hucre[11].x=280; 	hucre[11].y=440; 	hucre[11].id=12;
				
				break;
			}
			case 3: {
				renk = new Renk[13];
				hucre = new Hucre[13];
				x=60; y=40;
				
				for(int i=0;i<renk.length;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    renk[i].renkkodu=arlist.get(i);
				    
				    if(i==5 || i==10){
				    	y+=90;
				    	x=60;
				    }
				    renk[i].x=x; renk[i].y=y; 
				    x+=90;
				}
				
				hucre[0].x=120;  	hucre[0].y=520; 	hucre[0].id=1;
				hucre[1].x=120; 	hucre[1].y=440; 	hucre[1].id=2;
				hucre[2].x=40; 		hucre[2].y=440; 	hucre[2].id=3;
				hucre[3].x=40; 		hucre[3].y=360; 	hucre[3].id=4;
				hucre[4].x=40; 		hucre[4].y=280; 	hucre[4].id=5;
				hucre[5].x=120; 	hucre[5].y=280; 	hucre[5].id=6;
				hucre[6].x=200; 	hucre[6].y=280; 	hucre[6].id=7;
				hucre[7].x=280; 	hucre[7].y=280; 	hucre[7].id=8;
				hucre[8].x=360; 	hucre[8].y=280; 	hucre[8].id=9;
				hucre[9].x=360; 	hucre[9].y=360; 	hucre[9].id=10;			
				hucre[10].x=360; 	hucre[10].y=440; 	hucre[10].id=11;			
				hucre[11].x=280; 	hucre[11].y=440; 	hucre[11].id=12;
				hucre[12].x=280; 	hucre[12].y=520; 	hucre[12].id=13;
				
				break;
			}
			case 4: {
				renk = new Renk[15];
				hucre = new Hucre[15];
				x=60; y=40;
				
				for(int i=0;i<renk.length;i++){
				    renk[i]= new Renk();
				    hucre[i] = new Hucre();
				    renk[i].boyut=60;
				    hucre[i].boyut=80;
				    if (arlist.get(i)!=null) {
						renk[i].renkkodu=arlist.get(i);
					}else {
						renk[i].renkkodu=arlist.get(0);
					}
				    
				    
				    if(i==5 || i==10){
				    	y+=90;
				    	x=60;
				    }
				    renk[i].x=x; renk[i].y=y; 
				    x+=90;
				}
				
				hucre[0].x=40;  	hucre[0].y=520; 	hucre[0].id=1;
				hucre[1].x=40; 		hucre[1].y=440; 	hucre[1].id=2;
				hucre[2].x=40; 		hucre[2].y=360; 	hucre[2].id=3;
				hucre[3].x=40; 		hucre[3].y=280; 	hucre[3].id=4;
				hucre[4].x=120; 	hucre[4].y=280; 	hucre[4].id=5;
				hucre[5].x=200; 	hucre[5].y=280; 	hucre[5].id=6;
				hucre[6].x=280; 	hucre[6].y=280; 	hucre[6].id=7;
				hucre[7].x=360; 	hucre[7].y=280; 	hucre[7].id=8;
				hucre[8].x=360; 	hucre[8].y=360; 	hucre[8].id=9;
				hucre[9].x=360; 	hucre[9].y=440; 	hucre[9].id=10;			
				hucre[10].x=360; 	hucre[10].y=520; 	hucre[10].id=11;			
				hucre[11].x=280; 	hucre[11].y=520; 	hucre[11].id=12;
				hucre[12].x=200; 	hucre[12].y=520; 	hucre[12].id=13;
				hucre[13].x=200; 	hucre[13].y=440; 	hucre[13].id=14;
				hucre[14].x=200; 	hucre[14].y=360; 	hucre[14].id=15;

				break;
			}
			}
			break;
		}
		}// en büyük switch in bitiþi
	}// olustur_renk
	
	private void ciz_renkler(Canvas canvas){
		for(int i=0;i<renk.length;i++) {
			paint.setStyle(Style.FILL);
			paint.setColor(Color.parseColor(renk[i].renkkodu));
			canvas.drawCircle(renk[i].x, renk[i].y,renk[i].boyut/2, paint);
			
		}
	}
	
	private void ciz_hucreler(Canvas canvas){
		for(int i=0;i<hucre.length;i++) {
			paint.setStyle(Style.STROKE);
			paint.setStrokeWidth(5);
			canvas.drawRect(hucre[i].x, hucre[i].y, hucre[i].x+hucre[i].boyut, hucre[i].y+hucre[i].boyut, paint);
			paint.setStrokeWidth(1);
			canvas.drawText(""+hucre[i].id , hucre[i].x+hucre[i].boyut/2, hucre[i].y+hucre[i].boyut/2, paint);
		}
	}
	
	private void merkeze_tuttur() {
		for (int i = 0; i < renk.length; i++) {
			for (int j = 0; j < hucre.length; j++) {
				if ( (Math.abs(renk[i].x - (hucre[j].x + hucre[j].boyut / 2)) < 20) && 
					 (Math.abs(renk[i].y - (hucre[j].y + hucre[j].boyut / 2)) < 20)) {
					renk[i].x = hucre[j].x + hucre[j].boyut / 2;
					renk[i].y = hucre[j].y + hucre[j].boyut / 2;
				}
			}
		}
	}

	private void olustur_butonlar()
	{
		btnTamam = new Buton();
		btnTamam.x=100; btnTamam.y=650;
		btnTamam.boyut_x=100; btnTamam.boyut_y=50; 
		
	}
	
	private void ciz_butonlar(Canvas canvas)
	{
		paint.setColor(Color.GREEN);
		paint.setStyle(Style.FILL);
		canvas.drawRect(btnTamam.x, btnTamam.y,btnTamam.x+btnTamam.boyut_x, btnTamam.y+btnTamam.boyut_y, paint);
		paint.setColor(Color.RED);
		canvas.drawText("TAMAM", btnTamam.x+30, btnTamam.y+30 , paint);
		paint.setColor(Color.BLUE);
		paint.setTextSize(30);
		canvas.drawText(""+(int)(saniye/50),400,700, paint);
		paint.setTextSize(12);
	}
	
	public boolean yerlestirme_kontrol() {
		//	Renk dizisi ile hucre sýralý olacak
		
	
		int kontrol=0;
		for (int i = 0; i < renk.length-1; i++) {
			if(renk[hucre[i].id-1].renkkodu.substring(1).compareTo
			  (renk[hucre[i+1].id-1].renkkodu.substring(1)) <= 1 ) {
				kontrol=1;
				break;
			}
		}
		
		if (kontrol==0) {
			return true;
		} else {
			return false;
		}
	}

	

}

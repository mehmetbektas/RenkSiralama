package algoritmalar;


import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.Color;


public class MedianCutAlg {
	
	Bitmap bitmap;
	public  ArrayList<String> renklerson = new ArrayList<String>();
	public  ArrayList<String> alRenkler = new ArrayList<String>();
	
	/**
	 * 
	 * Kurucu Fonksiyon
	 * @param bitmap
	 * 
	 */
	
	public MedianCutAlg(Bitmap bitmap)
	{
		 bitmap=Bitmap.createScaledBitmap(bitmap, (int)(bitmap.getWidth()/4), (int)(bitmap.getHeight()/4), true);
		this.bitmap=bitmap;
		int width = bitmap.getWidth();
		int height = bitmap.getHeight();
		int x=0,y=0;
		
	//	x=20; y=20; width=160; height=160;
		ArrayList<ArrayList<String>> alDortAL = new ArrayList<ArrayList<String>>();
		do {
			alDortAL.clear();
			alRenkler.clear();
			
			for (int j = y; j < height; j = j+(height-y) / 2) {
				for (int i = x; i < width; i = i+(width-x) / 2) {
					alDortAL.add(tara((int)i, (int)j, (int)(i+(width-x) / 2), (int)(j+(height-y) / 2)));
					
				}
			}
			
			int enb=0, k=0;
			
			for (int i = 0; i < alDortAL.size(); i++) {
				if (alDortAL.get(i).size()>enb) {
					enb=alDortAL.get(i).size();
					k=i;
				}
			}
			
			
			alRenkler.addAll(alDortAL.get(k));
			
			/* Burada x,y,width,height yeniden düzenleniyor */
			
			switch (k) {
				case 0:
					width=(width-x)/2; 		
					height=(height-y)/2;		
					break;
				case 1:
					x=x+(width-x)/2; 	
					height=(height-y)/2;	
					break;
				case 2:
					y=y+(height-y)/2; 
					width=(width-x)/2; 	
					break;
				case 3:
					x=x+(width-x)/2; 	
					y=y+(height-y)/2; 	
					break;
				default:
					break;
			}
			
			
			
		} while (alRenkler.size()>60);//alRenkler.size()<15		
		
		
		//renklerson arraylistini dolduruyoruz	
		/*<-- en son iþlem bu olacak -->*/

		renklerson.addAll(alRenkler);
	}		
	
	private ArrayList<String> tara(int x,int y,int width,int height)
	{
		String geciciRenk;
		ArrayList<String> alRenkler = new ArrayList<String>();
		for (int j = x; j < height; j++) {
			for (int i = y; i < width; i++) {
				geciciRenk = hexRenkKodu(i, j);
				boolean kontrol = false;
				for (int k = 0; k < alRenkler.size(); k++) {
					if (alRenkler.get(k).equals(geciciRenk)) {
						kontrol=true;
					}
				}
				if (!kontrol) {
					alRenkler.add(geciciRenk);
				}
			}
		}
		return alRenkler;
	}
	
	private String hexRenkKodu(int x, int y){
		int pixel = bitmap.getPixel(x,y);//bu x ve y yi bulmam gerek
		int redValue = Color.red(pixel);
		int greenValue = Color.green(pixel);
		int blueValue = Color.blue(pixel);
	    return "#" + getBaseColorAsHex(redValue) + getBaseColorAsHex(greenValue) + getBaseColorAsHex(blueValue);
	}

	private String getBaseColorAsHex(int baseColor){
	    final String hex = Integer.toHexString(baseColor);
	    return (hex.length() == 1 ? '0' : "") + hex;
	}
}



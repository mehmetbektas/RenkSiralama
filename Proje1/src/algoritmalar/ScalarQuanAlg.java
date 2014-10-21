package algoritmalar;

import java.util.ArrayList;
import android.graphics.Bitmap;
import android.graphics.Color;


public class ScalarQuanAlg {

public ArrayList<String> renklerson=new ArrayList<String>();	
Bitmap kucukBitmap;
	
	public ScalarQuanAlg(Bitmap bitmap) {
		
		kucukBitmap=Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/10, bitmap.getHeight()/10, true);
		// kucukBitmap in bit derinliði 8 e düþürülecek
		
		int width = kucukBitmap.getWidth();
		int height = kucukBitmap.getHeight();
		
		this.renklerson.addAll(tara(width, height));
		
	}
	
	private ArrayList<String> tara(int width,int height)
	{
		String geciciRenk;
		ArrayList<String> alRenkler = new ArrayList<String>();
		for (int j = 0; j < height; j++) {
			for (int i = 0; i < width; i++) {
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
		int pixel = kucukBitmap.getPixel(x,y);//bu x ve y yi bulmam gerek
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

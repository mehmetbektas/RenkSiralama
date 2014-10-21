package com.example.proje1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import algoritmalar.MedianCutAlg;
import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.provider.MediaStore.MediaColumns;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * @author Innocent
 *
 */



public class resimyukleme extends Activity {

	
	private final int SELECT_FILE = 1;
	private final int REQUEST_CAMERA = 0;
	public static ImageView ivImage;
	public static Bitmap resim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resim_yukleme);
		
		ivImage = (ImageView) findViewById(R.id.ivImage);
		Button btnResimYukleme = (Button) findViewById(R.id.btnSelectPhoto);
		btnResimYukleme.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				selectImage();
			}
		});
	
		Button btnIleri = (Button) findViewById(R.id.btnIleri);
		btnIleri.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		//		Bitmap bmap = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
//			resim = ((BitmapDrawable)ivImage.getDrawable()).getBitmap();
		//		ivImage.buildDrawingCache();
		//		resim = Bitmap.createBitmap(ivImage.getWidth(),ivImage.getHeight(), Bitmap.Config.ALPHA_8);
		//	ivImage.setImageBitmap(resim);	

/*			ivImage.setDrawingCacheEnabled(true);
			ivImage.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), 
			                   MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED));
			ivImage.layout(0, 0, 
					ivImage.getMeasuredWidth(), ivImage.getMeasuredHeight()); 
			ivImage.buildDrawingCache(true);
			resim = Bitmap.createBitmap(ivImage.getDrawingCache());
			 ivImage.setDrawingCacheEnabled(false);
*/			
			
/*				Drawable d = ivImage.getDrawable();
				resim= Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
*/
/*				Drawable d = ivImage.getDrawable();
				resim= Bitmap.createBitmap(d.getIntrinsicWidth(), d.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
				Canvas canvas = new Canvas(resim);
				d.draw(canvas);
*/				
				BitmapDrawable drawable = (BitmapDrawable) ivImage.getDrawable();
				resim = drawable.getBitmap();
				
				
				Intent intAlg = new Intent("com.example.proje1.ALGORITMASECIM");
				
				startActivity(intAlg);
				
			}
		});
		
	
		
		
		
	}


	private void selectImage() {
        final CharSequence[] items = { "Fotoðraf Çek", "Galeriden Seç", "Vazgeç" };
        AlertDialog.Builder builder = new AlertDialog.Builder(resimyukleme.this);
        builder.setTitle("Fotoðraf Ekle");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Fotoðraf Çek")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                    startActivityForResult(intent, REQUEST_CAMERA);
                } else if (items[item].equals("Galeriden Seç")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    startActivityForResult( Intent.createChooser(intent, "Select File"),SELECT_FILE);
                } else if (items[item].equals("Vazgeç")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
	
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CAMERA) {
                File f = new File(Environment.getExternalStorageDirectory()
                        .toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bm;
                    BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
 
                    bm = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            btmapOptions);
 
                    bm = Bitmap.createScaledBitmap(bm, 70, 70, true);
                    ivImage.setImageBitmap(bm);
 
                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream fOut = null;
                    File file = new File(path, String.valueOf(System
                            .currentTimeMillis()) + ".jpg");
                    try {
                        fOut = new FileOutputStream(file);
                        bm.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
                        fOut.flush();
                        fOut.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == SELECT_FILE) {
                Uri selectedImageUri = data.getData();
 
                String tempPath = getPath(selectedImageUri);
                Bitmap bm;
                BitmapFactory.Options btmapOptions = new BitmapFactory.Options();
                bm = BitmapFactory.decodeFile(tempPath, btmapOptions);
                ivImage.setImageBitmap(bm);
          }
        }
        
		
  }
	
	/*
	public String getPath(Uri uri) {
        if( uri == null ) {
            return null;
        }
        String projection[] = new String[]{MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(Media.EXTERNAL_CONTENT_URI, projection,null,null, null);
        if( cursor != null ){
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
}
	
	*/
	
	public String getPath(Uri uri) {
        String[] projection = { MediaStore.Images.Media.DATA };
   
		Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }

	

}

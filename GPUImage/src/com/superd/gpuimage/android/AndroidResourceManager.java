package com.superd.gpuimage.android;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class AndroidResourceManager {
	
	private Context mContext = null;
	
	private AndroidResourceManager() {
		
	}
	
	public void setContext(Context context) {
		mContext = context;
	}
	
	public String readStringFromAssets(String fileName) {
		if (mContext != null) {
			try {
				InputStreamReader inputReader = new InputStreamReader(mContext
						.getResources().getAssets().open(fileName));
				BufferedReader bufReader = new BufferedReader(inputReader);
				String line = "";
				String Result = "";
				while ((line = bufReader.readLine()) != null)
					Result += line;
				return Result;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Bitmap readBitmapFromAssets(String fileName) {
		InputStream bitmapStream = null;
		try {
			bitmapStream = mContext.getAssets().open(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Bitmap bitmap = BitmapFactory.decodeStream(bitmapStream);
		return bitmap;
	}
	
	public static synchronized AndroidResourceManager getAndroidResourceManager() {
		if (mAndroidResourceManager == null) {
			mAndroidResourceManager = new AndroidResourceManager();
		}
		return mAndroidResourceManager;
	}
	
	private static AndroidResourceManager mAndroidResourceManager = null;
}

package com.example.try_connect4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class BitmapUtil {
	
	// 初始化好紅綠兩點
	public static Bitmap createSpecificSizeBitmap(Drawable drawable, int width, int height) {
		// 新建一個bitmap，長寬20，使用ARGB_8888設定，此bitmap現在空白bitmap但非null。
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap); // 新建畫布，用空白bitmap當畫布
		drawable.setBounds(0, 0, width, height);// 設定drawable的邊界(原圖片有自己的長寬)
		drawable.draw(canvas); // 在畫布上畫上此drawable(此時bitmap已經被畫上東西，不是空白了)
		return bitmap;
	}
	
}

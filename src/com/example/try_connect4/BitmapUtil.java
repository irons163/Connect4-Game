package com.example.try_connect4;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

public class BitmapUtil {
	
	// ��l�Ʀn������I
	public static Bitmap createSpecificSizeBitmap(Drawable drawable, int width, int height) {
		// �s�ؤ@��bitmap�A���e20�A�ϥ�ARGB_8888�]�w�A��bitmap�{�b�ť�bitmap���Dnull�C
		Bitmap bitmap = Bitmap.createBitmap(width, height,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap); // �s�صe���A�Ϊť�bitmap��e��
		drawable.setBounds(0, 0, width, height);// �]�wdrawable�����(��Ϥ����ۤv�����e)
		drawable.draw(canvas); // �b�e���W�e�W��drawable(����bitmap�w�g�Q�e�W�F��A���O�ťդF)
		return bitmap;
	}
	
}

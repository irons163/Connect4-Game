package com.example.try_connect4;

import android.content.Context;
import android.content.res.Resources;

public class ChessPointFactory implements IChessPointFactory{
	Resources resources;
	int chessPointWidth, chessPointHeight;
	
	public ChessPointFactory(Context context, int chessPointWidth, int chessPointHeight){
		resources = context.getResources();
		this.chessPointWidth = chessPointWidth;
		this.chessPointHeight = chessPointHeight;
	}
	
	@Override
	public IChessPoint createChessPointRed() {
		// TODO Auto-generated method stub
		IChessPoint chessPoint = new ChessPoint();
		chessPoint.setChessPointBitmap(BitmapUtil.createSpecificSizeBitmap(resources.getDrawable(R.drawable.red_point), chessPointWidth, chessPointHeight));
		return chessPoint;
	}

	@Override
	public IChessPoint createChessPointYellow() {
		// TODO Auto-generated method stub
		IChessPoint chessPoint = new ChessPoint();
		chessPoint.setChessPointBitmap(BitmapUtil.createSpecificSizeBitmap(resources.getDrawable(R.drawable.yellow_point), chessPointWidth, chessPointHeight));
		return chessPoint;
	}

	@Override
	public IChessPoint createChessPointRamdon(int resource) {
		// TODO Auto-generated method stub
		IChessPoint chessPoint = new ChessPoint();
		chessPoint.setChessPointBitmap(BitmapUtil.createSpecificSizeBitmap(resources.getDrawable(resource), chessPointWidth, chessPointHeight));
		return chessPoint;
	}

}

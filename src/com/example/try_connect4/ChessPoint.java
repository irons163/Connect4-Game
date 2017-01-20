package com.example.try_connect4;

import android.graphics.Bitmap;

public class ChessPoint implements IChessPoint{
	Bitmap chessPointBimap;
	
	@Override
	public Bitmap getChessPointBitmap() {
		// TODO Auto-generated method stub
		return chessPointBimap;
	}

	@Override
	public void setChessPointBitmap(Bitmap chessPointBimap) {
		// TODO Auto-generated method stub
		this.chessPointBimap = chessPointBimap;
	}

}

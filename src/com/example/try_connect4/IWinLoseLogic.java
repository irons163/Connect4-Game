package com.example.try_connect4;

import android.graphics.Point;

public interface IWinLoseLogic {
	public boolean isWin(Point p);
	public void rank();
	public void countScore();
}

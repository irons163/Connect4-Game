package com.example.try_connect4;

import java.util.List;

import android.graphics.Point;

public interface IPlayer {
	
	boolean run(Point point, Point clickPoint, List<Point> allFreePoints);
	void setThinkingTime();
	int getThinkingTime();
	void setCurrentThinkingTime();
	int getCurrentThinkingTime();
	void setCurrentMove();
	IChessPoint getChessPoint();
//	boolean isSuccessArrival();
//	boolean isAutoPlayable();
//	void doAutoPlay();
}

package com.example.try_connect4;

import java.util.Iterator;
import java.util.List;

import android.graphics.Point;
import android.view.MotionEvent;

public class HumanPlayer implements IPlayer{
	private IChessPoint chessPoint;
//	private IWinLoseLogic winLoseLogic;
	
	public HumanPlayer(IChessPoint chessPoint){
		this.chessPoint = chessPoint;
//		this.winLoseLogic = new NormalWinLoseLogic();
	}

	@Override
	public boolean run(Point point, Point clickPoint, List<Point> allFreePoints) {
		// TODO Auto-generated method stub
		return player1Run(point, clickPoint, allFreePoints);
	}
	
	private boolean player1Run(Point point, Point clickPoint, List<Point> allFreePoints){
		boolean isFinishMove = false;

			isFinishMove = true;
		
		return isFinishMove;
	}

	@Override
	public void setThinkingTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getThinkingTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCurrentThinkingTime() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getCurrentThinkingTime() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setCurrentMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IChessPoint getChessPoint() {
		// TODO Auto-generated method stub
		return chessPoint;
	}

//	@Override
//	public boolean isSuccessArrival() {
//		// TODO Auto-generated method stub
//		winLoseLogic.isSuccessArrival(this);
//		return false;
//	}
	
}

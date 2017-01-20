package com.example.try_connect4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.graphics.Point;
import android.util.Log;
import android.util.Pair;

public class AiPlayer implements IPlayer {
	private IChessPoint chessPoint;
//	private IWinLoseLogic winLoseLogic;

	public AiPlayer(IChessPoint chessPoint) {
		this.chessPoint = chessPoint;
//		this.winLoseLogic = new NormalWinLoseLogic();
		// IStrategy strategy = new Strategy();
		// IBestMoveStrategy bestMoveStrategy = new BestOnceMoveStrategy(logic,
		// clickPointX, clickPointY);
	}

	@Override
	public boolean run(Point point, Point clickPoint, List<Point> allFreePoints) {
		// TODO Auto-generated method stub
		return player1Run(point, clickPoint, allFreePoints);
	}

	private boolean player1Run(Point point, Point clickPoint,
			List<Point> allFreePoints) {
		boolean isFinishMove = false;
		if (Logic.jumps.contains(point)) {// Ê≠§Ê??ØÂê¶?Ø‰?
			movePoint(point, clickPoint, allFreePoints);
			isFinishMove = true;
		} else {
			Logic.jumps.clear();
		}

		return isFinishMove;
	}

	private void movePoint(Point point, Point clickPoint,
			List<Point> allFreePoints) {

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


//	@Override
//	public boolean isSuccessArrival() {
//		// TODO Auto-generated method stub
//		winLoseLogic.isSuccessArrival(this);
//		return false;
//	}

	int count = 0;
	
	public void doAutoPlay(PlayerManager playerManager, Logic logic, List<Point> allFreePoints) {


	}
	
	public void doAutoPlay2(PlayerManager playerManager, Logic logic, List<Point> allFreePoints) {


	}

	@Override
	public IChessPoint getChessPoint() {
		// TODO Auto-generated method stub
		return chessPoint;
	}

}

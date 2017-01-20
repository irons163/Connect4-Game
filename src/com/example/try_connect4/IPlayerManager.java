package com.example.try_connect4;

import java.util.List;

import com.example.try_connect4.GameView.JumpChessBoard;

import android.view.MotionEvent;

public interface IPlayerManager {
	void setPlayersBySquential(List<IPlayer> playersBySquential);
	void setBoard(JumpChessBoard jumpChessBoard);
	IPlayer getNextPlayer();
	IPlayer getBefforePlayer();
	IPlayer getCurrentPlayer();
	void toNextPlayer();
	void toBefforePlayer();
	boolean isAllPlayersDone();
	boolean isPlayerCanRun();
	boolean isPlayerProcessing();
	void setOnProcessing();
	void onTouchEvent(MotionEvent event);
	void setLogic(Logic logic);
}

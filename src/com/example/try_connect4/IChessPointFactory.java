package com.example.try_connect4;

public interface IChessPointFactory {
	IChessPoint createChessPointRed();
	IChessPoint createChessPointYellow();
	
	IChessPoint createChessPointRamdon(int resource);
}

package com.example.try_connect4;

import java.util.List;

public interface IChessPointManager {
	IChessPoint createChessPointRed();
	IChessPoint createChessPointYellow();
	
	List<String> getUseableChessPointList();
	
	IChessPoint createChessPonitRamdon();
}

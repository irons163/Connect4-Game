package com.example.try_connect4;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Point;

public class Logic {
	private static final int MAX_NOT_FREE_POINT_DETECTED_COUNT = 2;
	private static final int NOT_FREE_POINT_COUNT_FOR_JUMP = 1;
	private int notFreePointDetectedCount = 0;
	private final List<Point> allFreePoints;
	Point clickPoint;
	public static ArrayList<Point> jumps = new ArrayList<Point>();

	public Logic(List<Point> allFreePoints) {
		this.allFreePoints = allFreePoints;
	}

	public void startToDetected(int clickPointX, int clickPointY) {

		clickPoint = new Point(clickPointX, clickPointY);
		startToDetected(clickPointX, clickPointY, clickPoint);
	}

	private void startToDetected(int currentPointX, int currentPointY,
			Point lastDetectedPoint) {

		rightBubbleForDetected(currentPointX, currentPointY, lastDetectedPoint);
		rightTopBubbleForDetected(currentPointX, currentPointY,
				lastDetectedPoint);
		leftTopBubbleForDetected(currentPointX, currentPointY,
				lastDetectedPoint);
		leftBubbleForDetected(currentPointX, currentPointY, lastDetectedPoint);
		leftBottomBubbleForDetected(currentPointX, currentPointY,
				lastDetectedPoint);
		rightBottomBubbleForDetected(currentPointX, currentPointY,
				lastDetectedPoint);
	}

	private void pointMoveableDeteced(int currentPointX, int currentPointY,
			Point lastDetectedPoint) {

		if (notFreePointDetectedCount == MAX_NOT_FREE_POINT_DETECTED_COUNT) {
			notFreePointDetectedCount = 0;
			return;
		}

		Point currentDetectedPoint = new Point(currentPointX, currentPointY);

		if (!allFreePoints.contains(currentDetectedPoint)) {
			notFreePointDetectedCount++;
			int prepareDetectPointX = currentPointX
					- (lastDetectedPoint.x - currentPointX);
			int prepareDetectPointY = currentPointY
					- (lastDetectedPoint.y - currentPointY);

			int lastDetectedPointX = currentPointX;
			int lastDetectedPointY = currentPointY;

			pointMoveableDeteced(prepareDetectPointX, prepareDetectPointY,
					new Point(lastDetectedPointX, lastDetectedPointY));

		} else {
			if (notFreePointDetectedCount == NOT_FREE_POINT_COUNT_FOR_JUMP) {
				notFreePointDetectedCount = 0;
				if (!jumps.contains(currentDetectedPoint)) {
//					currentDetectedPoint.setJumpableChecked(true);
					jumps.listIterator().add(currentDetectedPoint);
					startToDetected(currentPointX, currentPointY,
							lastDetectedPoint);
				}
			} else if (lastDetectedPoint.equals(clickPoint)) {
				jumps.listIterator().add(currentDetectedPoint);
			}
		}
	}

	private void rightBubbleForDetected(int currentPointX, int currentPointY,
			Point lastDetectedPoint) {
		sixDirectionDetected(currentPointX, currentPointY, currentPointX + 2,
				currentPointY, lastDetectedPoint);
	}

	private void rightTopBubbleForDetected(int currentPointX,
			int currentPointY, Point lastDetectedPoint) {
		sixDirectionDetected(currentPointX, currentPointY, currentPointX + 1,
				currentPointY - 2, lastDetectedPoint);
	}

	private void leftTopBubbleForDetected(int currentPointX, int currentPointY,
			Point lastDetectedPoint) {
		sixDirectionDetected(currentPointX, currentPointY, currentPointX - 1,
				currentPointY - 2, lastDetectedPoint);
	}

	private void leftBubbleForDetected(int currentPointX, int currentPointY,
			Point lastDetectedPoint) {
		sixDirectionDetected(currentPointX, currentPointY, currentPointX - 2,
				currentPointY, lastDetectedPoint);
	}

	private void leftBottomBubbleForDetected(int currentPointX,
			int currentPointY, Point lastDetectedPoint) {
		sixDirectionDetected(currentPointX, currentPointY, currentPointX - 1,
				currentPointY + 2, lastDetectedPoint);
	}

	private void rightBottomBubbleForDetected(int currentPointX,
			int currentPointY, Point lastDetectedPoint) {
		sixDirectionDetected(currentPointX, currentPointY, currentPointX + 1,
				currentPointY + 2, lastDetectedPoint);
	}

	private void sixDirectionDetected(int currentPointX, int currentPointY,
			int prepareDetectPointX, int prepareDetectPointY,
			Point lastDetectedPoint) {
		if (lastDetectedPoint.x == prepareDetectPointX
				&& lastDetectedPoint.y == prepareDetectPointY)
			return;
		pointMoveableDeteced(prepareDetectPointX, prepareDetectPointY,
				new Point(currentPointX, currentPointY));
	}

	private int toDetectedShortestDistance(Point startPoint, Point endPoint,
			List<Point> allMovablePointsOnBoard) {
		int currentPointX = startPoint.x;
		int currentPointY = startPoint.y;
		int targetPointX = endPoint.x;
		int targetPointY = endPoint.y;
		int distanceCount = 0;
		if (startPoint.equals(endPoint)) {
			return 0;
		}

		if (currentPointY > targetPointY) {
			if (currentPointX > targetPointX) {
				// leftTop
				Point localPoint7;
				Point localPoint8;
				localPoint7 = new Point(currentPointX - 1, currentPointY - 2);
				localPoint8 = new Point(currentPointX + 1, currentPointY - 2);
				if (allMovablePointsOnBoard.contains(localPoint7))
					distanceCount = toDetectedShortestDistance(localPoint7,
							endPoint, allMovablePointsOnBoard);
				else if (allMovablePointsOnBoard.contains(localPoint8))
					distanceCount = toDetectedShortestDistance(localPoint8,
							endPoint, allMovablePointsOnBoard);
				else
					distanceCount = toDetectedShortestDistance(new Point(
							currentPointX - 2, currentPointY), endPoint,
							allMovablePointsOnBoard);
			} else {
				// rightTop
				Point localPoint9 = new Point(currentPointX + 1,
						currentPointY - 2);
				Point localPoint10 = new Point(currentPointX - 1,
						currentPointY - 2);
				if (allMovablePointsOnBoard.contains(localPoint9))
					distanceCount = toDetectedShortestDistance(localPoint9,
							endPoint, allMovablePointsOnBoard);
				else if (allMovablePointsOnBoard.contains(localPoint10))
					distanceCount = toDetectedShortestDistance(localPoint10,
							endPoint, allMovablePointsOnBoard);
				else
					distanceCount = toDetectedShortestDistance(new Point(
							currentPointX + 2, currentPointY), endPoint,
							allMovablePointsOnBoard);
			}
		} else if (currentPointY < targetPointY) {
			if (currentPointX > targetPointX) {
				//leftBottom
				Point point = new Point(currentPointX - 1, currentPointY + 2);
				Point localPoint4 = new Point(currentPointX + 1,
						currentPointY + 2);
				if (allMovablePointsOnBoard.contains(point))
					distanceCount = toDetectedShortestDistance(point, endPoint,
							allMovablePointsOnBoard);
				else if (allMovablePointsOnBoard.contains(localPoint4))
					distanceCount = toDetectedShortestDistance(localPoint4,
							endPoint, allMovablePointsOnBoard);
				else
					distanceCount = toDetectedShortestDistance(new Point(
							currentPointX - 2, currentPointY), endPoint,
							allMovablePointsOnBoard);
			} else {
				//rightBottom
				Point point = new Point(currentPointX + 1, currentPointY + 2);
				Point localPoint6 = new Point(currentPointX - 1,
						currentPointY + 2);
				if (allMovablePointsOnBoard.contains(point))
					distanceCount = toDetectedShortestDistance(point, endPoint,
							allMovablePointsOnBoard);
				else if (allMovablePointsOnBoard.contains(localPoint6))
					distanceCount = toDetectedShortestDistance(localPoint6,
							endPoint, allMovablePointsOnBoard);
				else
					distanceCount = toDetectedShortestDistance(new Point(
							currentPointX + 2, currentPointY), endPoint,
							allMovablePointsOnBoard);
			}
		} else {

			if (currentPointX > targetPointX) {
				//left
				Point point = new Point(currentPointX - 2, currentPointY);
				if (allMovablePointsOnBoard.contains(point))
					distanceCount = toDetectedShortestDistance(point, endPoint,
							allMovablePointsOnBoard);
				else
					distanceCount = toDetectedShortestDistance(new Point(
							currentPointX + 2, currentPointY), endPoint,
							allMovablePointsOnBoard);
			} else {
				//right
				Point point = new Point(currentPointX + 2, currentPointY);
				if (allMovablePointsOnBoard.contains(point))
					distanceCount = toDetectedShortestDistance(point, endPoint,
							allMovablePointsOnBoard);
				else
					distanceCount = toDetectedShortestDistance(new Point(
							currentPointX - 2, currentPointY), endPoint,
							allMovablePointsOnBoard);
			}
		}
		// }

		// // right
		// Point point = new Point(currentPointX + 2, currentPointY);
		// if(allMovablePointsOnBoard.contains(point))
		// distanceCount = toDetectedShortestDistance(point, endPoint,
		// allMovablePointsOnBoard);
		// else{
		// point = new Point(currentPointX - 2, currentPointY);
		// distanceCount = toDetectedShortestDistance(point, endPoint,
		// allMovablePointsOnBoard);
		// }
		// }
		// }

		return ++distanceCount;
	}

	public int startToDetectedShortestDistance(Point startPoint,
			Point endPoint, List<Point> allMovablePointsOnBoard) {
		return toDetectedShortestDistance(startPoint, endPoint,
				allMovablePointsOnBoard);
	}
}

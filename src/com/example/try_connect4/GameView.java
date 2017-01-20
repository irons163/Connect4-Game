package com.example.try_connect4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
	private boolean isGameRun = true;
	private Context context;
	private int width, height;
	private int widthPaddingDimension;
	private int maxX, maxY;
	private int lineDistance;
	private SurfaceHolder surfaceHolder;
	JumpChessBoard jumpChessBoard;
	public static Object LOCK = new Object();
	IPlayerManager playerManager;
	
	private List<IPlayer> players = new ArrayList<IPlayer>();

	private static final int RED = 0;// 紅色點(紅色棋子)
	private static final int YELLOW = 1;

	// 畫棋盤
	private List<Line> lines = new ArrayList<Line>();// 此Line集合在onSizeChange時已被初始化，內有數條線(EX:25)

	
	
	public GameView(Context context, int width, int height) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.width = width;
		this.height = height;

		initChessLine(width, width);

		surfaceHolder = getHolder();
		surfaceHolder.addCallback(this);
	}

	private void initChessLine(int width, int height) {
		// maxX = (int) Math.floor(width / 8); // 取比此值大的最大整數(不大過自身)，也就是強制捨位
		// // 480/20=24 470/20=23.5
		// // >>
		// // 23
		// maxY = (int) Math.floor(width / 8);

		// // 设置X、Y座标微调值，目的整个框居中
		// xOffset = ((width - (lineDistance * maxX)) / 2); // (480 - 20*24)/2=0
		// // (470-20*23)/2=10/2=5
		// yOffset = ((width - (lineDistance * maxY)) / 2);

		maxX = 8;
		maxY = 7;

		// int widthPaddingPersent = 1;

		lineDistance = width / (maxX);
		widthPaddingDimension = lineDistance / 2;

		jumpChessBoard = new JumpChessBoard(maxX, maxY, 0, 0, lineDistance);

		// 創建棋盤上的線條
		jumpChessBoard.createLines();

		 jumpChessBoard.createPoints();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub

		if (event.getAction() == MotionEvent.ACTION_DOWN) {
//			Point point = jumpChessBoard.newPoint(event.getX(), event.getY());
//			if (jumpChessBoard.getAllFreePoints().contains(point)) {
//				// anim
//				jumpChessBoard.getAllFreePoints().remove(point);
				playerManager.onTouchEvent(event);
//			}
		}

		return true;
	}

	private void process() {

	}

	private void draw() {
		Canvas canvas = surfaceHolder.lockCanvas();

		canvas.drawColor(Color.BLACK);

		drawChssboardLines(canvas);
		drawAllExistPoints(canvas);

		surfaceHolder.unlockCanvasAndPost(canvas);
	}

	private void drawChssboardLines(Canvas canvas) {
		// 設置畫線時的顏色(棋盤的格子線)
		Paint paint = new Paint();
		paint.setColor(Color.LTGRAY);
		for (Line line : lines) {
			// 在View本身的畫布上畫線
			canvas.drawLine(line.xStart, line.yStart, line.xStop, line.yStop,
					paint);
		}
	}

	// 畫點(畫棋子)
	private void drawPoint(Canvas canvas, Point p, Bitmap pointBmp) {
		canvas.drawBitmap(pointBmp, p.x * lineDistance + lineDistance / 2, p.y
				* lineDistance + lineDistance / 2, null);
	}
	
	private void drawAllExistPoints(Canvas canvas) {
		for (int i = 0; i < jumpChessBoard.allExistPoints.length; i++) { // 畫所有舊棋子
			for(int j = 0; j < jumpChessBoard.allExistPoints[i].length; j++){
				if(jumpChessBoard.allExistPoints[i][j]==0)
					continue;
//				drawPoint(canvas, new Point(j, i), pointArray[jumpChessBoard.allExistPoints[i][j]-1]);
				drawPoint(canvas, new Point(i, j), players.get(jumpChessBoard.allExistPoints[i][j]-1).getChessPoint().getChessPointBitmap());
			}
		}
	}

	// 點(4種類)的Bigmap陣列
	private Bitmap[] pointArray = new Bitmap[10];

	// 初始化好紅綠兩點
	public void fillPointArrays(int color, Drawable drawable) {
		// 新建一個bitmap，長寬20，使用ARGB_8888設定，此bitmap現在空白bitmap但非null。
		Bitmap bitmap = Bitmap.createBitmap(lineDistance, lineDistance,
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap); // 新建畫布，用空白bitmap當畫布
		drawable.setBounds(0, 0, lineDistance, lineDistance);// 設定drawable的邊界(原圖片有自己的長寬)
		drawable.draw(canvas); // 在畫布上畫上此drawable(此時bitmap已經被畫上東西，不是空白了)
		pointArray[color] = bitmap; // 將此bitmap存入點陣列中(共4種點)
	}

	Thread gameThread = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (isGameRun) {
				process();
				draw();
			}
		}
	});

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		Resources r = this.getContext().getResources();
		fillPointArrays(RED, r.getDrawable(R.drawable.red_point));
		fillPointArrays(YELLOW, r.getDrawable(R.drawable.yellow_point));

//		allExistPoints = new ArrayList<List<Point>>();

		
//		pointGroupManager.setAllExistPoints(allExistPoints);
//		pointGroupManager.createPointGroup(pointArray[RED], 0);
//		pointGroupManager.createPointGroup(pointArray[YELLOW], 1);

		// logic = new Logic(allFreePoints);
		
		ChessPointManager chessPointManager = new ChessPointManager(context, lineDistance, lineDistance);
		
		players.add(new HumanPlayer(chessPointManager.createChessPointRed()));
		players.add(new HumanPlayer(chessPointManager.createChessPointYellow()));

		playerManager = new PlayerManager(jumpChessBoard);
		playerManager.setPlayersBySquential(players);
		playerManager.setBoard(jumpChessBoard);

//		playerManager.setAllExistPoints();
//		playerManager.setPointGroupManager(pointGroupManager);
		// playerManager.setLogic(logic);

		gameThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub

	}

	// 線類別
	class Line {
		float xStart, yStart, xStop, yStop;

		// 建構子
		public Line(float xStart, float yStart, float xStop, float yStop) {
			// onSizeChange初始化時，把各個座標傳入(開始的xy座標到結束的xy座標)
			this.xStart = xStart;
			this.yStart = yStart;
			this.xStop = xStop;
			this.yStop = yStop;
		}
	}

	class JumpChessBoard {
		private int maxX;
		private int maxY;
		private int xOffset;
		private int yOffset;
		private int lineDistance;
//		private List<Point> allFreePoints;
		private int[][] allExistPoints = new int[7][6];

		public JumpChessBoard(int maxX, int maxY, int xOffset, int yOffset,
				int lineDistance) {
			this.maxX = maxX;
			this.maxY = maxY;
			this.xOffset = xOffset;
			this.yOffset = yOffset;
			this.lineDistance = lineDistance;
//			allFreePoints = new ArrayList<Point>();
		}

		// 產生棋盤上所有的線
		public void createLines() {
			for (int i = 0; i < maxX; i++) {// 豎線 0-24 共25條
				// (5+0-10) (240+20-10) (-5+480-10)
				lines.add(new Line((i + 1) * lineDistance
						- widthPaddingDimension, widthPaddingDimension, (i + 1)
						* lineDistance - widthPaddingDimension, maxY
						* lineDistance - widthPaddingDimension));
			}
			for (int i = 0; i < maxY; i++) {// 橫線
				lines.add(new Line(widthPaddingDimension, (i + 1)
						* lineDistance - widthPaddingDimension, maxX
						* lineDistance - widthPaddingDimension, (i + 1)
						* lineDistance - widthPaddingDimension));
			}
		}

		public void createPoints() {
//			allFreePoints.clear(); // 所有空白點集合先清空(因為重新開始了)
//			for (int i = 0; i < maxX; i++) { // 比線少1
//				for (int j = 0; j < maxY; j++) {
//					allFreePoints.add(new Point(i, j));// 空白點集合
//				}
//			}
			
			for(int i = 0 ; i < maxX-1 ; i++){
				for(int j = 0 ; j < maxY-1; j++){
					allExistPoints[i][j] = 0;
				}
			}
		}

		// 根據觸摸點座標找到對應點
		public int newPoint(Float x, Float y) {
//			Point p = new Point(-1, -1);// 創建橫軸編號為0(橫軸的第一個點)，縱軸編號也為0(縱軸的第一個點)的點
			int positionX = -1;
			if(y >= widthPaddingDimension + yOffset && y <= 6*lineDistance + widthPaddingDimension + yOffset)
			for (int i = 0; i < maxX-1; i++) {// 0-23 共24點
				// (0-5)<0 0<(20-5)
				if ((i * lineDistance + widthPaddingDimension + xOffset) <= x
						&& x < ((i + 1) * lineDistance + widthPaddingDimension + xOffset)) {
					// p.setX(i);//設定p的x為i，也就是橫軸第i+1個點
					positionX = i;
				}
			}

			return positionX; // 回傳 ponit p
		}

		public int getMaxX() {
			return maxX;
		}

		public void setMaxX(int maxX) {
			this.maxX = maxX;
		}

		public int getMaxY() {
			return maxY;
		}

		public void setMaxY(int maxY) {
			this.maxY = maxY;
		}

		public int getxOffset() {
			return xOffset;
		}

		public void setxOffset(int xOffset) {
			this.xOffset = xOffset;
		}

		public int getyOffset() {
			return yOffset;
		}

		public void setyOffset(int yOffset) {
			this.yOffset = yOffset;
		}

		public int getLineDistance() {
			return lineDistance;
		}

		public void setLineDistance(int lineDistance) {
			this.lineDistance = lineDistance;
		}

		public int[][] getAllExistPoints() {
			return allExistPoints;
		}

		public void setAllExistPoints(int[][] allExistPoints) {
			this.allExistPoints = allExistPoints;
		}

	}

}

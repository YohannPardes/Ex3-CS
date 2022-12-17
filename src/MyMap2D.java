import java.util.ArrayList;

/**
 * This class implements the Map2D interface.
 * The available actions are:
 * Fill - filling a space.
 * drawRect - drawing a rectangle.
 * drawCircle - drawing a circle.
 * drawSegment - drawing a line.
 * shortestPath - finding the shortest path between 2 points.
 * */
public class MyMap2D implements Map2D{
	private int[][] _map;
	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}
	public MyMap2D(int[][] data) {
		this(data.length, data[0].length);
		init(data);
	}

	/**
	 * Initialize MyMap2D._map attribute as an 2D array
	 * @param w the number of rows
	 * @param h the number of columns
	 */
	@Override
	public void init(int w, int h) {
		_map = new int[w][h];
	}

	/**
	 * Setting all the 2DPoints color in _map to white color (255, 255, 255)
	 * @param arr _map
	 */
	@Override
	public void init(int[][] arr) {
		init(arr.length,arr[0].length);
		for(int x = 0;x<this.getWidth()&& x<arr.length;x++) {
			for(int y = 0; y<this.getHeight()&& y<arr[0].length;y++) {
				this.setPixel(x, y, WHITE);
			}
		}
	}

	/**
	 * Give the width (lenght of each row) of _map attribute
	 * @return the width of _map matrice
	 */
	@Override
	public int getWidth() {return _map.length;}
	/**
	 * Give the height (number of rows) of _map attribute
	 * @return the width of _map matrice
	 */
	@Override
	public int getHeight() {return _map[0].length;}

	/**
	 * Given a x, y coordinates return corresponding pixel value
	 * @param x x coordinate
	 * @param y y coordinate
	 * @return pixel value
	 */
	@Override
	public int getPixel(int x, int y) { return _map[x][y];}
	/**
	 * Given a 2Dpoint return the corresponding pixel value at it's x, y coordinates
	 * @param p the 2DPoint from which check the pixel val
	 * @return pixel value
	 */
	@Override
	public int getPixel(Point2D p) {
		return this.getPixel(p.ix(),p.iy());
	}

	/**
	 * Given an x, y coordinates change corresponding pixel value to the wanted color
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param v the wanted color
	 * @return pixel value
	 */
	public void setPixel(int x, int y, int v) {_map[x][y] = v;}

	/**
	 * Given a 2DPoint change the color at his x, y coordinates
	 * @param p The Point2D from which check the color
	 * @param v the wanted color
	 * @return pixel value
	 */
	public void setPixel(Point2D p, int v) {
		setPixel(p.ix(), p.iy(), v);
	}

	/**
	 *
	 * @return  _the map attribute
	 */
	public int[][] getMap(){
		return this._map;
	}

	/**
	 * This function draw a 2D line given a starting point and an ending point and a color.
	 * This is an implementation of Bresenham’s line Algorithm.*
	 * Link - https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm
	 * @param p1 Starting point (Point2D)
	 * @param p2 Starting point (Point2D)
	 * @param col The wanted color for the line (int)
	 */
	@Override
	public void drawSegment(Point2D p1, Point2D p2, int col) {
		//drawing starting and ending points

		int x0 = p1.ix();
		int x1 = p2.ix();

		int y0 = p1.iy();
		int y1 = p2.iy();


		int deltaX = Math.abs(x1 - x0);
		int Xincr = x0 < x1 ? 1 : -1;
		int deltaY = -1 * Math.abs(y1 - y0);
		int Yincr = y0 < y1 ? 1 : -1;
		int error = deltaX + deltaY;
		int e2;

		while (true) {
			this.setPixel(x0, y0, col);
			if (x0 == x1 && y0 == y1) {break;}

			e2 = 2 * error;

			if (e2 >= deltaY) {
				if (x0 == x1) {
					break;
				}
				error = error + deltaY;
				x0 = x0 + Xincr;
			}

			if (e2 <= deltaX){
				if (y0 == y1) {break;}
				error = error + deltaX;
				y0 = y0 + Yincr;
			}
		}
	}

	/**
	 * Given 2 points this function draw a filled rectangle on the 2Dmap.
	 * @param p1 first clicked point
	 * @param p2 secondly clicked point
	 * @param col the wanted color for filling the square
	 */
	@Override
	public void drawRect(Point2D p1, Point2D p2, int col) {

		//drawing all the points in the square
		int deltaX = Math.abs(p1.ix() - p2.ix());
		int deltaY = Math.abs(p1.iy() - p2.iy());
		int bottomLeftX = Math.min(p1.ix(), p2.ix());
		int bottomLeftY = Math.min(p1.iy(), p2.iy());

		for (int x = 0; x < deltaX + 1; x++) {
			for (int y = 0; y < deltaY + 1; y++) {
				setPixel(bottomLeftX + x, bottomLeftY + y, col);
			}
		}
	}

	/**
	 * Given a point and a radius draw a circle
	 *
	 * @param p the center of the circle
	 * @param rad The radius of the wanted circle
	 * @param col The color of the circle
	 */
	@Override
	public void drawCircle(Point2D p, double rad, int col) {

		for (int x = (int)(-1*rad); x < rad + 1; x++) {
			for (int y = (int)(-1*rad); y < rad + 1; y++) {
				Point2D currentP = new Point2D(p.ix()+x, p.iy()+y);

				boolean isInbound = ((0<=currentP.ix() & currentP.ix()<getWidth()) & (0<=currentP.iy() & currentP.iy()<getHeight()));
				if ((isInbound) && (dist(p, currentP)<rad)){

					setPixel(currentP, col);
				}
			}
		}
	}

	/**
	 * Given 2 points as x, y coordinates return the squared distance between this two points
	 * @param p1  first point
	 * @param p2  second point
	 * @return squaredDistance
	 */
	public static double dist(Point2D p1, Point2D p2){
		double dx = Math.pow((p1.ix() - p2.ix()), 2);
		double dy = Math.pow((p1.iy() - p2.iy()), 2);
		return Math.sqrt(dx + dy);
	}

	/**
	 * Given point2D object, the wanted color, and the changed color,
	 * 	the function fill the area with the wanted color using a wave propagation like algorithm
	 * @param p The chosen point for filling the area
	 * @param newCol The color that will replace the old color
	 * @return 0
	 */
	@Override
	public int fill(Point2D p, int newCol) {
		MyMap2D tempMap = initMap(p);
		ArrayList<Point2D> nextSteps; // the array holding all the neighbors

		int currentStep = 0;
		boolean update = true;
		while (update) {
			update = false;
			for (int x = 0; x < tempMap.getWidth(); x++) {
				for (int y = 0; y < tempMap.getHeight(); y++) {
					nextSteps = tempMap.getValidNeighbors(x, y);
					for (Point2D neighbor : nextSteps) {
						//update the neighbors of each not blank cell (-1)
						if (tempMap.getPixel(neighbor) == currentStep && tempMap.getPixel(x, y) == -1) {
							tempMap.setPixel(x, y, currentStep + 1);
							update = true;
						}
					}
				}
			}
			currentStep += 1;
		}
		this.drawMap(tempMap, newCol);

		return currentStep;
	}

	/**
	 * Draw all the positive value of the map for MyMap2D.fill
	 * @param MyMap  Processed map from fill func
	 * @param color The color to draw
	 *
	 * This function is a sub part of MyMap2D.fill
	 */
	private void drawMap(MyMap2D MyMap, int color){

		for (int x = 0; x < MyMap.getWidth(); x++) {
			for (int y = 0; y < MyMap.getHeight(); y++) {
				if (MyMap.getPixel(x, y) >= 0){
					this.setPixel(x, y, color);
				}
			}
		}
	}

	/**
	 Given x, y coordinate, the wanted color, and the changed color,
	 * 	the function fill the area with the wanted color
	 * @param x The x coordinate of the chosen point
	 * @param y The y coordinate of the chosen point
	 * @param newCol The color that will replace the old color
	 * @return 0
	 */
	@Override
	public int fill(int x, int y, int newCol) {
		int a = 0;
		return 0;
	}


	/**
	 * This function draw the shortest path between two 2D points
	 * @param p1  the first point
	 * @param p2  the second points
	 * @return - an array of 2D points
	 */
	@Override
	public Point2D[] shortestPath(Point2D p1, Point2D p2){
		// TODO Auto-generated method stub

		MyMap2D tempMap = this.initShortestPathMap(p1, p2);
		forwardSearch(p2, tempMap);
		return backwardTracking(p2, tempMap);
	}

	/**
	 * This function is a sub part of MyMap2D.shortestPath function
	 * Given a processed MyMap2D using MyMap2D.initShortestPathMap updating the map until reaching the end point
	 * using a simple wave propagation algorithm
	 * @param p2 the ending point of the wanted path
	 * @param tempMap The pre-processed map from MyMap2D.initShortestPathMap
	 * @return the number of steps required to complete the path or -1 if there is no valid path
	 */
	private static int forwardSearch(Point2D p2, MyMap2D tempMap) {
		ArrayList<Point2D> nextSteps; // the array holding all the neighbors

		int currentStep = 0;
		boolean update = true;
		while (update) {
			update = false;
			for (int x = 0; x < tempMap.getWidth(); x++) {
				for (int y = 0; y < tempMap.getHeight(); y++) {
					nextSteps = tempMap.getValidNeighbors(x, y);
					for (Point2D neighbor : nextSteps) {
						//update the neighbors of each not blank cell (-1)
						if (tempMap.getPixel(neighbor) == currentStep && tempMap.getPixel(x, y) == -1) {
							tempMap.setPixel(x, y, currentStep + 1);
							update = true;

							if (tempMap.checkEnd(p2)) { //getting out of the loops
								return currentStep;
							}
						}
					}
				}
			}
			currentStep += 1;
		}
		return -1;
	}
	/**
	 * This function is a sub part of MyMap2D.shortestPath function
	 * Given a processed pathMap using MyMap2D.shortestPath return the actual shortest path as a Point2D array
	 * @param p2  The ending point of the path
	 * @param tempMap  MyMap2D.shortestPath's processed tempMap
	 * @return The shortest path as a Point2D array
	 */
	private Point2D[] backwardTracking(Point2D p2, MyMap2D tempMap){
		ArrayList <Point2D> shortestPath = new ArrayList<>();
		shortestPath.add(p2);

		Point2D currentPoint = p2;
		while (tempMap.getPixel(currentPoint) != 0) {

			Point2D savedP = null;
			int min = this.getWidth() * this.getWidth(); // initializing at a known maximum value
			for (Point2D neighbor : getValidNeighbors(currentPoint)) {

				int neighborVal = tempMap.getPixel(neighbor);
				if (neighborVal >= 0 && neighborVal < min) {
					min = neighborVal;
					savedP = neighbor;
				}
			}
			shortestPath.add(savedP);
			currentPoint = savedP;

			if (currentPoint == null){
				System.out.println("There is no path between those points");
				return new Point2D[0];
			}
		}

		return shortestPath.toArray(new Point2D[shortestPath.size()]); //converting to regular array
	}

	/**
	 * Given a MyMap2D object this function initialize a MyMap2D object for pathfinding algorithm where :
	 * 0 is the starting point
	 * -1 is an empty space
	 * -2 is a wall
	 * -10 is the ending point
	 * @param p1 the starting point
	 * @param p2 the ending point
	 * @return the processed MyMap2D
	 */
	private MyMap2D initShortestPathMap(Point2D p1, Point2D p2) {
		MyMap2D map = initMap(p1);
		map.setPixel(p2, -10);
		return map;
	}

	private MyMap2D initMap(Point2D p){
		MyMap2D tempMap = new MyMap2D(this.getHeight());

		int clickedColor = this.getPixel(p);
		for (int x = 0; x<this.getWidth(); x+=1) {
			for (int y = 0; y<this.getWidth(); y+=1) {
				tempMap.setPixel(x, y, -1);

				if (this.getPixel(x, y) != clickedColor) {
					tempMap.setPixel(x, y, -2);
				}
			}
		}

		// starting point
		tempMap.setPixel(p, 0);
		return tempMap;
	}
	/**
	 * This function should be used as a sub part of the MyMap2D.shortestPath function
	 * Given an x, y coordinates return is 4 neighbors (not diagonally) if they are valid to be in the path
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return The valid neighbors as an ArrayList of Point2D objects
	 */
	private ArrayList<Point2D> getValidNeighbors(int x, int y) {
		ArrayList<Point2D> neighbors = new ArrayList<>();

		// check right
		Point2D rightN = new Point2D(x + 1, y);
		if (isIn(rightN) && this.getPixel(rightN) != -2) {
			neighbors.add(rightN);
		}

		// check left
		Point2D leftN = new Point2D(x - 1, y);
		if (isIn(leftN) && this.getPixel(leftN) != -2) {
			neighbors.add(leftN);
		}

		// check up
		Point2D upN = new Point2D(x, y + 1);
		if (isIn(upN) && this.getPixel(upN) != -2) {
			neighbors.add(upN);
		}

		// check down
		Point2D downN = new Point2D(x, y - 1);
		if (isIn(downN) && this.getPixel(downN) != -2) {
			neighbors.add(downN);
		}

		return neighbors;
	}
	/**
	 * This function should be used as a sub part of the MyMap2D.shortestPath function
	 * Given a Point2D object return is 4 neighbors (not diagonally) if they are valid to be in the path
	 * @param p The processed Point2D
	 * @return The valid neighbors as an ArrayList of Point2D objects
	 */
	private ArrayList<Point2D> getValidNeighbors(Point2D p){
		return getValidNeighbors(p.ix(), p.iy());
	}

	/**
	 * This function determine if the ending point have been reached
	 * @param endPoint The ending point
	 * @return true/false the ending point has been reached/ not been reached
	 */
	private boolean checkEnd(Point2D endPoint){
		for (Point2D neighbor :getValidNeighbors(endPoint)){
			if (this.getPixel(neighbor) >= 0){
				return true;
			}
		}
		return false;
	}
	/**
	 * This function check if a Point2D is in the _map array attribute
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return true/false
	 */
	private boolean isIn(int x, int y){
		boolean xIn = (x>=0 && x<this.getWidth());
		boolean yIn = (y>=0 && y<this.getHeight());

		return xIn && yIn;
	}
	/**
	 * This function check if a Point2D is in the _map array attribute
	 * @param p The given Point2D
	 * @return true/false
	 */
	private boolean isIn(Point2D p){

		return isIn(p.ix(), p.iy());
	}

	/**
	 * Using the MyMap2D.forwardSearch return the length of the shortest path
	 * @param p1 the starting point
	 * @param p2 the ending point
	 * @return the lenght of the path
	 */
	@Override
	public int shortestPathDist(Point2D p1, Point2D p2) {
		MyMap2D tempMap = this.initShortestPathMap(p1, p2);
		return forwardSearch(p2, tempMap);
	}

	@Override
	public void nextGenGol() {
		// TODO Auto-generated method stub

	}

	/**
	 * This function is filling the whole _map attribute by a single color
	 * @param c - The color by which the map will be filled
	 */
	@Override
	public void fill(int c) {
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);
			}
		}
	}
}

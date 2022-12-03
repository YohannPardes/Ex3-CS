
/**
 * This class implements the Map2D interface.
 * You should change (implement) this class as part of Ex3. */
public class MyMap2D implements Map2D{
	private int[][] _map;
	public MyMap2D(int w, int h) {init(w,h);}
	public MyMap2D(int size) {this(size,size);}
	public MyMap2D(int[][] data) {
		this(data.length, data[0].length);
		init(data);
	}
	@Override
	public void init(int w, int h) {
		_map = new int[w][h];

	}
	@Override
	public void init(int[][] arr) {
		init(arr.length,arr[0].length);
		for(int x = 0;x<this.getWidth()&& x<arr.length;x++) {
			for(int y=0;y<this.getHeight()&& y<arr[0].length;y++) {
				this.setPixel(x, y, WHITE);
			}
		}
	}
	@Override
	public int getWidth() {return _map.length;}
	@Override
	public int getHeight() {return _map[0].length;}
	@Override
	public int getPixel(int x, int y) { return _map[x][y];}
	@Override
	public int getPixel(Point2D p) {
		return this.getPixel(p.ix(),p.iy());
	}

	public void setPixel(int x, int y, int v) {_map[x][y] = v;}
	public void setPixel(Point2D p, int v) {
		setPixel(p.ix(), p.iy(), v);
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
				if ((isInbound) && (calcDSquared(p, currentP)<rad)){

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
	public static double calcDSquared(Point2D p1, Point2D p2){
		double dx = Math.pow((p1.ix() - p2.ix()), 2);
		double dy = Math.pow((p1.iy() - p2.iy()), 2);
		return dx + dy;
	}

	/**
	 * Given point2D object, the wanted color, and the changed color,
	 * 	the function fill the area with the wanted color
	 * @param p The chosen point for filling the area
	 * @param newCol The color that will replace the old color
	 * @return 0
	 */
	@Override
	public int fill(Point2D p, int newCol) {

		int filledCol = getPixel(p);
		fillRec(p.ix(), p.iy(), newCol, filledCol);
		return 0;
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
		int filledCol = getPixel(x, y);
		fillRec(x, y, newCol, filledCol);
		return 0;
	}

	/**
	 *Given x, y coordinate, the wanted color, and the changed color,
	 *this recursive function draw the inner part of a close form
	 *Warning : reaching stack overflow for 160*160 grid size (25600 potential calls)
	 *(this function is a sub part of the fill function)
	 */
	public void fillRec(int x, int y, int newCol, int oldCol){

		this.setPixel(x, y, newCol);

		// up
		if ((0 <= y+1 & y+1< getHeight()) && this.getPixel(x, y + 1) == oldCol){
			fillRec(x, y + 1, newCol, oldCol);
		}

		// down
		if ((0 <= y-1 & y-1< getHeight()) && this.getPixel(x, y - 1) == oldCol){
			fillRec(x, y - 1, newCol, oldCol);
		}

		// right
		if ((0 <= x+1 & x+1< getWidth()) && this.getPixel(x + 1, y) == oldCol){
			fillRec(x + 1, y, newCol, oldCol);
		}

		// left
		if ((0 <= x-1 & x-1< getWidth()) && this.getPixel(x - 1, y) == oldCol){
			fillRec(x - 1, y, newCol, oldCol);
		}
	}

	@Override
	public Point2D[] shortestPath(Point2D p1, Point2D p2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int shortestPathDist(Point2D p1, Point2D p2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void nextGenGol() {
		// TODO Auto-generated method stub

	}

	@Override
	public void fill(int c) {
		for(int x = 0;x<this.getWidth();x++) {
			for(int y = 0;y<this.getHeight();y++) {
				this.setPixel(x, y, c);
			}
		}
	}
}

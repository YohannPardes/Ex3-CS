
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

	/*
	This function draw a 2D line given a starting point and an ending point and a color.
	This is an implementation of Bresenham’s line Algorithm - https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm

	p1 - Starting point (Point2D)
	p2 - Starting point (Point2D)
	v - The wanted color for the line (int)
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

	@Override
	public void drawRect(Point2D p1, Point2D p2, int col) {

		// drawing the 4 vertices of the square
		drawSegment(p1, new Point2D(p1.ix(), p2.iy()), col);
		drawSegment(p1, new Point2D(p2.ix(), p1.iy()), col);
		drawSegment(p2, new Point2D(p1.ix(), p2.iy()), col);
		drawSegment(p2, new Point2D(p2.ix(), p1.iy()), col);

		// filling the square
		
	}

	@Override
	public void drawCircle(Point2D p, double rad, int col) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int fill(Point2D p, int new_v) {

		int startingCLR = getPixel(p);


		return 0;
	}

	@Override
	public int fill(int x, int y, int new_v) {
		// TODO Auto-generated method stub
		return 0;
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

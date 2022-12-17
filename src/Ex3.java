import java.awt.Color;

/**
 * This class is a simple "inter-layer" connecting (aka simplifying) the
 * StdDraw_Ex3 with the Map2D interface.
 * Written for 101 java course it uses simple static functions to allow a 
 * "Singleton-like" implementation.
 * You should change this class!
 *
 */
public class Ex3 {

	private static  Map2D _map = null;
	private static Color _color = Color.blue;
	private static String _mode = "";
	public static final int WHITE = Color.WHITE.getRGB();
	public static final int BLACK = Color.BLACK.getRGB();
	public static Point2D tempP = null;
	public static String tempM = "";
	public static boolean GOL = false;
	public static void main(String[] args) {
		int dim = 10;  // init matrix (map) 10*10
		init(dim);
	}
	private static void init(int x) {
		StdDraw_Ex3.clear();
		_map = new MyMap2D(x);
		StdDraw_Ex3.setScale(-0.5, _map.getHeight()-0.5);
		StdDraw_Ex3.enableDoubleBuffering();
		_map.fill(WHITE);
		drawArray(_map);		
	}
	 public static void drawGrid(Map2D map) {
		 int w = map.getWidth();
		 int h = map.getHeight();
		 for(int i=0;i<w;i++) {
			 StdDraw_Ex3.line(i, 0, i, h);
		 }
		 for(int i=0;i<h;i++) {
			 StdDraw_Ex3.line(0, i, w, i);
		 }
	}
	static public void drawArray(Map2D a) {
		StdDraw_Ex3.clear();
		StdDraw_Ex3.setPenColor(Color.gray);
		drawGrid(_map);
		for(int y=0;y<a.getWidth();y++) {
			for(int x=0;x<a.getHeight();x++) {
				int c = a.getPixel(x, y);
				StdDraw_Ex3.setPenColor(new Color(c));
				drawPixel(x,y);
			}
		}		
		StdDraw_Ex3.show();
	}
	public static void actionPerformed(String p) {
		_mode = p;

		//colors
		if(p.equals("Blue")) {_color = Color.BLUE; _mode = tempM;}
		if(p.equals("White")) {_color = Color.WHITE;  _mode = tempM;}
		if(p.equals("Black")) {_color = Color.BLACK;  _mode = tempM;}
		if(p.equals("Yellow")) {_color = Color.YELLOW;  _mode = tempM;}
		if(p.equals("Green")) {_color = Color.GREEN;  _mode = tempM;}
		if(p.equals("Red")) {_color = Color.RED;  _mode = tempM;}

		//resolutions
		if(p.equals("20x20")) {init(20);}
		if(p.equals("40x40")) {init(40);}
		if(p.equals("80x80")) {init(80);}
		if(p.equals("160x160")) {init(160);}

		// CLEAR
		if(p.equals("Clear")) _map.fill(WHITE);

		drawArray(_map);
		
	}
	public static void mouseClicked(Point2D p) {
		System.out.println(p);
		int col = _color.getRGB();

		//resetting the temporary values if the mode is changed
		if (!(_mode.equals(tempM))){
			if (_mode.equals("Gol")){GOL = true; MyMap2D.initGOL(_map);}
			else {GOL = false;}
			tempM = _mode;
			tempP = null;
		}

		// POINT
		if(_mode.equals("Point")) {
			_map.setPixel(p,col);
		}

		// SEGMENT
		if(_mode.equals("Segment")) {
			if (tempP == null){
				tempP = p;
			}
			else {
				_map.drawSegment(tempP, p, col);
				tempP = null;
			}
		}

		// CIRCLE
		if(_mode.equals("Circle")) {
			if (tempP == null) {
				tempP = p;
			}
			else {
				double rad = Math.sqrt(Math.pow(p.ix() - tempP.ix(), 2) + Math.pow(p.iy()-tempP.iy(), 2));
				_map.drawCircle(tempP, rad, col);
				tempP = null;
			}
		}

		// RECT
		if(_mode.equals("Rect")) {
			if (tempP == null) {
				tempP = p;
			}

			else {
				_map.drawRect(tempP, p, col);
				tempP = null;
			}
		}

		// FILL
		if(_mode.equals("Fill")) {
			_map.fill(p, col);
		}

		// RECT
		if(_mode.equals("ShortestPath")) {
			if (tempP == null) {
				tempP = p;
			}
			else {
				Point2D[] path = _map.shortestPath(tempP, p);
				for (Point2D P : path){
					_map.setPixel(P,col);
				}
				tempP = null;
			}
		}

		// GOL
		if(_mode.equals("Gol")) {
			_map.nextGenGol();	
		}

		drawArray(_map);
	}
	static private void drawPixel(int x, int y) {
		StdDraw_Ex3.filledCircle(x, y, 0.3);
	}
}

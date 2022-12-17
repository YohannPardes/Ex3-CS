import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * 332514140
 * This class implements a Junit test for the MyMap2D class
 */
class MyMap2DTest {

    private MyMap2D testMap;

    @BeforeEach
    void initTest(){
        testMap = new MyMap2D(5);
    }

    @Test
    void init() {
        int[][] expectedRes =
                {
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0}
                };

        assertArrayEquals(expectedRes, testMap.getMap());
    }

    @Test
    void getWidth() {

        assertEquals(5, testMap.getWidth());

        testMap.init(7, 10);

        assertEquals(7, testMap.getWidth());
        assertNotEquals(10, testMap.getWidth());
    }

    @Test
    void getHeight() {
        assertEquals(5, testMap.getHeight());

        testMap.init(7, 10);

        assertEquals(10, testMap.getHeight());
        assertNotEquals(7, testMap.getHeight());
    }

    /**
     * based on setPixel function
     */
    @Test
    void getPixelintint() {

        testMap.setPixel(1, 1, 10);
        testMap.setPixel(4, 4, 2);
        testMap.setPixel(0, 4, -100);
        testMap.setPixel(4, 1, -3);

        assertEquals(10, testMap.getPixel(1, 1));
        assertEquals(2, testMap.getPixel(4, 4));
        assertEquals(-100, testMap.getPixel(0, 4));
        assertEquals(-3, testMap.getPixel(4, 1));
        assertEquals(0, testMap.getPixel(2, 1));

    }

    @Test
    void testGetPixelPoint2D() {

        testMap.setPixel(new Point2D(1, 1), 10);
        testMap.setPixel(new Point2D(4, 4), 2);
        testMap.setPixel(new Point2D(0, 4), -100);
        testMap.setPixel(new Point2D(4, 1), -3);

        assertEquals(10, testMap.getPixel(new Point2D(1, 1)));
        assertEquals(2, testMap.getPixel(new Point2D(4, 4)));
        assertEquals(-100, testMap.getPixel(new Point2D(0, 4)));
        assertEquals(-3, testMap.getPixel(new Point2D(4, 1)));
        assertEquals(0, testMap.getPixel(new Point2D(2, 1)));
        assertNotEquals(1, testMap.getPixel(new Point2D(3, 4)));
    }

    @Test
    void setPixelintint() {
        int[][] expectedRes = {
                {0, 0, 0, 0, -100},
                {0, 10, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, -3, 0, 0, 2}
        };

        testMap.setPixel(1, 1, 10);
        testMap.setPixel(4, 4, 2);
        testMap.setPixel(0, 4, -100);
        testMap.setPixel(4, 1, -3);

        assertArrayEquals(expectedRes, testMap.getMap());
    }

    @Test
    void testSetPixelPoint2D() {
        int[][] expectedRes = {
                {0, 0, 0, 0, -100},
                {0, 10, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, -3, 0, 0, 2}
        };

        testMap.setPixel(new Point2D(1, 1), 10);
        testMap.setPixel(new Point2D(4, 4), 2);
        testMap.setPixel(new Point2D(0, 4), -100);
        testMap.setPixel(new Point2D(4, 1), -3);

        assertArrayEquals(expectedRes, testMap.getMap());
    }

    @Test
    void drawSegment() {
        int[][] expectedRes = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        testMap.init(5, 10);

        testMap.drawSegment(new Point2D(1, 1), new Point2D(3, 8), 1);

        assertArrayEquals(expectedRes, testMap.getMap());
    }

    @Test
    void drawRect() {
        int[][] expectedRes = {
                {0, -3, -3, 0, 0},
                {0, -3, -3, 0, 0},
                {0, -3, -3, 1, 1},
                {0, 1, 1, 1, 1},
                {0, 1, 1, 1, 1}
        };

        testMap.init(5, 5);
        testMap.drawRect(new Point2D(2, 1), new Point2D(4, 4),1);
        testMap.drawRect(new Point2D(0, 1), new Point2D(2, 2),-3);

        assertArrayEquals(expectedRes, testMap.getMap());
    }

    /**
     * Testing out-of-bounds circle
     */
    @Test
    void drawCircle2() {

        int[][] expectedRes = {
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
        };

        testMap.init(20, 20);
        testMap.drawCircle(new Point2D(10, 0), 5, 1);

        assertArrayEquals(expectedRes, testMap.getMap());
    }

    @Test
    void drawCircle() {

        int[][] expectedRes = {
                //   0  1  2  3  4  5  6  7  8  9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 1, 1, 1, 1, 1, 1, 1, 0},
                {0, 0, 0, 1, 1, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        testMap.init(10, 10);
        testMap.drawCircle(new Point2D(5, 5), 4, 1);

        assertArrayEquals(expectedRes, testMap.getMap());
    }

    @Test
    void dist() {

        double res = MyMap2D.dist(new Point2D(-3, 4), new Point2D(2, 3));
        assertEquals(5.0990, res, 0.001);

        double res2 = MyMap2D.dist(new Point2D(4.67, 0), new Point2D(3.9098, -1.234));
        assertEquals(1.4493, res2, 0.001);

    }

    @Test
    void fill() {
        int[][] expectedRes = {
                //   0  1  2  3  4  5  6  7  8  9
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, -10, -10, -10, -10, -10, 0, 0},
                {0, 0, -10, -10, -10, -10, -10, -10, -10, 0},
                {0, 0, -10, -10, -10, -10, -10, -10, -10, 0},
                {0, 0, -10, -10, -10, -10, -10, -10, -10, 0},
                {0, 0, -10, -10, -10, -10, -10, -10, -10, 0},
                {0, 0, -10, -10, -10, -10, -10, -10, -10, 0},
                {0, 0, 0, -10, -10, -10, -10, -10, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        };

        testMap.init(10, 10);
        testMap.drawCircle(new Point2D(5, 5), 4, 1);

        testMap.fill(5, 5, -10);

        assertArrayEquals(expectedRes, testMap.getMap());
    }

    @Test
    void shortestPath() {

        int[][] expectedRes = {
            //   0  1  2  3  4  5  6  7  8  9
                {3, 3, 3, 3, 3, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 3, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 3, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 3, 1, 0, 0, 0, 0},
                {1, 1, 1, 1, 3, 1, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 3, 3, 3, 3, 3, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0}
        };

        //setting up the map 2d
        testMap.init(10, 10);
        testMap.drawRect(new Point2D(0, 0), new Point2D(4, 5), 1);
        testMap.drawRect(new Point2D(8, 7), new Point2D(9, 8), 1);

        testMap.drawSegment(new Point2D(3, 4), new Point2D(8, 4), 1);
        testMap.drawSegment(new Point2D(8, 4), new Point2D(8, 6), 1);

        // computing the shortest path
        Point2D[] path = testMap.shortestPath(new Point2D(0, 0), new Point2D(8, 8));
        Point2D[] path2 = testMap.shortestPath(new Point2D(9, 0), new Point2D(0, 9));

        for (Point2D p : path) {
            testMap.setPixel(p, 3);
        }

        assertArrayEquals(expectedRes, testMap.getMap());
        assertArrayEquals(new Point2D[0], path2);

    }

    @Test
    void shortestPathDist() {

        //setting up the map 2d
        testMap.init(10, 10);
        testMap.drawRect(new Point2D(0, 0), new Point2D(4, 5), 1);
        testMap.drawRect(new Point2D(8, 7), new Point2D(9, 8), 1);

        testMap.drawSegment(new Point2D(3, 4), new Point2D(8, 4), 1);
        testMap.drawSegment(new Point2D(8, 4), new Point2D(8, 6), 1);

        // computing the shortest path
        Point2D[] path = testMap.shortestPath(new Point2D(0, 0), new Point2D(8, 8));
        Point2D[] path2 = testMap.shortestPath(new Point2D(9, 0), new Point2D(0, 9));

        assertEquals(17, path.length);
        assertEquals(0, path2.length);

    }

    @Test
    void nextGenGol() {

        int[][] expectedRes = {
                //   0  1  2  3  4  5  6  7  8  9
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, Ex3.BLACK, -1, -1, -1},
                {-1, -1, -1, -1, Ex3.BLACK, -1, Ex3.BLACK, -1, -1, -1},
                {-1, -1, -1, -1, -1, Ex3.BLACK, Ex3.BLACK, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
                {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}
        };

        testMap.init(10, 10);
        testMap.setPixel(0, 1, Ex3.BLACK);
        testMap.setPixel(1, 2, Ex3.BLACK);
        testMap.setPixel(2, 0, Ex3.BLACK);
        testMap.setPixel(2, 1, Ex3.BLACK);
        testMap.setPixel(2, 2, Ex3.BLACK);

        for (int i = 0; i < 18; i++) {
            testMap.nextGenGol();

        }

        assertArrayEquals(expectedRes, testMap.getMap());

    }

    @Test
    void getSumAliveNeighbors(){

        testMap.setPixel(0, 1, Ex3.BLACK);
        testMap.setPixel(2, 0, Ex3.BLACK);

        int res1 = testMap.getSumAliveNeighbors(1, 1);
        int res2 = testMap.getSumAliveNeighbors(0, 0);
        int res3 = testMap.getSumAliveNeighbors(2, 2);
        int res4 = testMap.getSumAliveNeighbors(2, 0);

        assertEquals(2, res1);
        assertEquals(1, res2);
        assertEquals(0, res3);
        assertEquals(0, res4);



    }

    private void printMap(MyMap2D map){
        for (int i = 0; i < map.getWidth(); i++) {
            System.out.println(Arrays.toString(map.getMap()[i]));
        }
    }
}
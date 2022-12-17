import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    void getPixel() {

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
    void testGetPixel() {
        int[][] expectedRes = {
                {0, 0, 0, 0, 0},
                {0, 10, 0, 0, 0},
                {0, 0, -100, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 2}
        };

    }

    @Test
    void setPixel() {
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
    void testSetPixel() {
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

        printMap(testMap);

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

        printMap(testMap);

        assertArrayEquals(expectedRes, testMap.getMap());
    }

    @Test
    void calcDSquared() {
    }

    @Test
    void fill() {
    }

    @Test
    void fillRec() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void nextGenGol() {
    }

    private void printMap(MyMap2D map){
        for (int i = 0; i < map.getWidth(); i++) {
            System.out.println(Arrays.toString(map.getMap()[i]));
        }
    }
}
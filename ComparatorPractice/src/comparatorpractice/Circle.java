package comparatorpractice;

import java.util.Comparator;

/**
 *
 * @author dev64
 */
public class Circle {

    private double x, y, rad;
    
    public static final Comparator<Circle> xComparator = new XComparator();
    public static final Comparator<Circle> yComparator = new YComparator();
    public static final Comparator<Circle> radComparator = new RadiusComparator();
    
    public Circle(double _x, double _y, double _r) {
        x = _x;
        y = _y;
        rad = _r;
    }
    
    @Override
    public String toString() {
        return String.format("Circle %.2f %.2f %.2f", x, y, rad);
    }
    
    static class XComparator implements Comparator<Circle> {

        @Override
        public int compare(Circle c1, Circle c2) {
            return Double.compare(c1.x - c1.rad, c2.x - c2.rad);
        }   
    }
    
    static class YComparator implements Comparator<Circle> {

        @Override
        public int compare(Circle c1, Circle c2) {
            return Double.compare(c1.y - c1.rad, c2.y - c2.rad);
        }   
    }
    
    static class RadiusComparator implements Comparator<Circle> {

        @Override
        public int compare(Circle c1, Circle c2) {
            return Double.compare(c1.rad, c2.rad);
        }   
    }
}

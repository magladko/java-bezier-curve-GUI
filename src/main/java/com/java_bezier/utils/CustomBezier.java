package com.java_bezier.utils;

import com.java_bezier.models.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * CustomBezier holds methods used for calculating data for the Bézier curve.
 */
public abstract class CustomBezier {

    /**
     * Calculates factorial of the given integer.
     *
     * @param number Integer number to apply the factorial.
     * @return factorial of the given integer
     */
    public static Long factorial(Integer number) {
        long result = 1;
        for (int factor = 2; factor <= number; factor++) result *= factor;
        return result;
    }

    /**
     * Calculates Bezier polynomial.
     * @see <a href="https://en.wikipedia.org/wiki/B%C3%A9zier_curve">Bezier curve theory</a>
     */
    private static Double BezierPoly(Integer i, Integer n, Double u) {
        if (i < 0 || i > n) return 0.;
        if (i == 0) return Math.pow(1 - u, n);
        return CustomBezier.factorial(n) * Math.pow(u, i) * Math.pow(1 - u, n - i) /
                (CustomBezier.factorial(i) * CustomBezier.factorial(n - i));
    }

    /**
     * Calculates Bézier curve points with the given precision.
     *
     * @param points The list of points used to calculate coordinates for the points contained in a curve,
     * @param precisionStep Step used for calculating points on the curve. The smaller, the more precise the output will be.
     *                      Should be in the range (0, 1).
     * @return list of coordinates approximated for the Bézier curve
     * @see <a href="https://en.wikipedia.org/wiki/B%C3%A9zier_curve">Bezier curve theory</a>
     */
    public static List<SimplePoint> calculateBezierCurve(List<Point> points, Double precisionStep) {
        List<SimplePoint> result = new ArrayList<>();
        if (points.size() == 0) return result;
        if (points.size() == 1) {
            // returns one point in a list
            result.add(new SimplePoint(points.get(0)));
            return result;
        }
        if (points.size() == 2) {
            // returns 2 points creating a line
            result.add(new SimplePoint(points.get(0)));
            result.add(new SimplePoint(points.get(1)));
            return result;
        }

        double x = points.get(0).getX(), y = points.get(0).getY(), polynomial = 0.;

        for (double u = 0; u <= 1; u += precisionStep) {
            double nominatorX = 0, nominatorY = 0, denominator = 0;

            for (int i = 0; i < points.size(); i++) {
                polynomial = CustomBezier.BezierPoly(i, points.size()-1, u);
                nominatorX += points.get(i).getWeight() * points.get(i).getX() * polynomial;
                nominatorY += points.get(i).getWeight() * points.get(i).getY() * polynomial;
                denominator += points.get(i).getWeight() * polynomial;
            }
            if (polynomial != 0) {
                x = nominatorX / denominator;
                y = nominatorY / denominator;
            }
            result.add(new SimplePoint(x, y));
        }
        return result;
    }
}

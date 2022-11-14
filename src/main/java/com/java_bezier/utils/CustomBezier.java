package com.java_bezier.utils;

public class CustomBezier {

    public static Long factorial(Integer number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }

    public static Double BezierPoly(Integer i, Integer n, Double u) {
        if (i < 0 || i > n) return 0.;
        if (i == 0) return Math.pow(1 - u, n);
        return CustomBezier.factorial(n) * Math.pow(u, i) * Math.pow(1 - u, n - i) /
                (CustomBezier.factorial(i) * CustomBezier.factorial(n - i));
    }
}

package Fingerprint;

/**
 * Created by 镇宇 on 2016/11/8.
 */
public class Complex {
    private double a, b;
    public Complex(double _a, double _b) {
        a = _a;
        b = _b;
    }
    public Complex() {
        a = 0;
        b = 0;
    }

    public Complex add(final Complex e) {
        return new Complex(a + e.a, b + e.b);
    }

    public Complex sub(final Complex e) {
        return new Complex(a - e.a, b - e.b);
    }

    public Complex mul(final Complex e) {
        return new Complex (a * e.a - b * e.b, a * e.b + b * e.a);
    }

    public double abs() {
        return Math.hypot(a, b);
    }

}

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int p = 31259, g = 2;
        long x, m, k, r, y, u, s, w, z;
        int h;

        Random random = new Random();

        x = 1 + random.nextInt(p - 1);
        y = findByModulo(g, x, p);

        m = 1 + random.nextInt(p);
        h = getHashFunction(m, p);

        do { k = 1 + random.nextInt(p - 1); } while (gcd(k, p - 1) != 1);

        r = findByModulo(g, k, p);
        u = findByModulo((h - x * r), 1, p - 1);
        s = findByModulo(inversion(k, p - 1) * u, 1, p - 1);
        z = findByModulo(findByModulo(y, r, p) * findByModulo(r, s, p), 1, p);
        w = findByModulo(g, h, p);

        System.out.println("Result = " + (z == w));
    }

    private static long findByModulo(long a, long x, long N) {
        if (x == 0){
            return 1;
        }
        long z = findByModulo(a, x / 2, N);
        return ((x % 2) == 0) ? (((z * z) % N) + N) % N : (((a * z * z) % N) + N) % N;
    }

    private static int getHashFunction(long x, long N) {
        return (int) (N * (x * 0.618033 % 1));
    }

    private static long gcd(long a, long b) {
        while (b != 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private static long inversion(long x, long m) {
        x = x % m;
        for (long i = 1; i < m; i++) {
            if ((x * i) % m == 1) {
                return i;
            }
        }
        return 0;
    }
}
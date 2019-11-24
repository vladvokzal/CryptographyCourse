import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int P = 97, Q = 101;
        long N, n, r, m, d, nn, mm, rr;
        int c;

        N = P * Q;
        Random random = new Random();
        do {
            c = 1 + random.nextInt(99);
            d = findingDbyC(c, (P - 1) * (Q - 1));
        } while ((c * d) % ((P - 1) * (Q - 1)) != 1);

        n = 1 + random.nextInt(P * Q - 1);

        do { r = 1 + random.nextInt(P * Q - 1); } while (gcd(r, N) != 1);

        nn = findByModulo(findByModulo(n, 1, N) * findByModulo(r, d, N), 1, N);
        mm = findByModulo(nn, c, N);
        rr = inversion(r, N);
        m = findByModulo(mm * rr, 1, N);

        System.out.println("Result = " + (findByModulo(n,c,N) == m));
    }


    private static long findingDbyC(long c, long p) {
        long oldp = p;
        long d = 0;
        long newd = 1;
        long tmp, newc, tmpd;
        while (c > 0) {
            tmp = p / c;
            newc = p - c * tmp;
            p = c;
            c = newc;
            tmpd = d - newd * tmp;
            d = newd;
            newd = tmpd;
        }
        if (d < 0) {
            d += oldp;
        }
        return d;
    }


    private static long gcd(long a, long b) {
        while (b > 0) {
            long tmp = a % b;
            a = b;
            b = tmp;
        }
        return a;
    }

    private static long findByModulo(long a, long x, long N) {
        if (x == 0){
            return 1;
        }
        long z = findByModulo(a, x / 2, N);
        return ((x % 2) == 0) ? (((z * z) % N) + N) % N : (((a * z * z) % N) + N) % N;
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
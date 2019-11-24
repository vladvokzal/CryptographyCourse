import java.util.Random;

public class Main {

    public static void main(String[] args) {
        long N, x, D, y, w;
        int P = 97, Q = 101;
        int C, h;

        N = P * Q;
        Random random = new Random();

        do {
            C = 1 + random.nextInt(99);
            D = findingDbyC(C, (P - 1) * (Q - 1));
        } while ((C * D) % ((P - 1) * (Q - 1)) != 1);

        x = 1 + random.nextInt(P * Q - 1);

        h = getHashFunction(x,N);

        y = findByModulo(h, C, N);
        w = findByModulo(y, D, N);
        System.out.println("Result = " + (w == h));
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

    private static int getHashFunction(long x, long N) {
        return (int) (N * (x * 0.618033 % 1));
    }

    private static long findByModulo(long a, long x, long N) {
        if (x == 0){
            return 1;
        }
        long z = findByModulo(a, x / 2, N);
        return ((x % 2) == 0) ? (((z * z) % N) + N) % N : (((a * z * z) % N) + N) % N;
    }
}
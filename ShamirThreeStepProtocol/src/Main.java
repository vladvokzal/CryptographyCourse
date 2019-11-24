import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int p = 30803;
        long x, Da, Db, y, z, u, w;
        int Ca, Cb;

        Random random = new Random();
        do {
            x = 1 + random.nextInt(p - 2);
            Ca = 1 + random.nextInt(99);
            Cb = 1 + random.nextInt(99);
            Da = findingDbyC(Ca, p - 1);
            Db = findingDbyC(Cb, p - 1);
        }while (((Ca * Da) % (p - 1)) != 1 || ((Cb * Db) % (p - 1)) != 1);

        y = findByModulo(x,Ca,p);
        z = findByModulo(y,Cb,p);
        u = findByModulo(z,Da,p);
        w = findByModulo(u,Db,p);

        System.out.println("Result = " + (x == w));
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

    private static long findByModulo(long a, long x, long N) {
        if (x == 0){
            return 1;
        }
        long z = findByModulo(a, x / 2, N);
        return ((x % 2) == 0) ? (((z * z) % N) + N) % N : (((a * z * z) % N) + N) % N;
    }
}

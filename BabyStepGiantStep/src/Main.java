import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("result = " + babyStepGiantStep(2, 61, 45));// result = 34
        System.out.println("result = " + babyStepGiantStep(2, 30203, 24322));//result = 10000
        System.out.println("result = " + babyStepGiantStep(2, 30539, 28620));//result = 1000
        System.out.println("result = " + babyStepGiantStep(5, 31607, 30994));//result = 25000
    }

    private static long babyStepGiantStep(int a, int p, int y) {
        long x, n, m;
        long k = 0, l = 0;

        n = m = (long) Math.sqrt(p) + 1;

        ArrayList<Long> list1 = new ArrayList<>();
        for (int i = 0; i <= m; ++i) {
            list1.add(findByModulo(findByModulo(a, i, p) * findByModulo(y, 1, p), 1, p));
        }

        ArrayList<Long> list2 = new ArrayList<>();
        for (int i = 1; i <= m; ++i) {
            long z = findByModulo(a, i * n, p);
            list2.add(z);
            for (int j = 0; j < list1.size(); j++) {
                if (list1.get(j) == z) {
                    k = j;
                    l = i;
                    break;
                }
            }
        }

        x = n * l - k;

        return x;
    }

    private static long findByModulo(long a, long x, long N) {
        if (x == 0){
            return 1;
        }
        long z = findByModulo(a, x / 2, N);
        return ((x % 2) == 0) ? (((z * z) % N) + N) % N : (((a * z * z) % N) + N) % N;
    }

}
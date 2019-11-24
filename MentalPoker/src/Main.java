import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        int p = 3209;
        long Da, Db, Ua, w, z, deck;
        int Ca, Cb, tmp;
        int cardsNumber = 3;
        ArrayList<Integer> r = new ArrayList<>();
        ArrayList<Integer> u = new ArrayList<>();
        ArrayList<Integer> v = new ArrayList<>();

        Random random = new Random();

        do {
            Ca = 1 + random.nextInt(99);
            Cb = 1 + random.nextInt(99);
            Da = findingDbyC(Ca, p - 1);
            Db = findingDbyC(Cb, p - 1);
        } while ((((Ca * Da) % (p - 1)) != 1) || (((Cb * Db) % (p - 1)) != 1));

        //Step #1

        for (int i = 0; i < cardsNumber; i++) {
            r.add(1 + random.nextInt(p - 1));
        }

        for (int i = 0; i < cardsNumber; i++) {
            u.add((int) findByModulo(r.get(i), Ca, p));
        }
        Collections.shuffle(u);

        //Step #2

        tmp = random.nextInt(3);

        //Alice:
        Ua = findByModulo(u.get(tmp), Da, p);
        u.remove(tmp);

        //Step #3

        for (int i = 0; i < 2; i++) {
            v.add((int) findByModulo(u.get(i), Cb, p));
        }
        Collections.shuffle(v);

        //Step #4

        tmp = random.nextInt(2);
        w = findByModulo(v.get(tmp), Da, p);
        //Bob
        z = findByModulo(w, Db, p);

        //Step #5

        v.remove(tmp);
        deck = findByModulo(findByModulo(v.get(0), Da, p), Db, p);
        System.out.print("Starter cards: ");
        r.forEach(x -> System.out.print(" " + x));
        System.out.println();
        System.out.println("Distributed cards: " + Ua + " " + z + " " + deck);
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
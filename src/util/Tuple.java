package util;

/**
 * unused, may delete later.
 *
 * @param <A> class a.
 * @param <B> class b.
 */
public class Tuple<A, B> {

    private final A first;
    private final B second;

    public Tuple(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }
}
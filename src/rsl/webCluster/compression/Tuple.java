package rsl.webCluster.compression;

/**
 * Created by vignesh on 19/9/14.
 */
public class Tuple<T,S> {

    private T first;
    private S second;

    public Tuple(T first, S second) {
        this.first = first;
        this.second = second;
    }

    public String toString() {
        String s = (first == null ? "<null>" : first.toString());
        s = s + " | ";
        s = s + (second == null ? "<null>" : second.toString());
        return s;
    }

    /**
     * shallow copy (<i>first</i> and <i>second</i> are not cloned only references are)
     */
    public Tuple<T, S> clone() {
        return new Tuple<T, S>(first, second);
    }
}

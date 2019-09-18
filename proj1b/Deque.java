public interface Deque<T> {

    void addFirst(T item);

    void addLast(T item);

    boolean isEmpty();

    int size();

    T get(int index);

    T removeFirst();

    T removeLast();

    void printDeque();

}

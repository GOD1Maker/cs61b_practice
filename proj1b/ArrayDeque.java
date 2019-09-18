public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int nextFirst = 0;
    private int nextLast = 1;

    public ArrayDeque(){
        items = (T[]) new Object[8];
    }

    public ArrayDeque(ArrayDeque other){
        this.items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0 ,other.items.length);
        this.size = other.size;
        this.nextFirst = other.nextFirst;
        this.nextLast = other.nextLast;
    }

    public void addLast(T x){
        items[nextLast] = x;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
        if(size == items.length){
            Resize(size * 2);
        }
    }

    public void addFirst(T x){
        items[nextFirst] = x;
        /**special case: the nextFirst is at the front of the list*/
        if(nextFirst == 0){
            nextFirst = items.length - 1;
        }else{
            nextFirst -= 1;
        }
        size += 1;
        if(size == items.length){
            Resize(size * 2);
        }
    }

    public void Resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        items = a;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size(){
        return size;
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T rmv;
        /**if nextFirst is at the end of the list, the first item is item[0]*/
        if(nextFirst == items.length - 1){
            rmv = items[0];
            nextFirst = 0;
            items[0] = null;
        }else{
            rmv = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            nextFirst = nextFirst + 1;
        }
        size -= 1;
        if(size > 0 && (size/items.length) <= 0.25 && items.length >= 16){
            Resize(items.length/2);
        }
        return rmv;

    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T rmv;
        /**if nextLast is at the front of the list, the last item is items[items.length -1 ]*/
        if(nextLast == 0){
            rmv = items[items.length - 1];
            items[items.length - 1] = null;
            nextLast = items.length - 1;
        }else{
            rmv = items[nextLast - 1];
            items[nextLast - 1] = null;
            nextLast = nextLast - 1;
        }
        size -= 1;
        if(size > 0 && (size/items.length) <= 0.25 && items.length >= 16){
            Resize(items.length / 2);
        }
        return rmv;
    }

    public T get(int i){
        return items[i];
    }

    public void printDeque(){
        //for(int i = (nextFirst % (items.length-1)+1); i <= items.length-1; i++){
           //System.out.println(items[i] + " ");
      // }
        for(int i = 0; i <= items.length -1; i++) {
            System.out.println(items[i] + " ");
        }
    }

    public static void main(String[] args) {
        ArrayDeque<Integer>a =  new ArrayDeque<Integer>();
        //a.addFirst(1);
        a.addLast(2);
        a.addLast(3);
        //a.addFirst(1);
        //a.addFirst(3);
        //a.addFirst(4);
        //a.removeFirst();
        a.printDeque();



    }
}

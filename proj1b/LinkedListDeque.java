import java.util.LinkedList;
import java.util.NoSuchElementException;
public class LinkedListDeque<T> extends LinkedList<T> implements Deque<T>{
    private class ItemNode{
        public ItemNode prev;
        public T item;
        public ItemNode next;

        public ItemNode(ItemNode prev, T item, ItemNode next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private ItemNode CirSentinel;
    public int size;

    public LinkedListDeque(){
        size = 0;
        CirSentinel = new ItemNode(null, null, null);
        CirSentinel.prev = CirSentinel;
        CirSentinel.next = CirSentinel;
    }

    /**A deep copy of other, if you change the copied list, the original one will not be affected*/
    public LinkedListDeque(LinkedListDeque other){
        CirSentinel = new ItemNode(null,null,null);
        CirSentinel.prev = CirSentinel;
        CirSentinel.next = CirSentinel;

        for(int i = 0; i < other.size(); i++){
            addLast((T) other.get(i));//(T) is a cast since the type is unknown
        }

    }

    @Override
    public void addFirst(T item){
        ItemNode first = new ItemNode(CirSentinel, item, CirSentinel.next);
        CirSentinel.next = first;
        first.next.prev = first;
        size += 1;
    }

    @Override
    public void addLast(T item){
        ItemNode last = new ItemNode(CirSentinel.prev, item, CirSentinel);
        CirSentinel.prev = last;
        last.prev.next = last;
        size += 1;
    }

    @Override
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public T get(int index){
        if(index > size()){
            return null;
        }
        ItemNode ptr = CirSentinel.next;
        int i = 0;
        while (i < index){
            ptr = ptr.next;
            i++;
        }
        return (T) ptr.item;
    }

    @Override
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T rmv = CirSentinel.next.item;
        CirSentinel.next = CirSentinel.next.next;
        CirSentinel.next.prev = CirSentinel;
        size--;
        return rmv;
    }

    @Override
    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T rmv = CirSentinel.prev.item;
        CirSentinel.prev = CirSentinel.prev.prev;
        CirSentinel.next.next = CirSentinel;
        size--;
        return rmv;
    }

    @Override
    public void printDeque(){
        for(int i = 0; i < this.size(); i++){
            System.out.println(this.get(i));
        }
    }


    public static void main(String[] args){
        LinkedListDeque<Integer> L1 = new LinkedListDeque<Integer>();
        L1.addLast(1);
        L1.addLast(2);
        L1.removeLast();
    }
}
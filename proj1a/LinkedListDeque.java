public class LinkedListDeque<T> {
    private TNode sentinel;
    private int size;

    public class TNode {
        public T item;
        public TNode next;
        public TNode prev;
        public TNode(T i, TNode n, TNode p){
            item = i;
            next = n;
            prev = p;
        }
    }

    public LinkedListDeque(){
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public void addFirst(T x) {
        TNode Newnode = new TNode(x, sentinel.next, sentinel);
        sentinel.next.prev = Newnode;
        sentinel.next = Newnode;
        size += 1;
    }    

    public void addLast(T x){
        TNode Newnode = new TNode(x, sentinel, sentinel.prev);
        sentinel.prev.next = Newnode;
        sentinel.prev = Newnode;
        size += 1;
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }
    
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }
        else{
            size -= 1;
            T t = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            return t;
        }

    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        else{
            size -= 1;
            T t = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            return t;
        }
    }

    public void printDeque() {
        TNode current = sentinel.next;
        while (current != sentinel) {
            System.out.print(current.item + " ");
            current = current.next;
        }
    }

    public T get(int index) {
        TNode current = sentinel.next;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(TNode current, int index) {
        if (index == 0) {
            return current.item;
        }
        return getRecursiveHelper(current.next, index - 1);
    }
}

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextfirst;
    private int nextlast;
    private int RFACTOR = 2;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextfirst = 4;
        nextlast = 5;
    }

    private void resize(int capacity){
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size ; i++) {
           a[i] = items[(i + (nextfirst + 1) % items.length) % items.length];
        }
        items = a;
        nextfirst = capacity - 1;
        nextlast = size;
    }

    private int minusOne(int index){
        if (index == 0){
            return items.length - 1; 
        }
        return index - 1;
    }


    public void addLast(T x){
        if (size == items.length){
            resize(size * RFACTOR);
        }
        items[nextlast] = x;
        nextlast += 1;
        nextlast %= items.length;
        size += 1;
    }

    public void addFirst(T x){
        if (size == items.length){
            resize(size * RFACTOR);
        }
        items[nextfirst] = x;
        nextfirst = minusOne(nextfirst);
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
        else {
            if ((double) size / items.length < 0.25 && items.length >= 16){
                resize(items.length / 2);
            }
            size -= 1;
            T t = items[(nextfirst + 1) % items.length];
            nextfirst += 1;
            nextfirst %= items.length;
            return t;
        }
    }

    public T removeLast(){
        if (isEmpty()){
            return null;
        }
        else {
            if (size / items.length < 0.25 && items.length >= 16){
                resize(items.length / 2);
            }
            size -= 1;
            T t = items[minusOne(nextlast)];
            nextlast = minusOne(nextlast);
            return t;
        }
    }


    public void printDeque() {
        for (int i = (nextfirst + 1) % items.length; i < (nextfirst + 1) % items.length + size; i++) {
            System.out.print(items[i % items.length] + " ");
        }
    }

    public T get(int index) {
       return items[((nextfirst + 1) % items.length + index) % items.length];
    }
    
}

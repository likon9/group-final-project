package customlist;

public class CustomArrayList<T>{
    private final int INIT_SIZE = 16;
    private int capacity;
    private Object[] array;
    private int size = 0;

    public CustomArrayList() {
        capacity = INIT_SIZE;
        array = new Object[capacity];
    }

    public CustomArrayList(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(Object element) {
        return indexOf(element) >= 0;
    }

    public void add(T item) {
        if (size == capacity) {
            resize();
        }
        array[size++] = item;
    }

    public void add(int index, T item) {
        if (index < 0) {
            return;
        }
        if (size + 1 >= capacity) {
            resize();
        }
        if (index > size) {
            index = size;
        }
        for (int i = size; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = item;
        size++;
    }

    public T get(int index) {
        return (T) array[index];
    }

    public Object set(int index, Object element) {
        if (index < size && index >= 0) {
            Object o = array[index];
            array[index] = element;
            return o;
        }
        return null;
    }

    public T remove(int index) {
        Object o = null;
        if (index < size && index >= 0) {
            o = get(index);
            shiftToLeft(index);
        }
        return (T) o;
    }

    public boolean remove(Object element) {
        if(size == 0) {
            return false;
        }
        int i;
        for (i = 0; i < size; i++){
            if (array[i] == null && element == null) {
                break;
            }
            if (array[i] != null && array[i].equals(element)) {
                break;
            }
        }
        if (i < size) {
            shiftToLeft(i);
            return true;
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    private void resize() {
        capacity = capacity * 2;
        Object[] newArray = new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    private void shiftToLeft(int start) {
        size--;
        if (size <= 0){
            return;
        }
        if(size != start) {
            System.arraycopy(array, start + 1, array, start, size - start);
        }
        array[size] = null;
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
}
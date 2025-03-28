package customlist;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CustomArrayList<T> implements List<T> {
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

    public CustomArrayList(List<T> list) {
        this.capacity = list.size();
        this.size = list.size();
        array = new Object[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = list.get(i);
        }
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element) >= 0;
    }

    @Override
    public Iterator<T> iterator() {
        Iterator<T> it = new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size && array[currentIndex] != null;
            }

            @Override
            public T next() {
                return (T) array[currentIndex++];
            }
        };
        return it;
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }

    @Override
    public Stream<T> stream() {
        return StreamSupport.stream(spliterator(), false);
    }

    @Override
    public Object[] toArray() {
        Object[] newArray = new Object[size];
        System.arraycopy(array, 0, newArray, 0, size);
        return newArray;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            return (T1[]) Arrays.copyOf(array, size, a.getClass());
        }
        System.arraycopy(array,0, a, 0, size);
        if (a.length > size) {
            a[size] = null;
        }
        return a;
    }

    @Override
    public boolean add(Object item) {
        if (size == capacity) {
            resize();
        }
        array[size++] = item;
        return true;
    }

    @Override
    public void add(int index, Object item) {
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

    @Override
    public T remove(int index) {
        Object o = null;
        if (index < size && index >= 0) {
            o = get(index);
            shiftToLeft(index);
        }
        return (T) o;
    }

    @Override
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

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null){
            return false;
        }
        if (c.isEmpty()) {
            return true;
        }
        for (Object e : c) {
            if (contains(e)) {
                ;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            return false;
        }
        if (c.isEmpty()) {
            return false;
        }
        for (Object item : c) {
            add(item);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            return false;
        }
        if (c.isEmpty() || index < 0) {
            return false;
        }
        if (index > size) {
            index = size;
        }
        for (Object item : c) {
            add(index++, item);
        }

        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }
        if (c.isEmpty() || size == 0) {
            return false;
        }
        boolean modyfied = false;
        int i = 0;
        while (i < size) {
            if (c.contains(array[i])) {
                shiftToLeft(i);
                modyfied = true;
            } else {
                i++;
            }
        }
        return modyfied;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            return true;
        }
        if (c.isEmpty()) {
            clear();
            return true;
        }
        boolean modyfied = false;
        int i = 0;
        while (i < size) {
            if (c.contains(array[i])) {
                i++;
            } else {
                shiftToLeft(i);
                modyfied = true;
            }
        }
        return modyfied;
    }

    @Override
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

    @Override
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

    @Override
    public int lastIndexOf(Object o) {
        int lastIndex = -1;
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    lastIndex = i;
                }
            }
            return lastIndex;
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(array[i])) {
                    lastIndex = i;
                }
            }
        }
        return lastIndex;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            int tmp = fromIndex;
            fromIndex = toIndex;
            toIndex = tmp;
        }
        if (fromIndex < 0 || toIndex > size) {
            return null;
        }
        List<T> customArrayList = new CustomArrayList<>(toIndex - fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            customArrayList.add((T) array[i]);
        }
        return customArrayList;
    }
}
package com.example.algorithmsPart_1.interface1;

import java.util.Arrays;

public class ArrayList implements StringList {
    private String[] elements;
    private int size;

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IndexOutOfBoundsException("Размер массива должен быть положительным.");
        }
        this.elements = new String[capacity];
        this.size = 0;
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new NullElementException("Невозможно добавить null элемент.");
        }
        ensureCapacity();
        elements[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (item == null) {
            throw new NullElementException("Невозможно добавить null элемент.");
        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вне пределов: " + index);
        }
        ensureCapacity();
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = item;
        size++;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (item == null) {
            throw new NullElementException("Невозможно установить null элемент.");
        }
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне пределов: " + index);
        }
        String oldValue = elements[index];
        elements[index] = item;
        return oldValue;
    }

    @Override
    public String remove(String item) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) {
                return remove(i);
            }
        }
        throw new ElementNotFoundException("Элемент не найден: " + item);
    }

    @Override
    public String remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне пределов: " + index);
        }
        String removedElement = elements[index];
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null; // Чтобы избежать утечки памяти
        return removedElement;
    }

    @Override
    public boolean contains(String item) {
        return indexOf(item) >= 0;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (elements[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Индекс вне пределов: " + index);
        }
        return elements[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullElementException("Сравнение с null списком невозможно.");
        }
        if (size != otherList.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!elements[i].equals(otherList.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    private void ensureCapacity() {
        if (size >= elements.length) {
            elements = Arrays.copyOf(elements, elements.length * 2);
        }
    }
}
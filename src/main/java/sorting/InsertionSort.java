package sorting;

import entity.ComparableByIntField;
import customlist.CustomArrayList;

import java.lang.reflect.Field;
import java.util.List;

public class InsertionSort<T extends Comparable<T>> {

    public static <T extends Comparable<T>> void insertionSort(CustomArrayList<T> list) {
        for (int i = 1; i < list.size(); i++) {
            T key = list.get(i);
            int j = i - 1;

            while (j >= 0 && list.get(j).compareTo(key) > 0) {
                list.set(j + 1, list.get(j));
                j--;
            }

            list.set(j + 1, key);
        }
    }

    /**
     * Sorts list of elements by corresponding integer field value.
     *
     * @param list         list to sort
     * @param <T>          class of list elements
     */
    public static <T extends ComparableByIntField> void sortByEvenIntField(List<T> list) {

        for (int i = 1; i < list.size(); i++) {
            T objToInsert = list.get(i);
            int fieldValueToInsert = objToInsert.getIntFieldValue();
            if (fieldValueToInsert % 2 != 0) {
                continue;
            }
            int indexToInsert = i;
            int indexOfElementToCompare = i - 1;

            while (indexOfElementToCompare >= 0) {
                T objToCompare = list.get(indexOfElementToCompare);
                int intFieldOfValueToCompareValue = objToCompare.getIntFieldValue();
                if (intFieldOfValueToCompareValue % 2 == 0 && intFieldOfValueToCompareValue > fieldValueToInsert) {
                    list.set(indexToInsert, list.get(indexOfElementToCompare));
                    indexToInsert = indexOfElementToCompare;
                }
                indexOfElementToCompare--;
            }
            list.set(indexToInsert, objToInsert);
        }

    }

    /**
     * Sorts list of elements by corresponding integer field value.
     *
     * @param list         list to sort
     * @param intFieldName name of integer field
     * @param <T>          class of list elements
     */
    public static <T> void sortByEvenIntField(List<T> list, String intFieldName) {

        try {
            for (int i = 1; i < list.size(); i++) {
                T objToInsert = list.get(i);
                Field intField = objToInsert.getClass().getDeclaredField(intFieldName);
                intField.setAccessible(true);
                int fieldValueToInsert = (int) intField.get(objToInsert);
                if (fieldValueToInsert % 2 != 0) {
                    continue;
                }
                int indexToInsert = i;
                int indexOfElementToCompare = i - 1;

                while (indexOfElementToCompare >= 0) {
                    T objToCompare = list.get(indexOfElementToCompare);
                    Field intFieldOfOjbToCompare = objToCompare.getClass().getDeclaredField(intFieldName);
                    intFieldOfOjbToCompare.setAccessible(true);
                    int intFieldOfValueToCompareValue = (int) intFieldOfOjbToCompare.get(objToCompare);
                    if (intFieldOfValueToCompareValue % 2 == 0 && intFieldOfValueToCompareValue > fieldValueToInsert) {
                        list.set(indexToInsert, list.get(indexOfElementToCompare));
                        indexToInsert = indexOfElementToCompare;
                    }
                    indexOfElementToCompare--;
                }
                list.set(indexToInsert, objToInsert);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}

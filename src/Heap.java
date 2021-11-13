import com.sun.jdi.Value;

import java.util.*;

public class Heap<ValueType extends Comparable<? super ValueType>> implements Iterable<ValueType> {

    public ArrayList<ValueType> elements;
    private final boolean isMax;

    public int size() {
        return elements.size() - 1;
    }

    @Override
    public Iterator<ValueType> iterator() {
        return elements.listIterator(1);
    }

    public Heap(boolean isMax, Collection<ValueType> elements) {
        // Ne pas modifier ces lignes
        this.isMax = isMax;
        this.elements = new ArrayList<>();
        this.elements.add(null);
        this.elements.addAll(elements);
        // Ne pas modifier ces lignes

        /* TODO Ajouter une ligne de code pour construire le heap */
        buildHeap();
    }

    /* TODO Implementer le compare pour un MaxHeap et MinHeap */
    protected boolean compare(ValueType first, ValueType second) {
        return isMax ? second.compareTo(first) > 0 : second.compareTo(first) < 0;
    }

    /* TODO Retourner l'index du parent */
    public int parentIndex(int index) {
        if (index != 1)
            return index / 2;
        else
            return index;
    }

    /* TODO Retourner l'enfant gauche du noeud */
    public int leftChildIndex(int index) {
        if (!isLeaf(index))
            return index * 2;

        return index;
    }

    /* TODO Retourner l'enfant droit du noeud */
    public int rightChildIndex(int index) {
        if (!isLeaf(index))
            return (index * 2) + 1;

        return index;
    }

    /* TODO Retourner si l'index present est une feuille */
    public boolean isLeaf(int pos) {
        return pos * 2 > size();
    }

    /* TODO Constuire le monceau avec les noeuds dans "elements" */
    public void buildHeap() {
        for (int i = size() / 2; i > 0; i--)
            percolateDown(i);
    }

    /* TODO Echanger les elements qui se retrouve aux indexes currentIndex et parentIndex */
    private void swap(int currentIndex, int parentIndex) {
        Collections.swap(elements, currentIndex, parentIndex);
    }

    /* TODO Ajouter un element dans le monceaux. */
    public void insert(ValueType value) {
        elements.add(value);

        for (int hole = size() + 1; hole > 1 && compare(value, elements.get(hole / 2)); hole /= 2)
            swap(hole, hole / 2);
    }

    /* TODO Completer l'implementation des conditions de percolateDown pour un heap */
    private void percolateDown(int index) {
        int child;
        ValueType temp = elements.get(index);
        for (; index * 2 < size(); index = child) {

            child = index * 2;

            if (child != size() && compare(elements.get(child), elements.get(child + 1)) ) {
                child++;
            }

            if (compare(temp, elements.get(child))){
                elements.set(index, elements.get(child));
            }
            else
                break;
        }
        elements.set(index, temp);
    }

    /* TODO En utilisant leftChildIndex, ajouter les elements gauche du Heap dans une liste et les retourner. */
    public List<ValueType> getLeftElements() {
        List<ValueType> leftList = new ArrayList<>();
        for (int i = 1; i <= size() / 2; i++)
            leftList.add(elements.get(leftChildIndex(i)));

        return leftList;
    }

    /* TODO En utilisant rightChildIndex, ajouter les droites du Heap dans une liste et les retourner. */
    public List<ValueType> getRightElements() {
        List<ValueType> rightList = new ArrayList<>();
        for (int i = 1; i <= size() / 2; i++)
            rightList.add(elements.get(rightChildIndex(i)));

        return rightList;
    }

    /* TODO En utilisant parentIndex, ajouter les noeuds  parents du Heap dans une liste et les retourner. */
    public List<ValueType> getParentElements() {
        List<ValueType> parentList = new ArrayList<>();
        for (int i = 1; i <= size(); i++)
            parentList.add(elements.get(parentIndex(i)));

        return parentList;
    }

    /* TODO Ajouter les noeuds feuilles du monceau en utilisant isLeaf */
    public List<ValueType> getLeaves() {
        List<ValueType> leaves = new ArrayList<>();
        for (ValueType element : elements) {
            if (isLeaf(elements.indexOf(element)))
                leaves.add(element);
        }
        return leaves;
    }

}

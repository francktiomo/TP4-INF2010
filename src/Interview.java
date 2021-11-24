import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Interview {
        // La compléxité est de O(n), on itère sur tout les éléments de heap, avec while(!heap.isEmpty()) en O(n),
        // les autre opérations dans la méthode sont en O(1)
        public int lastBox(int[] boxes){

            // Ne pas modifier la ligne suivante
            PriorityQueue<Integer> heap = new PriorityQueue<>(Comparator.reverseOrder());
            Arrays.stream(boxes).forEach(heap::add);

            while(!heap.isEmpty()) {
                if (heap.size() == 1) return heap.poll();

                int box1 = heap.poll();
                int box2 = heap.poll();

                if (box1 != box2) {
                    heap.add(Math.abs(box1 - box2));
                }
            }
            return 0;
        }
}

package lesson4;

import java.lang.reflect.Array;
import java.util.*;
import kotlin.NotImplementedError;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Префиксное дерево для строк
 */
public class Trie extends AbstractSet<String> implements Set<String> {

    private static class Node {
        Map<Character, Node> children = new LinkedHashMap<>();
    }

    private Node root = new Node();

    private int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root.children.clear();
        size = 0;
    }

    private String withZero(String initial) {
        return initial + (char) 0;
    }

    @Nullable
    private Node findNode(String element) {
        Node current = root;
        for (char character : element.toCharArray()) {
            if (current == null) return null;
            current = current.children.get(character);
        }
        return current;
    }

    @Override
    public boolean contains(Object o) {
        String element = (String) o;
        return findNode(withZero(element)) != null;
    }

    @Override
    public boolean add(String element) {
        Node current = root;
        boolean modified = false;
        for (char character : withZero(element).toCharArray()) {
            Node child = current.children.get(character);
            if (child != null) {
                current = child;
            } else {
                modified = true;
                Node newChild = new Node();
                current.children.put(character, newChild);
                current = newChild;
            }
        }
        if (modified) {
            size++;
        }
        return modified;
    }

    @Override
    public boolean remove(Object o) {
        String element = (String) o;
        Node current = findNode(element);
        if (current == null) return false;
        if (current.children.remove((char) 0) != null) {
            size--;
            return true;
        }
        return false;
    }

    /**
     * Итератор для префиксного дерева
     *
     * Спецификация: {@link Iterator} (Ctrl+Click по Iterator)
     *
     * Сложная
     */
    @NotNull
    @Override
    public Iterator<String> iterator() { return new TrieIterator(); }

    public class TrieIterator implements Iterator<String> {
        Queue<String> queue = new ArrayDeque<>();
        private String lastReturned;
        String str = "";

        TrieIterator() {
            if (root == null) return;
            initialize(root, str);
        }

        private void initialize(Node node, String str) {
            for (char key : node.children.keySet()) {
                if (key == '\u0000') queue.add(str);
                else initialize(node.children.get(key), str + key);
            }
        }

        // Трудоёмкость: O( 1 )
        // Ресурсоёмкость: O( 1 )
        @Override
        public boolean hasNext() { return !queue.isEmpty(); }

        // Трудоёмкость: O( 1 )
        // Ресурсоёмкость: O( 1 )
        @Override
        public String next() {
            if (!hasNext()) throw new IllegalStateException();
            lastReturned = queue.peek();
            return queue.poll();
        }

        // Трудоёмкость: O( logN )
        // Ресурсоёмкость: O( 1 )
        @Override
        public void remove() {
            if (lastReturned == null) throw new IllegalStateException();
            Trie.this.remove(lastReturned);
            lastReturned = null;
        }
    }
}
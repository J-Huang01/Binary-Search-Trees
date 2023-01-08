public class Node<K extends Comparable<K>, V> {
    private K key;
    private V value;
    private Node<K, V> left, right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public boolean equals(Node<K, V> other) {
        if (other == null) return false;
        boolean left, right;
        if (this.left == null) {
            left = other.left == null;
        }
        else {
            left = this.left.equals(other.left);
        }
        if (this.right == null) {
            right = other.right == null;
        }
        else {
            right = this.right.equals(other.right);
        }
        return left && right && this.key.equals(other.key) && this.value.equals(other.value);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getLeft() {
        return left;
    }

    public void setLeft(Node<K, V> left) {
        this.left = left;
    }

    public Node<K, V> getRight() {
        return right;
    }

    public void setRight(Node<K, V> right) {
        this.right = right;
    }


    public void remove(K key, Node<K, V> parent) {
        // key == this.key
        if (key.equals(this.key)) {
            if (this.left != null && this.right != null) {
                K min = this.right.min();
                this.value = this.find(min);
                this.key = min;
                this.right.remove(min, this);
            }
            else if (parent.left == this) {
                parent.left = (left != null) ? left : right;
            }
            else if (parent.right == this) {
                parent.right = (left != null) ? left : right;
            }
        }
        // key < this.key
        else if (key.compareTo(this.key) < 0 && this.left != null) {
            this.left.remove(key, this);
        }
        // key > this.key
        else if (key.compareTo(this.key) > 0 && this.right != null) {
            this.right.remove(key, this);
        }
    }
    private K min(){
        Node<K,V> current = this;
        K min = this.getKey();
        while (current.getLeft() != null){
            min = current.getKey();
            current = current.getLeft();
        }
        return min;
    }
    private V find(K key){
        if (this == null){
            return null;
        }
        else{
            Node<K,V> current = this;
            return findKey(key, current);
        }
    }
    private V findKey(K key, Node<K,V> current){
        V out = null;
        if(key.compareTo(current.getKey()) == 0){
            out = current.getValue();
        }
        else if(key.compareTo(current.getKey()) < 0){
            if(current.getLeft() == null){
                out = null;
            }
            else{
                return findKey(key, current.getLeft());
            }
        }
        else if(key.compareTo(current.getKey())>0){
            if(current.getRight() == null){
                out = null;
            }
            else{
                return findKey(key, current.getRight());
            }
        }
        return out;

    }
}

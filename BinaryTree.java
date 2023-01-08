import java.util.Stack;

public class BinaryTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public BinaryTree(Node root) {
        this.root = root;
    }

    public Node<K, V> getRoot() {
        return this.root;
    }

    public void add(K key, V value) {
        if (key == null){
            return;
        }
        Node<K, V> newNode =new Node<K, V>(key, value);
        addNode(newNode,this.root);

    }
//this is a helper function to find where to add the node.
    private void addNode(Node<K, V> newNode, Node<K, V> currentNode){
        if(currentNode == null){
            root = newNode;
        }
        else {
            if (newNode.getKey().compareTo(currentNode.getKey()) == 0) {
                currentNode.setValue(newNode.getValue());//update the value
            }
            else if (newNode.getKey().compareTo(currentNode.getKey()) < 0) {
                if(currentNode.getLeft()==null){
                    currentNode.setLeft(newNode);
                }
                else{
                    addNode(newNode, currentNode.getLeft());
                }
            }
            else if (newNode.getKey().compareTo(currentNode.getKey()) > 0) {
                if(currentNode.getRight()==null){
                    currentNode.setRight(newNode);
                }
                else{
                    addNode(newNode, currentNode.getRight());
                }
            }
        }

    }

    public V find(K key) {
        if (root == null){
            return null;
        }
        else{
            Node<K,V> current = this.root;
            return findKey(key, current);
        }
    }
    //this is a helper function to find the key
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

    @SuppressWarnings("unchecked")
    public V[] flatten() {
        int index = 0;
        int size = getSize(root);
        V[] value = (V[]) new Object[size];

        Node<K, V> current = root;
        Stack<Node<K, V>> newStack = new Stack<>();

        while (index < size) {
            if (current.getLeft() != null) {
                newStack.push(current);
                current = current.getLeft();
            }
            else {
                value[index] = current.getValue();
                index++;
                while ((current.getRight() == null) && (index < size)) {
                    current = newStack.pop();
                    value[index] = current.getValue();
                    index++;
                }
                if (current.getRight() != null) {
                    current = current.getRight();
                }
            }
        }
        return  value;
    }
    //this is a helper method to find the size of the tree
    private int getSize(Node<K,V> root){
        if(root == null){
            return 0;
        }
        int size = 1;
        if((root.getLeft() != null)&&(root.getRight()!= null)){
            size += (getSize(root.getLeft()) + getSize(root.getRight()));
        }
        else if(root.getLeft() != null){
            size += getSize(root.getLeft());
        }
        else if(root.getRight() != null){
            size += getSize(root.getRight());
        }
        return size;
    }
    public boolean containsSubtree(BinaryTree<K, V> other) {
        if(other.getRoot() == null){
            return true;
        }
        Node<K, V> otherRoot = other.getRoot();
        Node<K, V> subRoot = findNode(otherRoot.getKey());
        if (subRoot != null) {
            System.out.println("here");
            return sameTree(subRoot, otherRoot);
        } else {
            return false;
        }
    }
    //this is a helper function to make sure the other tree is same to the subtree
    private boolean sameTree(Node<K, V> root, Node<K, V> other) {
        if (root == null && other == null) {
            System.out.println("both null");
            return true;
        } else if (root.equals(other)) {
            boolean left = sameTree(root.getLeft(), other.getLeft());
            boolean right = sameTree(root.getRight(), other.getRight());
            return (left && right);
        } else {
            System.out.println(root.getValue() + " " + other.getValue());
            return false;
        }
    }

//this is a helper function to use the key to search the node
    private Node<K, V> findNode(K key) {
        Node<K, V> current = root;
            if (key.compareTo(current.getKey()) < 0) {
                if (current.getLeft() != null) {
                    current = current.getLeft();
                } else {
                    return current;
                }
            } else if (key.compareTo(current.getKey()) > 0) {
                if (current.getRight() != null) {
                    current = current.getRight();
                } else {
                    return current;
                }
            } else if (key.compareTo(current.getKey()) == 0) {
                return current;
            }
        return current;
    }


    public void remove(K key) {
        if (this.root != null) {
            if (this.root.getKey().equals(key)) {
                if (this.root.getLeft() == null && this.root.getRight() == null)  {
                    this.root = null;
                } else if (this.root.getLeft() != null && this.root.getRight() == null) {
                    this.root = this.root.getLeft();
                } else if (this.root.getLeft() == null && this.root.getRight() != null) {
                    this.root = this.root.getRight();
                } else {
                    this.root.remove(key, null);
                }
            } else {
                this.root.remove(key, null);
            }
        }
    }
//    public static void main(String[] args){
//        BinaryTree<Integer, String> p5Tree = new BinaryTree<Integer, String>(new Node<Integer, String>(40, "forty",
//                new Node<Integer, String>(20, "twenty",
//                        new Node<Integer, String>(10, "ten"), new Node<Integer, String>(30, "thirty")),
//                new Node<Integer, String>(60, "sixty",
//                        new Node<Integer, String>(50, "fifty"), new Node<Integer, String>(70, "seventy"))));
//        System.out.println(p5Tree.getSize(p5Tree.getRoot()));
//    }
}

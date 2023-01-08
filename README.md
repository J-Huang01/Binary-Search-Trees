## CSCI 1933 
## Project5: Binary Search Trees
## 4/26/2022

#### Classes we created and edited:

 - BinaryTree.java
 - Node.java


#### How to compile and run your program

We tested our completed methods in BinaryTreeTest.java and our code passed all the test cases.

#### Any assumptions
We use some recursion to complete the method.

#### Additional features that you implemented (if applicable)

there are some helper function used.

 - addNode(Node<K, V> newNode, Node<K, V> currentNode)
//this is a helper function to find where to add the node.

 - findKey(K key, Node<K,V> current)
//this is a helper function to find the key

 - getSize(Node<K,V> root)
//this is a helper method to find the size of the tree

 - sameTree(Node<K, V> root, Node<K, V> other)
//this is a helper function to make sure the other tree is same to the subtree

 - findNode(K key)
//this is a helper function to use the key to search the node

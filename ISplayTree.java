public interface ISplayTree {

    void splay(Node x);

    void leftRotate(Node x);

    void rightRotate(Node x);

    void insert(int key);

    void delete(int key);

    Node findMin(Node node);

    void replace(Node node, Node newNode);

    Node search(int key);


}

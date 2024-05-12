public class SplayTree implements ISplayTree{

    private Node root;

    private int iterations;

    public SplayTree(){}

    public int getIterations() {
        return iterations;
    }

    @Override
    public void splay(Node x) {
        while (x.getParent() != null) {
            if (x.getParent().getParent() == null) {
                if (x == x.getParent().getLeft()) {
                    //zig rotation
                    rightRotate(x.getParent());
                } else {
                    //zag rotation
                    leftRotate(x.getParent());
                }
            } else if (x == x.getParent().getLeft() && x.getParent() == x.getParent().getParent().getLeft()) {
                //zig-zig rotation
                rightRotate(x.getParent().getParent());
                rightRotate(x.getParent());
            } else if (x == x.getParent().getRight() && x.getParent() == x.getParent().getParent().getRight()) {
                //zag-zag rotation
                leftRotate(x.getParent().getParent());
                leftRotate(x.getParent());
            } else if (x == x.getParent().getRight() && x.getParent() == x.getParent().getParent().getLeft()) {
                //zig-zag rotation
                leftRotate(x.getParent());
                rightRotate(x.getParent());
            } else {
                //zag-zig rotation
                rightRotate(x.getParent());
                leftRotate(x.getParent());
            }
        }
    }

    @Override
    public void leftRotate(Node x) {
        Node z = x.getRight();
        x.setRight(z.getLeft());
        if (z.getLeft() != null) {
            z.getLeft().setParent(x);
        }
        z.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = z;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(z);
        } else {
            x.getParent().setRight(z);
        }
        z.setLeft(x);
        x.setParent(z);
    }

    @Override
    public void rightRotate(Node x) {
        Node z = x.getLeft();
        x.setLeft(z.getRight());
        if (z.getRight() != null) {
            z.getRight().setParent(x);
        }
        z.setParent(x.getParent());
        if (x.getParent() == null) {
            this.root = z;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRight(z);
        } else {
            x.getParent().setLeft(z);
        }
        z.setRight(x);
        x.setParent(z);
    }

    @Override
    public void insert(int key) {
        iterations = 0;
        Node node = new Node(key);
        Node parent = null;
        Node current = this.root;
        while (current != null) {
            iterations++;
            parent = current;
            if (node.getKey() < current.getKey()) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }
        node.setParent(parent);
        if (parent == null) {
            root = node;
        } else if (node.getKey() < parent.getKey()) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
        splay(node);
    }

    @Override
    public void delete(int key) {
        iterations = 0;
        Node current = root;
        Node node = search(key);
        if (node == null) {
            return;
        }
        splay(node);
        if (node.getLeft() == null) {
            iterations++;
            replace(node, node.getRight());
        } else if (node.getRight() == null) {
            iterations++;
            replace(node, node.getLeft());
        } else {
            Node minRight = findMin(node.getRight());
            if (minRight.getParent() != node) {
                iterations++;
                replace(minRight, minRight.getRight());
                minRight.setRight(node.getRight());
                minRight.getRight().setParent(minRight);
            }
            iterations++;
            replace(node, minRight);
            minRight.setLeft(node.getLeft());
            minRight.getLeft().setParent(minRight);
        }
    }

    @Override
    public Node findMin(Node node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    @Override
    public void replace(Node node, Node newNode) {
        if (node.getParent() == null) {
            root = newNode;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(newNode);
        } else {
            node.getParent().setRight(newNode);
        }
        if (newNode != null) {
            newNode.setParent(node.getParent());
        }
    }

    @Override
    public Node search(int key) {
        iterations = 0;
        Node current = root;
        while (current != null) {
            iterations++;
            if (key < current.getKey()) {
                current = current.getLeft();
            } else if (key > current.getKey()) {
                current = current.getRight();
            } else {
                splay(current);
                return current;
            }
        }
        return null;
    }
}


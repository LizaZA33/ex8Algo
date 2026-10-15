import java.util.ArrayList;
import java.util.List;

public class YearBst {
    private BstNode root;

    public void insert(Artwork artwork) {
        root = insert(root, artwork);
    }

    private BstNode insert(BstNode node, Artwork artwork) {
        if (node == null) {
            return new BstNode(artwork);
        }
        if (artwork.getYear() < node.artwork.getYear()) {
            node.left = insert(node.left, artwork);
        } else if (artwork.getYear() > node.artwork.getYear()) {
            node.right = insert(node.right, artwork);
        } else {
            if (artwork.getTitle().compareTo(node.artwork.getTitle()) < 0) {
                node.left = insert(node.left, artwork);
            } else {
                node.right = insert(node.right, artwork);
            }
        }
        return node;
    }

    public Artwork search(int year) {
        BstNode current = root;
        while (current != null) {
            if (year < current.artwork.getYear()) {
                current = current.left;
            } else if (year > current.artwork.getYear()) {
                current = current.right;
            } else {
                return current.artwork;
            }
        }
        return null;
    }

    public List<Artwork> inOrder() {
        List<Artwork> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    private void inOrder(BstNode node, List<Artwork> result) {
        if (node == null) return;
        inOrder(node.left, result);
        result.add(node.artwork);
        inOrder(node.right, result);
    }

    public List<Artwork> preOrder() {
        List<Artwork> result = new ArrayList<>();
        preOrder(root, result);
        return result;
    }

    private void preOrder(BstNode node, List<Artwork> result) {
        if (node == null) return;
        result.add(node.artwork);
        preOrder(node.left, result);
        preOrder(node.right, result);
    }

    public List<Artwork> postOrder() {
        List<Artwork> result = new ArrayList<>();
        postOrder(root, result);
        return result;
    }

    private void postOrder(BstNode node, List<Artwork> result) {
        if (node == null) return;
        postOrder(node.left, result);
        postOrder(node.right, result);
        result.add(node.artwork);
    }

    public boolean remove(int year, String title) {
        boolean[] removed = {false};
        root = remove(root, year, title, removed);
        return removed[0];
    }

    private BstNode remove(BstNode node, int year, String title, boolean[] removed) {
        if (node == null) return null;
        if (year < node.artwork.getYear()) {
            node.left = remove(node.left, year, title, removed);
        } else if (year > node.artwork.getYear()) {
            node.right = remove(node.right, year, title, removed);
        } else if (node.artwork.getTitle().equals(title)) {
            removed[0] = true;
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            BstNode min = findMin(node.right);
            node.artwork = min.artwork;
            node.right = remove(node.right, min.artwork.getYear(), min.artwork.getTitle(), new boolean[1]);
        } else {
            node.left = remove(node.left, year, title, removed);
            node.right = remove(node.right, year, title, removed);
        }
        return node;
    }

    private BstNode findMin(BstNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public int size() {
        return size(root);
    }

    private int size(BstNode node) {
        if (node == null) return 0;
        return 1 + size(node.left) + size(node.right);
    }

    public int height() {
        return height(root);
    }

    private int height(BstNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public boolean isEmpty() {
        return root == null;
    }
}
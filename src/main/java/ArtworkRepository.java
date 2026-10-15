import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtworkRepository {
    private final YearBst yearIndex = new YearBst();
    private final Map<String, Artwork> titleIndex = new HashMap<>();

    public boolean add(Artwork artwork) {
        if (titleIndex.containsKey(artwork.getTitle())) {
            return false;
        }
        yearIndex.insert(artwork);
        titleIndex.put(artwork.getTitle(), artwork);
        return true;
    }

    public Artwork findByYear(int year) {
        return yearIndex.search(year);
    }

    public Artwork findByTitle(String title) {
        return titleIndex.get(title);
    }

    public boolean remove(String title) {
        Artwork artwork = titleIndex.remove(title);
        if (artwork == null) {
            return false;
        }
        return yearIndex.remove(artwork.getYear(), artwork.getTitle());
    }

    public List<Artwork> allByYear() {
        return yearIndex.inOrder();
    }

    public List<Artwork> allPreOrder() {
        return yearIndex.preOrder();
    }

    public List<Artwork> allPostOrder() {
        return yearIndex.postOrder();
    }

    public List<Artwork> byAuthor(String author) {
        List<Artwork> result = new ArrayList<>();
        for (Artwork artwork : titleIndex.values()) {
            if (artwork.getAuthor().equals(author)) {
                result.add(artwork);
            }
        }
        return result;
    }

    public int size() {
        return titleIndex.size();
    }

    public boolean isEmpty() {
        return titleIndex.isEmpty();
    }
}
import java.util.Objects;

public class Artwork {
    private final String title;
    private final String author;
    private final int year;

    public Artwork(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Artwork)) return false;
        Artwork artwork = (Artwork) o;
        return year == artwork.year
                && Objects.equals(title, artwork.title)
                && Objects.equals(author, artwork.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, year);
    }

    @Override
    public String toString() {
        return "Картина{" + title + ", " + author + ", " + year + "}";
    }
}
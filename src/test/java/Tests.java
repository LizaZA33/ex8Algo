import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Tests {

    @Test
    void emptyRepository() {
        ArtworkRepository repository = new ArtworkRepository();
        assertTrue(repository.isEmpty());
        assertEquals(0, repository.size());
        assertNull(repository.findByYear(1500));
        assertNull(repository.findByTitle("Мона Лиза"));
        assertTrue(repository.allByYear().isEmpty());
    }

    @Test
    void singleElement() {
        ArtworkRepository repository = new ArtworkRepository();
        Artwork artwork = new Artwork("Мона Лиза", "Леонардо да Винчи", 1503);
        assertTrue(repository.add(artwork));
        assertEquals(1, repository.size());
        assertEquals(artwork, repository.findByYear(1503));
        assertEquals(artwork, repository.findByTitle("Мона Лиза"));
        assertFalse(repository.add(new Artwork("Мона Лиза", "Неизвестный", 1600)));
    }

    @Test
    void orderedTraversal() {
        ArtworkRepository repository = new ArtworkRepository();
        repository.add(new Artwork("В", "А", 2000));
        repository.add(new Artwork("А", "А", 1500));
        repository.add(new Artwork("Б", "А", 1800));

        List<Artwork> ordered = repository.allByYear();
        assertEquals(3, ordered.size());
        assertEquals(1500, ordered.get(0).getYear());
        assertEquals(1800, ordered.get(1).getYear());
        assertEquals(2000, ordered.get(2).getYear());
    }

    @Test
    void preAndPostOrder() {
        ArtworkRepository repository = new ArtworkRepository();
        repository.add(new Artwork("Б", "А", 1800));
        repository.add(new Artwork("А", "А", 1500));
        repository.add(new Artwork("В", "А", 2000));

        assertEquals(3, repository.allPreOrder().size());
        assertEquals(3, repository.allPostOrder().size());
        assertEquals(1800, repository.allPreOrder().get(0).getYear());
    }

    @Test
    void missingYear() {
        ArtworkRepository repository = new ArtworkRepository();
        repository.add(new Artwork("Мона Лиза", "Леонардо да Винчи", 1503));
        assertNull(repository.findByYear(1900));
    }

    @Test
    void missingTitle() {
        ArtworkRepository repository = new ArtworkRepository();
        repository.add(new Artwork("Мона Лиза", "Леонардо да Винчи", 1503));
        assertNull(repository.findByTitle("Неизвестно"));
    }

    @Test
    void removeExisting() {
        ArtworkRepository repository = new ArtworkRepository();
        repository.add(new Artwork("Мона Лиза", "Леонардо да Винчи", 1503));
        repository.add(new Artwork("Звёздная ночь", "Ван Гог", 1889));
        assertTrue(repository.remove("Мона Лиза"));
        assertEquals(1, repository.size());
        assertNull(repository.findByTitle("Мона Лиза"));
        assertNull(repository.findByYear(1503));
    }

    @Test
    void removeMissing() {
        ArtworkRepository repository = new ArtworkRepository();
        repository.add(new Artwork("Мона Лиза", "Леонардо да Винчи", 1503));
        assertFalse(repository.remove("Неизвестно"));
        assertEquals(1, repository.size());
    }

    @Test
    void byAuthor() {
        ArtworkRepository repository = new ArtworkRepository();
        repository.add(new Artwork("А", "Автор1", 1500));
        repository.add(new Artwork("Б", "Автор1", 1600));
        repository.add(new Artwork("В", "Автор2", 1700));
        assertEquals(2, repository.byAuthor("Автор1").size());
        assertEquals(1, repository.byAuthor("Автор2").size());
        assertEquals(0, repository.byAuthor("Неизвестный").size());
    }
}
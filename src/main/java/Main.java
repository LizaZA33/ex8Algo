public class Main {
    public static void main(String[] args) {
        ArtworkRepository repository = new ArtworkRepository();
        repository.add(new Artwork("Мона Лиза", "Леонардо да Винчи", 1503));
        repository.add(new Artwork("Звёздная ночь", "Ван Гог", 1889));
        repository.add(new Artwork("Крик", "Мунк", 1893));
        repository.add(new Artwork("Герника", "Пикассо", 1937));
        repository.add(new Artwork("Постоянство памяти", "Дали", 1931));
        System.out.println("Размер каталога: " + repository.size());
        System.out.println("Поиск по году 1889: " + repository.findByYear(1889));
        System.out.println("Поиск по названию Герника: " + repository.findByTitle("Герника"));
        System.out.println("Обход по возрастанию года:");
        for (Artwork artwork : repository.allByYear()) {
            System.out.println("  " + artwork);
        }
        repository.remove("Крик");
        System.out.println("После удаления Крик размер: " + repository.size());
    }
}
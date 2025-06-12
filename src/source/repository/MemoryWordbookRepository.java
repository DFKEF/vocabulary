package source.repository;

import source.model.Wordbook;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryWordbookRepository implements WordbookRepositoryInterface{

    private static Map<String, Wordbook> store;

    @Override
    public List<Wordbook> findAll() {
        return null;
    }

    @Override
    public Optional<Wordbook> findByName(String name) {
        return Optional.ofNullable(store.get(name));
    }

    @Override
    public Wordbook create() {
        return null;
    }
}

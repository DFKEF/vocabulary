package source.repository;

import source.model.Dict;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MemoryDictRepository implements DictRepositoryInteface{
    private static Map<String, Dict> store;

    @Override
    public List<Dict> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Dict> findByEng(String eng) {
        return Optional.ofNullable(store.get(eng));
    }

    @Override
    public Dict addDict(Dict dict) {
        store.put(dict.getEng(), dict);
        return dict;
    }

    @Override
    public Optional<List<Dict>> findAllBookmark() {
        return Optional.of(store.values().stream().filter(Dict::isBookmarked).collect(Collectors.toList()));
    }

}

package source.service;

import source.model.Dict;
import source.repository.DictRepositoryInteface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WordbookDictService {
    private final DictRepositoryInteface repository;

    public WordbookDictService(DictRepositoryInteface repository) {
        this.repository = repository;
    }

    public Dict addDict(Dict dict) {
        validateDuplicateDict(dict);

        return repository.addDict(dict);
    }

    private void validateDuplicateDict(Dict dict) {
        repository.findByEng(dict.getEng()).ifPresent(d -> {
            throw new IllegalStateException("이미 존재하는 단어입니다.");
        });
    }

    public List<Dict> findAll() {
        return repository.findAll();
    }

    public Optional<Dict> findByEng(String eng) {
        return repository.findByEng(eng);
    }

    public Optional<List<Dict>> findAllBookmark() {
        return repository.findAllBookmark();
    }
}

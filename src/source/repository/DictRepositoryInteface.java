package source.repository;

import source.model.Dict;

import java.util.List;
import java.util.Optional;

public interface DictRepositoryInteface {
    List<Dict> findAll();
    Optional<Dict> findByEng(String eng);
    Dict addDict(Dict dict);
    Optional<List<Dict>> findAllBookmark();
}

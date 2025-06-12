package source.repository;

import source.model.Wordbook;

import java.util.List;
import java.util.Optional;

public interface WordbookRepositoryInterface {
    /*
    요거 짜면서 생각하는것

    1. SQL 기준 wordbook 테이블이 있고, 해당 테이블 내 데이터에 또 다른 테이블을 넣을 수 있을까?
        - 단어장 몇 개가 있고, 그 단어장 안에 단어들이 있는 형식
    2. 그렇다면 wordbookRepository, DictRepository 따로 해야 하는가?
        - 아마 그럴 수도...
     */
    List<Wordbook> findAll();
    Optional<Wordbook> findByName(String name);
    Wordbook create();

}

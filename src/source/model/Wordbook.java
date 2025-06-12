package source.model;

public class Wordbook {

    private String name;
    private String memo;
    private boolean bookmarked;

    public Wordbook(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo() {
        this.memo = memo;
    }

    public boolean isBookmarked() {
        return bookmarked;
    }

    public boolean setBookmark() {
        bookmarked = !bookmarked;
        return bookmarked;
    }


    /*
    source.model.Wordbook 클래스에는 총 4가지 파트로 구성되어 있다.
    1. 단어장 내 단어들을 컨트롤하는 파트
    2. 영단어들을 리스트업하는 파트
    3. 데이터에서 wordList로 불러오는 파트
    4. CSV파일을 여는 데 사용되는 파트

    앞으로 이 4가지 파트들을 어떻게 쪼갤 지에 대해서 생각해보자.
     */

    /*
    CSV는 너무 난해하기에 그냥 빼버렸다.
    위에 적어놓았던 것들은 MVC 패턴에 의거하여 분리, 프로젝트를 진행 중이다.
     */

}

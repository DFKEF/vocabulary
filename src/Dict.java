import java.util.*;

public class Dict {

    private String eng;
    private String kor;
    private String examples;
    private List<String> synonyms = new ArrayList<>();
    private boolean bookmarked;
    private String memo;


    public Dict(String eng, String kor, boolean bookmarked) {
        this.eng = eng;
        this.kor = kor;
        this.bookmarked = bookmarked;
    }

    public boolean setBookmarked() {
        return this.bookmarked = !this.bookmarked;
    }

    public boolean isBookmarked(){
        return bookmarked;
    }

    public String getEng() {
        return this.eng;
    }

    public String getKor() {
        return this.kor;
    }

    public void setExamples(String examples) {this.examples = examples;}

    public String getExamples() {return this.examples;}

    public void setSynonyms(String eng) {synonyms.add(eng);}

    public List<String> getSynonyms() {return this.synonyms;}

    public void setMemo(String memo) {this.memo = memo;}

    public String getMemo() {return this.memo;}

    @Override
    public String toString() {
        return eng + "\t\t\t" + kor;
    }
}

public class Dict {

    private String eng;
    private String kor;
    boolean bookmarked;

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


    @Override
    public String toString() {
        return eng + "\t\t\t" + kor;
    }
}

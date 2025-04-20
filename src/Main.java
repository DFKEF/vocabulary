import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Wordbook wordbook = new Wordbook("./src/source/wordbook.csv");
        wordbook.showWordbook();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("::원하는 메뉴 번호 입력::");
            int input = scanner.nextInt();
            if(input < 0) break;
            if(input == 0) wordbook.showWordbook();
            if(input == 1) {
                String eng = scanner.next();
                String kor = scanner.next();
                wordbook.addWord(new Dict(eng,kor,false));
            }
            if(input == 2) {
                String eng = scanner.next();
                wordbook.deleteWord(new Dict(eng,null,false));
            }
            if(input == 3) wordbook.showBookmarks();
            if(input == 4) {
                String eng = scanner.next();
                boolean bookmarked = wordbook.setWordBookmark(new Dict(eng,null,false));
                System.out.println(eng + " 단어의 북마크가 " + (bookmarked ? "설정되었습니다." : "해제되었습니다."));
            }
        }
    }
}
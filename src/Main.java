import java.util.Scanner;

public class Main{
    public static void main(String[] args) {
        Wordbook wordbook = new Wordbook("./src/source/wordbook.csv");
        wordbook.showWordbook();

        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.println("::원하는 메뉴 번호 입력::");
            System.out.println("0. 단어장 보기");
            System.out.println("1. 단어 추가하기");
            System.out.println("2. 단어 삭제하기");
            System.out.println("3. 북마크된 단어 보기");
            System.out.println("4. 단어 북마크 설정");
            System.out.println("5. 단어 집중 보기");
            int input = scanner.nextInt();
            if(input < 0) break;
            try {
                if (input == 0) wordbook.showWordbook();
                if (input == 1) {
                    String eng = scanner.next();
                    String kor = scanner.next();
                    wordbook.addWord(new Dict(eng, kor, false));
                }
                if (input == 2) {
                    String eng = scanner.next();
                    wordbook.deleteWord(eng);
                }
                if (input == 3) wordbook.showBookmarks();
                if (input == 4) {
                    String eng = scanner.next();
                    boolean bookmarked = wordbook.setWordBookmark(eng);
                    System.out.println(eng + " 단어의 북마크가 " + (bookmarked ? "설정되었습니다." : "해제되었습니다."));
                }
                if (input == 5) {
                    String eng = scanner.next();
                    wordbook.showWord(eng);
                }
            }catch(NullPointerException e) {
                System.out.println("단어가 존재하지 않습니다.");
            }
        }
    }
}
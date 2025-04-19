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
            if(input == 1) wordbook.showBookmarks();
            if(input == 2) {
                String eng = scanner.next();
                String kor = scanner.next();
                wordbook.addWord(new Dict(eng,kor,false));
            }
        }
    }
}
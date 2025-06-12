package source.view;

import source.model.Dict;

import java.util.List;
import java.util.Scanner;

public class WordbookDictView {

    public int getInput(String name) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("<< 단어장 이름: " + name + " >>");
        System.out.println(":: 원하는 메뉴를 입력하세요 ::");
        System.out.println("1. 단어 전체 보기");
        System.out.println("2. 단어 상세 보기");
        System.out.println("3. 북마크된 단어 보기");
        System.out.println("4. 단어 추가하기");
        System.out.println("이외. 이전으로 돌아가기");
        return scanner.nextInt();
    }

    public String getStringInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("영어 단어 입력: ");
        return scanner.next();
    }

    public Dict addDict() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("영어 단어 입력: ");
        String eng = scanner.next();

        System.out.println("한글 뜻 입력: ");
        String kor = scanner.next();

        return new Dict(eng,kor,false);
    }


    public void showWordbookDict(List<Dict> list) {
        System.out.println("::영단어 리스트::");
        list.forEach(System.out::println);
    }

    public void showDict(Dict dict) {
        System.out.print(dict);
        System.out.println(dict.isBookmarked() ? " ★" : "");
        System.out.println(dict.getExamples());
        System.out.println("동의어: " + dict.getSynonyms());
    }

    public void showBookmarkDict(List<Dict> list) {
        System.out.println("::북마크 리스트::");
        list.forEach(System.out::println);
    }

}

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Wordbook {
    Map<String, Dict> wordList = new HashMap<>();
    String raw_url;

    private File csv;
    private BufferedReader br;
    private BufferedWriter bw;

    public Wordbook(String url) {
        this.raw_url = url;
        this.csv = new File(url);
        getCSVfile(this::setWordList);
    }

    /*
    #1. 단어장 내 단어들을 컨트롤하는 메서드

    getCSVfile을 통해 파일을 열고 수정한다.

    뭐 이런 데이터베이스를 저장할 때는 SQL DB가 가장 편하겠으나,
    기억해라. 난 아직 군대이다. 못쓴다.

    일단 NoSQL을 지향하다가, 찍턴때 Spring 찍먹 시작하면서 바꿔가면 된다.
     */
    public void addWord(Dict dict) {
        wordList.put(dict.getEng(), dict);
        getCSVfile(this::modifyCSVfile);
    }


    public void deleteWord(String eng) {
        wordList.remove(eng);
        getCSVfile(this::modifyCSVfile);
    }

    public boolean setWordBookmark(String eng){
        boolean bookmarked = wordList.get(eng).setBookmarked();
        getCSVfile(this::modifyCSVfile);
        return bookmarked;
    }

    /*
    #2. 영단어들을 리스트업하는 메서드
    전체 / 북마크 / 상세
     */
    public void showWordbook() {
        System.out.println("::영단어 리스트::");
        wordList.values().forEach(System.out::println);
    }

    public void showBookmarks() {
        System.out.println("::북마크 리스트::");
        wordList.values().stream()
                .filter(Dict::isBookmarked)
                .forEach(System.out::println);
    }

    /*
    단어의 상세 내용을 보여주는 메서드
    일단 terminal based project이기 때문에 wordbook 클래스에 메서드를 넣었다.
    만약 Spring으로 넘어가게 된다면 Dict에 메서드를 넣고, wordList에서 forEach를 하는것도 방법이다.
     */
    public void showWord(String eng) {
        Dict dict = wordList.get(eng);
        System.out.print(dict);
        System.out.println(dict.isBookmarked() ? " ★" : "");
        System.out.println(dict.getExamples());
        System.out.println("동의어: " + dict.getSynonyms());
    }

    /*
    #3. 데이터에서 wordList로 불러오는 메서드
     */
    private void setWordList(){
        List<List<String>> raw = new ArrayList<>();
        String line;

        // csv 파일에서 단어들을 불러오는 과정 (file.csv -> raw)
        try{
            while((line = br.readLine()) != null) {
                raw.add(new ArrayList<>(Arrays.asList(line.split("/"))));
            }
        }catch(IOException e) {
            e.printStackTrace();
            System.out.println("파일에 접근하는 도중 오류가 발생하였습니다.");
        }

        // raw 데이터를 wordList에 복제
        // 단, raw는 List, wordList는 Map
        raw.forEach(l -> {
            try {
                String english = l.get(0);
                String korean = l.get(1);
                List<String> synonymsList = new ArrayList<>(Arrays.asList(l.get(2).split(",")));
                String examples = l.get(3);
                boolean bookmark = l.get(4).equals("true");

                Dict tempDict = new Dict(english, korean, bookmark);
                synonymsList.forEach(tempDict::setSynonyms);
                tempDict.setExamples(examples);
                wordList.put(english, tempDict);
            }catch (IndexOutOfBoundsException e) {e.printStackTrace();}
        });
    }

    /*
    #4. CSV파일을 여는 데 사용되는 메서드
    : modifyCSVfile(), getCSVfile(Runnable r)

    둘 다 open, modify(추가, 삭제) 하는 데 반복적으로 사용되는 코드를 모아놓음.
    getCSVfile의 경우 Runnable을 통해 실행하고 싶은 행동을 하도록 함.
    modifyCSVfile은 getCSVfile의 인수로 들어가는 아이겠죠.
     */
    private void modifyCSVfile() {
        try {
            bw = new BufferedWriter(new FileWriter(csv));
            wordList.forEach((e, d) -> {
                String k = d.getKor();
                String synonymsList = d.getSynonyms().stream().collect(Collectors.joining(","));
                String examples = d.getExamples();
                boolean book = d.isBookmarked();
                try {
                    bw.write(e + "/" + k + "/" + synonymsList + "/" + (examples == null ? "" : examples) +"/" + book);
                    bw.newLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCSVfile(Runnable r) {
        try {
            br = new BufferedReader(new FileReader(csv));
            r.run();
        }
        catch (IOException e) {e.printStackTrace();}
        finally{
            try{
                if(br != null) {br.close();}
                if(bw != null) {bw.close();}
            } catch(IOException e) {e.printStackTrace();}
        }
    }

    /*
    Wordbook 클래스에는 총 4가지 파트로 구성되어 있다.
    1. 단어장 내 단어들을 컨트롤하는 파트
    2. 영단어들을 리스트업하는 파트
    3. 데이터에서 wordList로 불러오는 파트
    4. CSV파일을 여는 데 사용되는 파트

    앞으로 이 4가지 파트들을 어떻게 쪼갤 지에 대해서 생각해보자.
     */

}

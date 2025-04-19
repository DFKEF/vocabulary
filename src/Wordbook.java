import java.io.*;
import java.util.*;

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

    public void addWord(Dict dict) {
        getCSVfile(() -> {
            try {
                bw = new BufferedWriter(new FileWriter(csv, true));
                if (csv.length() > 0) bw.newLine();
                bw.write(dict.getEng() + "/" + dict.getKor() + "/" + dict.isBookmarked());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        wordList.put(dict.getEng(), dict);
    }

    public void deleteWord(Dict dict) {
        wordList.remove(dict.getEng());

    }

    public void setWordBookmark(Dict dict){
        boolean bookmarked = wordList.get(dict.getEng()).setBookmarked();
    }

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

    private void setWordList(){
        List<List<String>> raw = new ArrayList<>();
        String line;

        // csv 파일에서 단어들을 불러오는 과정 (file.csv -> raw)
        try{
            while((line = br.readLine()) != null) {
                raw.add(new ArrayList<>(Arrays.asList(line.split("/"))));
            }
        }catch(IOException e) {e.printStackTrace();}

        // raw 데이터를 wordList에 복제
        // 단, raw는 List, wordList는 Map
        raw.forEach(l -> {
            String english = l.get(0);
            String korean = l.get(1);
            boolean bookmark = l.get(2).equals("true");
            wordList.put(english, new Dict(english, korean, bookmark));
        });
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

}

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Wordbook {
    Map<String, Dict> wordList = new HashMap<>();
    String raw_url;

    public Wordbook(String url) {
        this.raw_url = url;
        setWordList(url);
    }

    public void addWord(Dict dict) {

    }

    public void deleteWord(Dict dict) {

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


    private void setWordList(String url) {
        List<List<String>> raw = new ArrayList<>();
        File csv = new File(url);
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));

            while((line = br.readLine()) != null) {
                List<String> aLine = new ArrayList<>();
                String[] lineArr = line.split("/");
                aLine = Arrays.asList(lineArr);
                raw.add(aLine);
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try{
                if(br != null) {br.close();}
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

        for(List l : raw) {
            wordList.put(l.get(0).toString(), new Dict(l.get(0).toString(), l.get(1).toString()));
        }

        /*wordList.values().addAll(
                raw.stream()
                        .map(l -> new Dict(l.get(0), l.get(1)))
                        .collect(Collectors.toList())
        );*/

    }
}

package source.controller;

import source.model.Wordbook;
import source.service.WordbookDictService;
import source.view.WordbookDictView;

public class DictController {
    private Wordbook wordbook;
    private WordbookDictService service;
    private WordbookDictView view;

    public DictController(Wordbook wordbook, WordbookDictView view) {
        this.wordbook = wordbook;
        this.view = view;
        this.service = new WordbookDictService(wordbook.getRepository());
    }

    public void init() {
        int menu = view.getInput(wordbook.getName());

        while(true) {
            switch (menu) {
                case 1:
                    view.showWordbookDict(service.findAll());
                    break;
                case 2:
                    view.showDict(service.findByEng(view.getStringInput()).get());
                    break;
                case 3:
                    view.showBookmarkDict(service.findAllBookmark().get());
                    break;
                case 4:
                    service.addDict(view.addDict());
                    break;
                default:
                    return;
            }
        }
    }


}

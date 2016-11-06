package com.app.rdc.taxation.item;

import com.app.rdc.taxation.item.objects.Book;
import com.app.rdc.taxation.item.objects.Food;
import com.app.rdc.taxation.item.objects.Generic;
import com.app.rdc.taxation.item.objects.Item;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.morph.SimpleStemmer;

import java.util.List;

public class ItemFactory {

    private static final String medicalWords[] = {"pills", "medical"};
    private static final String foodWords[] = {"chocolates"};
    private static final String bookWords[] = {"books"};

    private static final SimpleStemmer stemmer = new SimpleStemmer();

    private IDictionary dictionary;

    public ItemFactory(IDictionary dictionary) {
        this.dictionary = dictionary;
    }

    private String getLexicalWordCategory(String wordStr) throws ItemFactoryException {

        /*
         * Stemming removes pluralization and converts the noun to its singular form.
         * Many words cannot be stemmed so we use the original word if no stem is found.
         */

        List<String> stemmed = stemmer.findStems(wordStr, POS.NOUN);

        IIndexWord idxWord;

        if(stemmed.size() > 0)
            idxWord = this.dictionary.getIndexWord(stemmed.get(0), POS.NOUN);
        else
            idxWord = this.dictionary.getIndexWord(wordStr, POS.NOUN);

        if (idxWord == null)
            return null;

        IWordID wordId = idxWord.getWordIDs().get(0);
        IWord word = this.dictionary.getWord(wordId);

        return word.getSenseKey().getLexicalFile().getName();
    }


    /**
     * generateItem() creates a new Item which can be added to the orders area.
     * 4 possible items type can be returned (Book, Food, Medical, Generic)
     * If any food or book is marked as medical in the name field, the item returned
     * will be guaranteed to be medical.
     *
     * Also note that this factory uses the wordnet api and ensures that the order entry name
     * contains at least one noun or else an exception will be thrown.
     *
     * If there isn't a word at least 3 characters long, a creation exception will be thrown as well
     * until there is a noun exception list for anything that is deemed to be two characters in length.
     *
     * Food and Book items are automatically discovered based on the lexical noun category from the
     * wordnet dictionary.
     *
     * @param name of the intended item
     * @return the item type based on the name given.
     */

    public Item generateItem(String name, double price) throws ItemFactoryException {

        Item item = null;

        String[] namePieces = name.split("\\s+");

        for(String s : namePieces) {
            String lexicalName = getLexicalWordCategory(s);

            if(lexicalName != null) {

                if(lexicalName.startsWith("noun")) {
                    String[] details = lexicalName.split("\\.");

                    if(details[1].equals("food")) {
                        item = new Food();
                        break;
                    }

                    if(details[1].equals("communication")) {
                        item = new Book();
                        break;
                    }
                }
            }
        }

        if(item == null)
            item = new Generic();

        item.setName(name);
        item.setPrice(price);

        return item;
    }
}

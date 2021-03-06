package com.app.rdc.taxation.item;

import com.app.rdc.taxation.item.objects.*;
import edu.mit.jwi.IDictionary;
import edu.mit.jwi.item.IIndexWord;
import edu.mit.jwi.item.IWord;
import edu.mit.jwi.item.IWordID;
import edu.mit.jwi.item.POS;
import edu.mit.jwi.morph.SimpleStemmer;

import java.util.List;
import java.util.regex.Pattern;

public class ItemFactory {

    private static final Pattern lettersAndSpaces = Pattern.compile("[a-z\\s]*", Pattern.CASE_INSENSITIVE);
    private static final String medicalWords[] = {"pill", "medical"};

    private static final SimpleStemmer stemmer = new SimpleStemmer();

    private IDictionary dictionary;

    public ItemFactory(IDictionary dictionary) {
        this.dictionary = dictionary;
    }

    private String getLexicalWordCategory(String wordStr) throws ItemFactoryException {

        /*
         * Stemming removes pluralization and converts the noun to its singular form.
         * Many words cannot be stemmed so we use the original word if no stem is found.
         *
         * Some words may have multiple lexical categories but since we are only interested in
         * food or books.
         */

        List<String> stemmed = stemmer.findStems(wordStr, POS.NOUN);

        IIndexWord idxWord;

        if(stemmed.size() > 0)
            idxWord = this.dictionary.getIndexWord(stemmed.get(0), POS.NOUN);
        else
            idxWord = this.dictionary.getIndexWord(wordStr, POS.NOUN);

        if (idxWord == null)
            return null;

        for(int i = 0; i < idxWord.getWordIDs().size(); i++) {

            IWordID wordId = idxWord.getWordIDs().get(i);
            IWord word = this.dictionary.getWord(wordId);

            if(word.getSenseKey().getLexicalFile().getName().equals("noun.book")) {
                return word.getSenseKey().getLexicalFile().getName();
            }

            if(word.getSenseKey().getLexicalFile().getName().equals("noun.food")) {
                return word.getSenseKey().getLexicalFile().getName();
            }
        }

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
     * If there are lexical attributes that discover food and book in the same order name, a book will be returned
     *
     * @param name of the intended item
     * @return the item type based on the name given.
     */

    public Item generateItem(String name, float price) throws ItemFactoryException {

        if(lettersAndSpaces.matcher(name).find() == false)
            throw new ItemFactoryException(ItemFactoryException.BAD_CHARACTERS);

        Item item = null;
        boolean hasNoun = false;

        String[] namePieces = name.split("\\s+");

        for(String s : namePieces) {
            String lexicalName = getLexicalWordCategory(s);

            if(lexicalName != null) {

                if(lexicalName.startsWith("noun")) {

                    hasNoun = true;
                    String[] details = lexicalName.split("\\.");

                    if(details[1].equals("food")) {
                        item = new Food();
                        // Keep going, we may have a food book
                    }

                    if(details[1].equals("communication")) {
                        item = new Book();
                        break;
                    }
                }
            }
        }

        if(hasNoun == false)
            throw new ItemFactoryException(ItemFactoryException.NO_NOUNS);

        /*
         * I am sure there is probably a better way to do this in Java 8, I just want to
         * fully complete the assignment.
         */

        medicalScan: for(String medicalWord : medicalWords) {

            for(String namePiece : namePieces) {

                List<String> stemmed = stemmer.findStems(namePiece, POS.NOUN);

                if(stemmed.size() > 0) {
                    if(medicalWord.equals(stemmed.get(0))) {
                        item = new Medical();
                        break medicalScan;
                    }
                }

                if(medicalWord.equals(namePiece)) {
                    item = new Medical();
                    break medicalScan;
                }
            }
        }

        if(item == null)
            item = new Generic();

        if(name.contains("imported")) {
            item.setImported(true);
        }

        item.setName(name);
        item.setPrice(price);

        return item;
    }
}

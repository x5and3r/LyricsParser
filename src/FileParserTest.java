import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class FileParserTest {

    private final Map<String, Integer> words = new HashMap<>();
    private final Map<String, Integer> wordsFinal = new HashMap<>();

    private final List<String> terms = asList("merry", "christmas", "merry", "christmas", "and");

    private void fillMap() {
        wordsFinal.put("merry", 2);
        wordsFinal.put("christmas", 2);
        wordsFinal.put("and", 1);
    }

    @Test
    public void testCollectWords() throws Exception {
        FileParser.collectWords(words, terms);
        fillMap();
        assertEquals(wordsFinal, words);
    }

}
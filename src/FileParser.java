import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import static Messages.Messages.FILE_IS_NOT_FOUND;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyMap;
import static java.util.Map.Entry;
import static java.util.logging.Logger.getLogger;
import static java.util.regex.Pattern.compile;
import static java.util.stream.Collectors.toMap;

public class FileParser {

    private final String filePath;
    private final Integer wordsCount;
    private final Logger logger = getLogger(getClass().getName());

    public FileParser(String filePath, Integer wordsCount) {
        this.filePath = filePath;
        this.wordsCount = wordsCount;
    }

    public Map<String, Integer> sortWordsByInputCount() {
        final Map<String, Integer> wordsWithTheirCount = countWords();
        final Comparator<Map.Entry<String, Integer>> entryValueComparator = (e1, e2) -> e2.getValue().compareTo(e1.getValue());
        return wordsWithTheirCount.entrySet().stream()
                .sorted(entryValueComparator.thenComparing((e1, e2) -> e1.getKey().compareTo(e2.getKey())))
                .limit(wordsCount)
                .collect(toMap(Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

    public Map<String, Integer> countWords() {
        try {
            final BufferedReader reader = new BufferedReader(new FileReader(filePath));
            final String regex = "(\\s|\\.|,)";
            final Pattern pattern = compile(regex);
            String line = reader.readLine();
            Map<String, Integer> wordCounts = new HashMap<>();
            while (line != null) {
                final List<String> lineTerms = asList(pattern.split(line));
                collectWords(wordCounts, lineTerms);
                line = reader.readLine();
            }
            wordCounts.size();
            return wordCounts;
        } catch (IOException e) {
            logger.warning(FILE_IS_NOT_FOUND);
            return emptyMap();
        }
    }

    public static void collectWords(Map<String, Integer> wordCounts, List<String> lineTerms) {
        lineTerms.stream()
                .filter(term -> !term.isEmpty())
                .map(String::toLowerCase)
                .forEach(term -> {
                    Integer count = wordCounts.getOrDefault(term, 0);
                    count++;
                    wordCounts.put(term, count);
                });
    }

}

import java.util.Map;

public class ResultProcessor {

    private final Map<String, Integer> sortedWords;

    public ResultProcessor(Map<String, Integer> sortedWords) {
        this.sortedWords = sortedWords;
    }

    public void showResults() {
        sortedWords.entrySet()
                .forEach(sortedWordEntry -> {
                    //TODO: use logger instead of sout
                    System.out.println(sortedWordEntry.getKey() + "-" + sortedWordEntry.getValue());
                });
    }

}

package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.nio.file.Paths;

public class SuggestionEngineTest {
    private SuggestionEngine suggestionEngine = new SuggestionEngine();

//    @Mock
//    private SuggestionsDatabase mockSuggestionDB;
    private boolean testInstanceSame = false;

    @Test
    public void testGenerateSuggestions() throws Exception {
        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));

//        Assertions.assertTrue(testInstanceSame);
        Assertions.assertTrue(suggestionEngine.generateSuggestions("hellw").contains("hello"));
    }

    @Test
    public void testGenerateSuggestionsFail() throws Exception {
        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));

        testInstanceSame = true;
        Assertions.assertTrue(testInstanceSame);
        Assertions.assertFalse(suggestionEngine.generateSuggestions("hello").contains("hello"));
    }

//    @Test
//    public void testSuggestionsAsMock() {
//        Map<String,Integer> wordMapForTest = new HashMap<>();
//
//        wordMapForTest.put("test", 1);
//
//        suggestionEngine.setWordSuggestionDB(mockSuggestionDB);
//
//        Assertions.assertFalse(suggestionEngine.generateSuggestions("test").contains("test"));
//
//        Assertions.assertTrue(suggestionEngine.generateSuggestions("tes").contains("test"));
//    }
}

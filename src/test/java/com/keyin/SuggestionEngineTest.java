package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class SuggestionEngineTest {
    private SuggestionEngine suggestionEngine = new SuggestionEngine();
    private boolean testInstanceSame = false;

    @Test
    public void testGenerateSuggestions() throws Exception {
        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));

        Assertions.assertFalse(testInstanceSame);
        Assertions.assertTrue(suggestionEngine.generateSuggestions("hellw").contains("hello"));
    }

    @Test
    public void testGenerateSuggestionsFail() throws Exception {
        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));

        testInstanceSame = true;
        Assertions.assertTrue(testInstanceSame);
        Assertions.assertFalse(suggestionEngine.generateSuggestions("hello").contains("hello"));
    }

    @Test
    public void testTwoSeparateSuggestionsAreEqual() throws Exception {
        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));

        boolean suggestion1 = suggestionEngine.generateSuggestions("zwifter").contains("zwitter");
        boolean suggestion2 = suggestionEngine.generateSuggestions("zwizter").contains("zwitter");

        Assertions.assertTrue(suggestionEngine.generateSuggestions("zwifter").contains("zwitter"));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("zwizter").contains("zwitter"));
        Assertions.assertEquals(suggestion1, suggestion2);

    }

    @Test
    public void testCorrectResourcePath() throws Exception {
        // testing to make sure words.txt file path matches what is expected
        suggestionEngine.loadDictionaryData( Paths.get( ClassLoader.getSystemResource("words.txt").getPath()));
        Path words = Paths.get( ClassLoader.getSystemResource("words.txt").getFile());
        Assertions.assertEquals(words.toString(), "/Users/keyinstudent/Documents/SD/Semester4/DevOps/s4-qap1/target/classes/words.txt");
    }

    @Test
    public void testEmployeeFileExists() throws Exception {
        Path employeeClass = Paths.get( ClassLoader.getSystemClassLoader().loadClass("com.keyin.Employee").toString());
//        Assertions.assertEquals(employeeClass.toString(), "class com.keyin.Employee");
        Assertions.assertTrue(employeeClass.toString().equals("class com.keyin.Employee"), "class exists!");
    }

    @Test
    public void testDidgeridooClassDoesNotExist() {
        String didgeridooClass = "com.keyin.Didgeridoo";
        String employeeClass = "com.keyin.Employee";

        try {
            Class.forName(didgeridooClass);
            Assertions.fail("Class exists.");
        } catch (ClassNotFoundException e) {
            Assertions.assertTrue(true, "Class does not exist.");
        }
    }
}

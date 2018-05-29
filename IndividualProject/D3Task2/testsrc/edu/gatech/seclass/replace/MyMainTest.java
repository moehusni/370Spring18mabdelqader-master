package edu.gatech.seclass.replace;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.TemporaryFolder;

public class MyMainTest {

	 private ByteArrayOutputStream outStream;
	    private ByteArrayOutputStream errStream;
	    private PrintStream outOrig;
	    private PrintStream errOrig;
	    private Charset charset = StandardCharsets.UTF_8;

	    @Rule
	    public TemporaryFolder temporaryFolder = new TemporaryFolder();
	    public final ExpectedException exception = ExpectedException.none();

	    @Before
	    public void setUp() throws Exception {
	        outStream = new ByteArrayOutputStream();
	        PrintStream out = new PrintStream(outStream);
	        errStream = new ByteArrayOutputStream();
	        PrintStream err = new PrintStream(errStream);
	        outOrig = System.out;
	        errOrig = System.err;
	        System.setOut(out);
	        System.setErr(err);
	    }

	    @After
	    public void tearDown() throws Exception {
	        System.setOut(outOrig);
	        System.setErr(errOrig);
	    }

	    // Some utilities

	    private File createTmpFile() throws IOException {
	        File tmpfile = temporaryFolder.newFile();
	        tmpfile.deleteOnExit();
	        return tmpfile;
	    }

	    private File createInputFile1() throws Exception {
	        File file1 =  createTmpFile();
	        FileWriter fileWriter = new FileWriter(file1);

	        fileWriter.write("How much Wood can a woodchuck chuck");

	        fileWriter.close();
	        return file1;
	    }

	    private File createInputFile2() throws Exception {
	        File file1 =  createTmpFile();
	        FileWriter fileWriter = new FileWriter(file1);

	        fileWriter.write("Howdy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"howdy Bill\" twice");

	        fileWriter.close();
	        return file1;
	    }

	    private File createInputFile3() throws Exception {
	        File file1 =  createTmpFile();
	        FileWriter fileWriter = new FileWriter(file1);

	        fileWriter.write("Howdy Bill, have you learned your abc and 123?\n" +
	                "It is important to know your abc and 123," +
	                "so you should study it\n" +
	                "and then repeat with me: abc and 123");

	        fileWriter.close();
	        return file1;
	    }
	    
	    private File createEmptyFile() throws Exception {
	        File file1 =  createTmpFile();
	        return file1;
	    }

	    private String getFileContent(String filename) {
	        String content = null;
	        try {
	            content = new String(Files.readAllBytes(Paths.get(filename)), charset);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return content;
	    }

	    // Actual test cases
	    /**
	     * Purpose: Test of behavior when file is empty, with no arguments
	     * Implementation of test frame #5
	     * @throws Exception
	     */
	    @Test
	    public void mainTest1() throws Exception {
	        File inputFile1 = createEmptyFile();
	     

	        String args[] = {"", "", "--", inputFile1.getPath()};
	        Main.main(args);

	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", 0, actual1.length());
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    /**
	     * Purpose: Test of behavior when file is empty, with to string non-empty
	     * Implementation of test frame #6
	     * @throws Exception
	     */
        //type A
	    @Test
	    public void mainTest2() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	        String args[] = {"", "fdssf", "--", inputFile1.getPath()};
	        Main.main(args);

	        String expected1 = "";
	        

	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected1, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }

	    /**
	     * Purpose: Test of behavior when multiple files are empty, with no arguments
	     * Implementation of test frame #7
	     * @throws Exception
	     */
	    @Test
	    public void mainTest3() throws Exception {
	        File inputFile1 = createEmptyFile();
	        File inputFile2 = createEmptyFile();
	        File inputFile3 = createEmptyFile();
	     

	        String args[] = {"", "", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);

	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", 0, actual1.length());
	        assertEquals("The files differ!", 0, actual2.length());
	        assertEquals("The files differ!", 0, actual3.length());
	        
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

	    }

	    /**
	     * Purpose: Test of behavior when multiple files are empty, with to string non-empty
	     * Implementation of test frame #8
	     * @throws Exception
	     */
        //type A
	    @Test
	    public void mainTest4() throws Exception {
	    	File inputFile1 = createEmptyFile();
	        File inputFile2 = createEmptyFile();
	        File inputFile3 = createEmptyFile();
		     

	        String args[] = {"", "fdssf", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);

	        String expected1 = "";
	        String expected2 = "";
	        String expected3 = "";
	        

	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected1, actual1);
	        assertEquals("The files differ!", expected2, actual2);
	        assertEquals("The files differ!", expected3, actual3);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	    }

	    /**
	     * Purpose: Test of behavior when filename doesn't exist
	     * Implementation of test frame #1
	     * @throws Exception
	     */
        //type C <Error output shouldn't be a usage problem, but file not found>
	    @Test
	    public void mainTest5() throws Exception {
	        File inputFile1 = createEmptyFile();
	     

	        String args[] = {"", "", "--", inputFile1.getPath()};
	        inputFile1.delete();
	        Main.main(args);
	        //exception.expect(FileNotFoundException.class);
	        assertEquals("File " + inputFile1.getName() + " not found", errStream.toString().trim());
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));


	    }
	    /**
	     * Purpose: Test of behavior when no files are given as arguments
	     * Implementation of test frame #2
	     * @throws Exception
	     */
	    @Test
	    public void mainTest6() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	        String args[] = {"", "", "--"};
	        Main.main(args);
	        exception.expect(IllegalArgumentException.class);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior when from string is greater than file length
	     * Implementation of test frame #3
	     * @throws Exception
	     */
	    @Test
	    public void mainTest7() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"fdsafff", "", "--", inputFile1.getPath()};
	        Main.main(args);
	        exception.expect(IllegalArgumentException.class);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the options are invalid
	     * Implementation of test frame #4
	     * @throws Exception
	     */
	    @Test//(expected = IllegalArgumentException.class)
	    public void mainTest8() throws Exception {
	    	
	    	File inputFile1 = createEmptyFile();
		     
	    	
	    	String args[] = {"-k", "test", "hey", "--", inputFile1.getPath()};
	    	//exception.expect(IllegalArgumentException.class);
	        Main.main(args);
	        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior when the option is -b on empty file
	     * Implementation of test frame #9
	     * @throws Exception
	     */
    //Type C <-b flag isn't creating a backup>
	    @Test
	    public void mainTest9() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-b", "", "", "--", inputFile1.getPath()};
	        Main.main(args);
	      
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", 0, actual1.length());
	        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -f on empty file
	     * Implementation of test frame #10
	     * @throws Exception
	     */
	    @Test
	    public void mainTest10() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-f", "", "", "--", inputFile1.getPath()};
	        Main.main(args);
	      
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", 0, actual1.length());
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -l on empty file
	     * Implementation of test frame #11
	     * @throws Exception
	     */
	    @Test
	    public void mainTest11() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-l", "", "", "--", inputFile1.getPath()};
	        Main.main(args);
	      
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", 0, actual1.length());
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -i on empty file
	     * Implementation of test frame #12
	     * @throws Exception
	     */
	    @Test
	    public void mainTest12() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-i", "", "", "--", inputFile1.getPath()};
	        Main.main(args);
	      
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", 0, actual1.length());
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -b on an empty file with to string as non empty
	     * Implementation of test frame #13
	     * @throws Exception
	     */
    //Type A
	    @Test
	    public void mainTest13() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-b", "", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	        String expected1 = "";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", actual1, expected1);
	        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior when the option is -f on an empty file with to string as non empty
	     * Implementation of test frame #14
	     * @throws Exception
	     */
    //Type A
	    @Test
	    public void mainTest14() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-f", "", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	        String expected1 = "";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", actual1, expected1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior when the option is -l on an empty file with to string as non empty
	     * Implementation of test frame #15
	     * @throws Exception
	     */
    //Type A
	    @Test
	    public void mainTest15() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-l", "", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	        String expected1 = "";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", actual1, expected1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior when the option is -i on an empty file with to string as non empty
	     * Implementation of test frame #16
	     * @throws Exception
	     */
    //Type A
	    @Test
	    public void mainTest16() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-i", "", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	        String expected1 = "";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", actual1, expected1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -b on many files where to and from is emtpy
	     * Implementation of test frame #17
	     * @throws Exception
	     */
    //Type C <-b flag doesn't create a backup file>
	    @Test
	    public void mainTest17() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-b", "", "", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", 0, actual1.length());
	        assertEquals("The files differ!", 0, actual2.length());
	        assertEquals("The files differ!", 0, actual3.length());
	        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -f on many files where to and from is emtpy
	     * Implementation of test frame #18
	     * @throws Exception
	     */
	    @Test
	    public void mainTest18() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-f", "", "", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", 0, actual1.length());
	        assertEquals("The files differ!", 0, actual2.length());
	        assertEquals("The files differ!", 0, actual3.length());
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -l on many files where to and from is empty
	     * Implementation of test frame #19
	     * @throws Exception
	     */
	    @Test
	    public void mainTest19() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-l", "", "", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", 0, actual1.length());
	        assertEquals("The files differ!", 0, actual2.length());
	        assertEquals("The files differ!", 0, actual3.length());
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -i on many files where to and from is emtpy
	     * Implementation of test frame #20
	     * @throws Exception
	     */
	    @Test
	    public void mainTest20() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-i", "", "", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", 0, actual1.length());
	        assertEquals("The files differ!", 0, actual2.length());
	        assertEquals("The files differ!", 0, actual3.length());
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -b on many files where to is non empty
	     * Implementation of test frame #21
	     * @throws Exception
	     */
    //Type A
	    @Test
	    public void mainTest21() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-b", "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	       String expected = "";
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertEquals("The files differ!", expected, actual2);
	        assertEquals("The files differ!", expected, actual3);
	        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertTrue(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertTrue(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -f on many files where to is non empty
	     * Implementation of test frame #22
	     * @throws Exception
	     */
    //Type A
	    @Test
	    public void mainTest22() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-f", "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	        String expected = "";
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertEquals("The files differ!", expected, actual2);
	        assertEquals("The files differ!", expected, actual3);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -l on many files where to is non empty
	     * Implementation of test frame #23
	     * @throws Exception
	     */
    //Type A
	    @Test
	    public void mainTest23() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-l", "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	        String expected = "";
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertEquals("The files differ!", expected, actual2);
	        assertEquals("The files differ!", expected, actual3);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -i on many files where to is non empty
	     * Implementation of test frame #24
	     * @throws Exception
	     */
    //Type A
	    @Test
	    public void mainTest24() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-i", "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	        String expected = "";
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertEquals("The files differ!", expected, actual2);
	        assertEquals("The files differ!", expected, actual3);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior when we have multiple options but to and from are empty and file is empty
	     * Implementation of test frame #25
	     * @throws Exception
	     */
	    @Test
	    public void mainTest25() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-l", "-i" , "", "", "--", inputFile1.getPath()};
	        Main.main(args);
	
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", actual1.length(), 0);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when we have multiple options and to is non emtpy and file is empty
	     * Implementation of test frame #26
	     * @throws Exception
	     */
	    @Test
	    public void mainTest26() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-b", "-i" , "aaaaa", "", "--", inputFile1.getPath()};
	        Main.main(args);
	
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        String expected = "";
	        assertEquals("The files differ!", actual1, expected);
	        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior when there are multiple options on multiple empty files with from and to empty
	     * Implementation of test frame #27
	     * @throws Exception
	     */
	    @Test
	    public void mainTest27() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-i", "-l" , "", "", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	    	
	        Main.main(args);
	  
	        int expected = 0;
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected, actual1.length());
	        assertEquals("The files differ!", expected, actual2.length());
	        assertEquals("The files differ!", expected, actual3.length());
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when there are multiple options on multiple empty files with to non empty
	     * Implementation of test frame #28
	     * @throws Exception
	     */
    //Type A
	    @Test
	    public void mainTest28() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-i", "-l" , "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	        String expected = "";
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertEquals("The files differ!", expected, actual2);
	        assertEquals("The files differ!", expected, actual3);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior when there are multiple options on multiple empty files with to non empty
	     * Implementation of test frame #29
	     * @throws Exception
	     */
        // Type A
	    @Test
	    public void mainTest29() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-i", "-l" , "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	        String expected = "";
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertEquals("The files differ!", expected, actual2);
	        assertEquals("The files differ!", expected, actual3);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior on an non empty file
	     * Implementation of test frame #30
	     * @throws Exception
	     */
	    @Test
	    public void mainTest30() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	  
	        String expected = "How much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with from empty and to non empty
	     * Implementation of test frame #31
	     * @throws Exception
	     */
	    @Test
	    public void mainTest31() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"How", "", "--", inputFile1.getPath()};
	        Main.main(args);
	  
	        String expected = " much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with from non emtpy and to non empty with alpha
	     * numeric characters
	     * Implementation of test frame #32
	     * @throws Exception
	     */
	    @Test
	    public void mainTest32() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"How", "abc123", "--", inputFile1.getPath()};
	        Main.main(args);
	  
	        String expected = "abc123 much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with from non emtpy and to non empty with special
	     * characters
	     * Implementation of test frame #33
	     * @throws Exception
	     */
    //Type C <Dollar sign in the to string may have caused unexpected regex outputs using matcher's appendReplacement , or replaceAll>
	    @Test
	    public void mainTest33() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"How", "&$^%", "--", inputFile1.getPath()};
	        Main.main(args);
	  
	        String expected = "&$^% much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with from non emtpy and to non empty with spaces
	     * Implementation of test frame #34
	     * @throws Exception
	     */
	    @Test
	    public void mainTest34() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"How", "    ", "--", inputFile1.getPath()};
	        Main.main(args);
	  
	        String expected = "     much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with from non emtpy and to non empty with spaces
	     * Implementation of test frame #35
	     * @throws Exception
	     */
	    @Test
	    public void mainTest35() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"How", "    ", "--", inputFile1.getPath()};
	        Main.main(args);
	  
	        String expected = "     much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with from non emtpy and to non empty with spaces
	     * Implementation of test frame #36
	     * @throws Exception
	     */
	    @Test
	    public void mainTest36() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"How", "    ", "--", inputFile1.getPath()};
	        Main.main(args);
	  
	        String expected = "     much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior on an non empty files with from non emtpy and to non empty 
	     * Implementation of test frame #37
	     * @throws Exception
	     */
	    @Test
	    public void mainTest37() throws Exception {
	        File inputFile1 = createInputFile1();
	        File inputFile2 = createInputFile1();
	        File inputFile3 = createInputFile1();
	     

	        String args[] = {"How", "abc123", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	        String expected = "abc123 much Wood can a woodchuck chuck";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertEquals("The files differ!", expected, actual2);
	        assertEquals("The files differ!", expected, actual3);
	    
	        
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty files with from non emtpy and to non empty with alphanumeric
	     * argument
	     * Implementation of test frame #38
	     * @throws Exception
	     */
	    @Test
	    public void mainTest38() throws Exception {
	        File inputFile1 = createInputFile1();
	        File inputFile2 = createInputFile1();
	        File inputFile3 = createInputFile1();
	     

	        String args[] = {"How", "abc123", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	        String expected = "abc123 much Wood can a woodchuck chuck";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertEquals("The files differ!", expected, actual2);
	        assertEquals("The files differ!", expected, actual3);
	    
	        
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty files with from non emtpy and to non empty with special
	     * characters argument
	     * Implementation of test frame #39
	     * @throws Exception
	     */
    //Type C <Dollar sign in the to string may have caused unexpected regex outputs using matcher's appendReplacement , or replaceAll>
	    @Test
	    public void mainTest39() throws Exception {
	        File inputFile1 = createInputFile1();
	        File inputFile2 = createInputFile1();
	        File inputFile3 = createInputFile1();
	     

	        String args[] = {"How", "#$%^", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	        String expected = "#$%^ much Wood can a woodchuck chuck";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertEquals("The files differ!", expected, actual2);
	        assertEquals("The files differ!", expected, actual3);
	    
	        
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty files with from non emtpy and to non empty with spaces
	     * argument 
	     * Implementation of test frame #40
	     * @throws Exception
	     */
	    @Test
	    public void mainTest40() throws Exception {
	        File inputFile1 = createInputFile1();
	        File inputFile2 = createInputFile1();
	        File inputFile3 = createInputFile1();
	     

	        String args[] = {"How", "    ", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	        String expected = "     much Wood can a woodchuck chuck";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        String actual2 = getFileContent(inputFile2.getPath());
	        String actual3 = getFileContent(inputFile3.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertEquals("The files differ!", expected, actual2);
	        assertEquals("The files differ!", expected, actual3);
	    
	        
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
	        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));

	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument b
	     * Implementation of test frame #41
	     * @throws Exception
	     */
    //Type C <-b flag isn't creating a backup, or isn't creating one with extension .bck>
	    @Test
	    public void mainTest41() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"-b","", "", "--", inputFile1.getPath()};
	        Main.main(args);
	  
	        String expected = "How much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        String actual2 = getFileContent(inputFile1.getPath() + ".bck");
	        assertEquals("The files differ!", expected, actual2);
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument f
	     * Implementation of test frame #42
	     * @throws Exception
	     */
	    @Test
	    public void mainTest42() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"-f","", "", "--", inputFile1.getPath()};
	        Main.main(args);
	  
	        
	        String actual1 = getFileContent(inputFile1.getPath());
	        String expected = "How much Wood can a woodchuck chuck";
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument l
	     * Implementation of test frame #42
	     * @throws Exception
	     */
	    @Test
	    public void mainTest43() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"-l","", "", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String actual1 = getFileContent(inputFile1.getPath());
	        String expected = "How much Wood can a woodchuck chuck";
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument i
	     * Implementation of test frame #44
	     * @throws Exception
	     */
	    @Test
	    public void mainTest44() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"-i","", "", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String actual1 = getFileContent(inputFile1.getPath());
	        String expected = "How much Wood can a woodchuck chuck";
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument b and to string non emtpy
	     * Implementation of test frame #45
	     * @throws Exception
	     */
        //Type C <-b flag isn't creating a backup, or isn't creating one with extension .bck>
	    @Test
	    public void mainTest45() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"-b","", "", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "How much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	        String actual2 = getFileContent(inputFile1.getPath() + ".bck");
	        assertEquals("The files differ!", expected, actual2);
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument f with from empty and to nonempty
	     * Implementation of test frame #46
	     * @throws Exception
	     */
	    @Test
	    public void mainTest46() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"-f","", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "How much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument l with from empty and to nonempty
	     * Implementation of test frame #47
	     * @throws Exception
	     */
	    @Test
	    public void mainTest47() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"-l","", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "How much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument i with from empty and to nonempty
	     * Implementation of test frame #48
	     * @throws Exception
	     */
	    @Test
	    public void mainTest48() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"-i","", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "How much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument b with from non empty and to empty
	     * Implementation of test frame #49
	     * @throws Exception
	     */
	    @Test
	    public void mainTest49() throws Exception {
	    	File inputFile1 = createInputFile1();
	    
		     

	    	String args[] = {"-b","How", "", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = " much Wood can a woodchuck chuck";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument b with from non empty and to empty
	     * Implementation of test frame #50
	     * @throws Exception
	     */
	    @Test
	    public void mainTest50() throws Exception {
	    	File inputFile1 = createInputFile2();
	    
		     

	    	String args[] = {"-f","How", "", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "dy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"howdy Bill\" twice";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument f with from non empty and to empty
	     * Implementation of test frame #51
	     * @throws Exception
	     */
	    @Test
	    public void mainTest51() throws Exception {
	    	File inputFile1 = createInputFile2();
	    
		     

	    	String args[] = {"-f","How", "", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "dy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"howdy Bill\" twice";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument i with from non empty and to empty
	     * Implementation of test frame #52
	     * @throws Exception
	     */
	    @Test
	    public void mainTest52() throws Exception {
	    	File inputFile1 = createInputFile2();
	    
		     

	    	String args[] = {"-i","How", "", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "dy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"dy Bill\" twice";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument b with from non empty and to non empty
	     * Implementation of test frame #53
	     * @throws Exception
	     */
	    @Test
	    public void mainTest53() throws Exception {
	    	File inputFile1 = createInputFile2();
	    
		     

	    	String args[] = {"-b","How", "abc123", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "abc123dy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"howdy Bill\" twice";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument f with from non empty and to non empty
	     * Implementation of test frame #54
	     * @throws Exception
	     */
	    @Test
	    public void mainTest54() throws Exception {
	    	File inputFile1 = createInputFile2();
	    
		     

	    	String args[] = {"-f","How", "abc123", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "abc123dy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"howdy Bill\" twice";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument l with from non empty and to non empty
	     * Implementation of test frame #55
	     * @throws Exception
	     */
	    @Test
	    public void mainTest55() throws Exception {
	    	File inputFile1 = createInputFile2();
	    
		     

	    	String args[] = {"-l","How", "abc123", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "abc123dy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"howdy Bill\" twice";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument i with from non empty and to non empty
	     * Implementation of test frame #56
	     * @throws Exception
	     */
	    @Test
	    public void mainTest56() throws Exception {
	    	File inputFile1 = createInputFile2();
	    
		     

	    	String args[] = {"-i","How", "abc123", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "abc123dy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"abc123dy Bill\" twice";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument b with from non empty and to non empty
	     * with special cahracters
	     * Implementation of test frame #57
	     * @throws Exception
	     */
    // Type C <Dollar sign in the to string may have caused unexpected regex outputs using matcher's appendReplacement , or replaceAll>
	    @Test
	    public void mainTest57() throws Exception {
	    	File inputFile1 = createInputFile2();
	    
		     

	    	String args[] = {"-b","How", "@#$%", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "@#$%dy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"howdy Bill\" twice";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument f with from non empty and to non empty
	     * with special cahracters
	     * Implementation of test frame #58
	     * @throws Exception
	     */
        // Type C <Dollar sign in the to string may have caused unexpected regex outputs using matcher's appendReplacement for flag -f>
	    @Test
	    public void mainTest58() throws Exception {
	    	File inputFile1 = createInputFile2();
	    
		     

	    	String args[] = {"-f","How", "@#$%", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "@#$%dy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"howdy Bill\" twice";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument l with from non empty and to non empty
	     * with special cahracters
	     * Implementation of test frame #59
	     * @throws Exception
	     */
	    @Test
	    public void mainTest59() throws Exception {
	    	File inputFile1 = createInputFile2();
	    
		     

	    	String args[] = {"-l","How", "@#$%", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "@#$%dy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"howdy Bill\" twice";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
	    
	    /**
	     * Purpose: Test of behavior on an non empty file with one argument f with from non empty and to non empty
	     * with special cahracters
	     * Implementation of test frame #60
	     * @throws Exception
	     */
        // Type C <Dollar sign in the to string may have caused unexpected regex outputs using matcher's appendReplacement for flag -f>
	    @Test
	    public void mainTest60() throws Exception {
	    	File inputFile1 = createInputFile2();
	    
		     

	    	String args[] = {"-f","How", "@#$%", "--", inputFile1.getPath()};
	        Main.main(args);
	  

	        String expected = "@#$%dy Bill,\n" +
	                "This is another test file for the replace utility\n" +
	                "that contains a list:\n" +
	                "-a) Item 1\n" +
	                "-b) Item 2\n" +
	                "...\n" +
	                "and says \"howdy Bill\" twice";
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", expected, actual1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	    }
    private File createInputFile4() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Howdy Bill," + System.lineSeparator() +
                         "This is a test file for the replace utility" + System.lineSeparator() +
                         "Let's make sure it has at least a few lines" + System.lineSeparator() +
                         "so that we can create some interesting test cases..." + System.lineSeparator() +
                         "And let's say \"howdy bill\" again!");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile5() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Howdy Bill," + System.lineSeparator() +
                         "This is another test file for the replace utility" + System.lineSeparator() +
                         "that contains a list:" + System.lineSeparator() +
                         "-a) Item 1" + System.lineSeparator() +
                         "-b) Item 2" + System.lineSeparator() +
                         "..." + System.lineSeparator() +
                         "and says \"howdy Bill\" twice");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile6() throws Exception {
        File file1 = createTmpFile();
        FileWriter fileWriter = new FileWriter(file1);
        
        fileWriter.write("Howdy Bill, have you learned your abc and 123?" + System.lineSeparator() +
                         "It is important to know your abc and 123," +
                         "so you should study it" + System.lineSeparator() +
                         "and then repeat with me: abc and 123");
        
        fileWriter.close();
        return file1;
    }
    
    private File createInputFile7() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);
        
        fileWriter.write("");
        
        fileWriter.close();
        return file;
    }
    
    private File createInputFile8() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);
        
        fileWriter.write("First line: not replaced" + System.lineSeparator() +
                         "Second line: not replaced" + System.lineSeparator() +
                         "Third line: not replaced" + System.lineSeparator() +
                         "Last line: not replaced" + System.lineSeparator());
        
        fileWriter.close();
        return file;
    }
    
    private File createInputFile9() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);
        
        fileWriter.write("ababab");
        
        fileWriter.close();
        return file;
    }
    
    private File createInputFile10() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);
        
        fileWriter.write("The goal here is to replace string \"-i\" with" + System.lineSeparator() +
                         "string \"-f\". Since we may also want to do multiple replacements," + System.lineSeparator() +
                         "we will repeat the two strings here: -i and -f");
        
        fileWriter.close();
        return file;
    }
    
    private File createInputFile11() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);
        
        fileWriter.write("Let's have some numbers in the file: 12345678");
        
        fileWriter.close();
        return file;
    }
    
    private File createInputFile12() throws Exception {
        File file = createTmpFile();
        FileWriter fileWriter = new FileWriter(file);
        
        fileWriter.write("-- -- -- --");
        
        fileWriter.close();
        return file;
    }

    
    // Actual test cases
    
    @Test
    public void mainTestAddOn7() {
        String args[] = {"-a", "-b"};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn8() throws Exception {
        File inputFile = createInputFile4();
        String args[] = {"Howdy", "Hello", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "Hello Bill," + System.lineSeparator() +
        "This is a test file for the replace utility" + System.lineSeparator() +
        "Let's make sure it has at least a few lines" + System.lineSeparator() +
        "so that we can create some interesting test cases..." + System.lineSeparator() +
        "And let's say \"howdy bill\" again!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
        
    }
    
    @Test
    public void mainTestAddOn9() throws Exception {
        File inputFile = createInputFile4();
        String args[] = {"howdy", "Hello", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy Bill," + System.lineSeparator() +
        "This is a test file for the replace utility" + System.lineSeparator() +
        "Let's make sure it has at least a few lines" + System.lineSeparator() +
        "so that we can create some interesting test cases..." + System.lineSeparator() +
        "And let's say \"Hello bill\" again!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn10() throws Exception {
        File inputFile = createInputFile4();
        String args[] = {"-i", "howdy", "Hello", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "Hello Bill," + System.lineSeparator() +
        "This is a test file for the replace utility" + System.lineSeparator() +
        "Let's make sure it has at least a few lines" + System.lineSeparator() +
        "so that we can create some interesting test cases..." + System.lineSeparator() +
        "And let's say \"Hello bill\" again!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn11() throws Exception {
        File inputFile = createInputFile4();
        String args[] = {"-f", "-l", "-i", "Bill", "William", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy William," + System.lineSeparator() +
        "This is a test file for the replace utility" + System.lineSeparator() +
        "Let's make sure it has at least a few lines" + System.lineSeparator() +
        "so that we can create some interesting test cases..." + System.lineSeparator() +
        "And let's say \"howdy William\" again!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn12() throws Exception {
        File inputFile = createInputFile4();
        String args[] = {"lines" + System.lineSeparator() + "so that ", "lines." +
            System.lineSeparator() + "In this way, ", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy Bill," + System.lineSeparator() +
        "This is a test file for the replace utility" + System.lineSeparator() +
        "Let's make sure it has at least a few lines." + System.lineSeparator() +
        "In this way, we can create some interesting test cases..." + System.lineSeparator() +
        "And let's say \"howdy bill\" again!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn13() throws Exception {
        File inputFile = createInputFile4();
        String args[] = {"-b", "Let's make sure", "Let's make sure", "--", inputFile.getPath()};
        Main.main(args);
        String expected = getFileContent(inputFile.getPath() + ".bck");
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn14() throws Exception {
        File inputFile = createInputFile4();
        String expected = getFileContent(inputFile.getPath());
        String args1[] = {"Let's make sure", "We hope", "--", inputFile.getPath()};
        Main.main(args1);
        String args2[] = {"We hope", "Let's make sure", "--", inputFile.getPath()};
        Main.main(args2);
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn15() throws Exception {
        File inputFile = createInputFile4();
        String args1[] = {"-b", "-f", "-i", "bill", "William", "--", inputFile.getPath()};
        Main.main(args1);
        String actual1 = getFileContent(inputFile.getPath());
        File inputFile2 = createInputFile4();
        String args2[] = {"-f", "-i", "-b", "bill", "William", "--", inputFile2.getPath()};
        Main.main(args2);
        String actual2 = getFileContent(inputFile.getPath());
        File inputFile3 = createInputFile4();
        String args3[] = {"-i", "-b", "-f", "bill", "William", "--", inputFile3.getPath()};
        Main.main(args3);
        String actual3 = getFileContent(inputFile.getPath());
        File inputFile4 = createInputFile4();
        String args4[] = {"-i", "-f", "-b", "bill", "William", "--", inputFile4.getPath()};
        Main.main(args4);
        String actual4 = getFileContent(inputFile.getPath());
        String expected = "Howdy William," + System.lineSeparator() +
        "This is a test file for the replace utility" + System.lineSeparator() +
        "Let's make sure it has at least a few lines" + System.lineSeparator() +
        "so that we can create some interesting test cases..." + System.lineSeparator() +
        "And let's say \"howdy bill\" again!";
        assertEquals("The files differ!", expected, actual1);
        assertEquals("The files differ!", expected, actual2);
        assertEquals("The files differ!", expected, actual3);
        assertEquals("The files differ!", expected, actual4);
        assertTrue(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn16() throws Exception {
        File inputFile = createInputFile4();
        String expected = getFileContent(inputFile.getPath());
        String args[] = {"Let's", "Let us", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn17() throws Exception {
        File inputFile = createInputFile4();
        String args[] = {"s", "5", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy Bill," + System.lineSeparator() +
        "Thi5 i5 a te5t file for the replace utility" + System.lineSeparator() +
        "Let'5 make 5ure it ha5 at lea5t a few line5" + System.lineSeparator() +
        "5o that we can create 5ome intere5ting te5t ca5e5..." + System.lineSeparator() +
        "And let'5 5ay \"howdy bill\" again!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn18() throws Exception {
        File inputFile = createInputFile4();
        String args[] = {"l", "1", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy Bi11," + System.lineSeparator() +
        "This is a test fi1e for the rep1ace uti1ity" + System.lineSeparator() +
        "Let's make sure it has at 1east a few 1ines" + System.lineSeparator() +
        "so that we can create some interesting test cases..." + System.lineSeparator() +
        "And 1et's say \"howdy bi11\" again!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn19() throws Exception {
        File inputFile = createInputFile4();
        String args[] = {"-i", "l", "1", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy Bi11," + System.lineSeparator() +
        "This is a test fi1e for the rep1ace uti1ity" + System.lineSeparator() +
        "1et's make sure it has at 1east a few 1ines" + System.lineSeparator() +
        "so that we can create some interesting test cases..." + System.lineSeparator() +
        "And 1et's say \"howdy bi11\" again!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn20() throws Exception {
        File inputFile = createInputFile4();
        String args[] = {"-i", "-f", "let's", "Let us", "Let's", "let us", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "Howdy Bill," + System.lineSeparator() +
        "This is a test file for the replace utility" + System.lineSeparator() +
        "Let us make sure it has at least a few lines" + System.lineSeparator() +
        "so that we can create some interesting test cases..." + System.lineSeparator() +
        "And let us say \"howdy bill\" again!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn21() throws Exception {
        File inputFile = createInputFile10();
        String args[] = {"-i", "-f", "--", inputFile.getPath()};
        Main.main(args);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn22() throws Exception {
        File inputFile = createInputFile10();
        String args[] = {"-i", "-f", "the goal", "The objective", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "The objective here is to replace string \"-i\" with" + System.lineSeparator() +
        "string \"-f\". Since we may also want to do multiple replacements," + System.lineSeparator() +
        "we will repeat the two strings here: -i and -f";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn23() throws Exception {
        File inputFile = createInputFile10();
        String args[] = {"the goal", "The objective", "-i", "-f", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "The goal here is to replace string \"-f\" with" + System.lineSeparator() +
        "string \"-f\". Since we may also want to do multiple replacements," + System.lineSeparator() +
        "we will repeat the two strings here: -f and -f";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn24() throws Exception {
        File inputFile = createInputFile10();
        String args[] = {"--", "-i", "-f", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "The goal here is to replace string \"-f\" with" + System.lineSeparator() +
        "string \"-f\". Since we may also want to do multiple replacements," + System.lineSeparator() +
        "we will repeat the two strings here: -f and -f";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn25() throws Exception {
        File inputFile = createInputFile10();
        String args[] = {"-f", "--", "-i", "-f", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "The goal here is to replace string \"-f\" with" + System.lineSeparator() +
        "string \"-f\". Since we may also want to do multiple replacements," + System.lineSeparator() +
        "we will repeat the two strings here: -i and -f";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn26() throws Exception {
        File inputFile = createInputFile10();
        String args[] = {"-l", "--", "-i", "-f", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "The goal here is to replace string \"-i\" with" + System.lineSeparator() +
        "string \"-f\". Since we may also want to do multiple replacements," + System.lineSeparator() +
        "we will repeat the two strings here: -f and -f";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn27() throws Exception {
        File inputFile = createInputFile10();
        String args[] = {"-f", "-l", "--", "-i", "-f", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "The goal here is to replace string \"-f\" with" + System.lineSeparator() +
        "string \"-f\". Since we may also want to do multiple replacements," + System.lineSeparator() +
        "we will repeat the two strings here: -f and -f";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn28() throws Exception {
        File inputFile = createInputFile11();
        String args[] = {"Let's have some numbers in the file: 12345678", "New content", "--",
            inputFile.getPath()};
        Main.main(args);
        String expected = "New content";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn29() throws Exception {
        File inputFile = createInputFile11();
        String args[] = {"Let's have some numbers in the file: 12345678", "", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn30() throws Exception {
        File inputFile = createInputFile11();
        String args[] = {"", "Hey", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "Let's have some numbers in the file: 12345678";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn31() throws Exception {
        File inputFile = createInputFile11();
        String args[] = {"--", inputFile.getPath()};
        Main.main(args);
        String expected = "Let's have some numbers in the file: 12345678";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertEquals("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- <filename> [<filename>]*", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn32() throws Exception {
        File inputFile = createInputFile4();
        inputFile.delete();
        String args[] = {"a", "b", "--", inputFile.getPath()};
        Main.main(args);
        assertEquals("File " + inputFile.getName() + " not found", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn33() throws Exception {
        File inputFile1 = createInputFile4();
        File inputFile2 = createInputFile5();
        File inputFile3 = createInputFile6();
        inputFile2.delete();
        
        String args[] = {"-i", "Howdy", "Hello", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);
        
        String expected1 = "Hello Bill," + System.lineSeparator() +
        "This is a test file for the replace utility" + System.lineSeparator() +
        "Let's make sure it has at least a few lines" + System.lineSeparator() +
        "so that we can create some interesting test cases..." + System.lineSeparator() +
        "And let's say \"Hello bill\" again!";
        String expected3 = "Hello Bill, have you learned your abc and 123?" + System.lineSeparator() +
        "It is important to know your abc and 123," +
        "so you should study it" + System.lineSeparator() +
        "and then repeat with me: abc and 123";
        
        String actual1 = getFileContent(inputFile1.getPath());
        String actual3 = getFileContent(inputFile3.getPath());
        
        assertEquals("The files differ!", expected1, actual1);
        assertEquals("The files differ!", expected3, actual3);
        
        assertEquals("File " + inputFile2.getName() + " not found", errStream.toString().trim());
        
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }
    
    @Test
    public void mainTestAddOn34() throws Exception {
        File inputFile1 = createInputFile4();
        File inputFile2 = createInputFile5();
        File inputFile3 = createInputFile6();
        inputFile1.delete();
        inputFile2.delete();
        
        String args[] = {"-i", "Howdy", "Hello", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
        Main.main(args);
        
        String expected3 = "Hello Bill, have you learned your abc and 123?" + System.lineSeparator() +
        "It is important to know your abc and 123," +
        "so you should study it" + System.lineSeparator() +
        "and then repeat with me: abc and 123";
        
        String actual3 = getFileContent(inputFile3.getPath());
        
        assertEquals("The files differ!", expected3, actual3);
        
        assertEquals("File " + inputFile1.getName() + " not found" + System.lineSeparator() +
                     "File " + inputFile2.getName() + " not found",
                     errStream.toString().trim());
        
        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile2.getPath() + ".bck")));
        assertFalse(Files.exists(Paths.get(inputFile3.getPath() + ".bck")));
    }
    
    @Test
    public void mainTestAddOn35() throws Exception {
        File inputFile = createInputFile8();
        String args[] = {"not replaced", "replaced", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "First line: replaced" + System.lineSeparator() +
        "Second line: replaced" + System.lineSeparator() +
        "Third line: replaced" + System.lineSeparator() +
        "Last line: replaced" + System.lineSeparator();
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn36() throws Exception {
        File inputFile = createInputFile8();
        String args[] = {"-f", "not replaced", "replaced", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "First line: replaced" + System.lineSeparator() +
        "Second line: not replaced" + System.lineSeparator() +
        "Third line: not replaced" + System.lineSeparator() +
        "Last line: not replaced" + System.lineSeparator();
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn37() throws Exception {
        File inputFile = createInputFile8();
        String args[] = {"-l", "not replaced", "replaced", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "First line: not replaced" + System.lineSeparator() +
        "Second line: not replaced" + System.lineSeparator() +
        "Third line: not replaced" + System.lineSeparator() +
        "Last line: replaced" + System.lineSeparator();
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn38() throws Exception {
        File inputFile = createInputFile8();
        String args[] = {"-f", "-l", "not replaced", "replaced", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "First line: replaced" + System.lineSeparator() +
        "Second line: not replaced" + System.lineSeparator() +
        "Third line: not replaced" + System.lineSeparator() +
        "Last line: replaced" + System.lineSeparator();
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn39() throws Exception {
        File inputFile = createInputFile8();
        String args[] = {"-l", "-f", "not replaced", "replaced", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "First line: replaced" + System.lineSeparator() +
        "Second line: not replaced" + System.lineSeparator() +
        "Third line: not replaced" + System.lineSeparator() +
        "Last line: replaced" + System.lineSeparator();
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn40() throws Exception {
        File inputFile = createInputFile9();
        String args[] = {"abab", "<repl>", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "<repl>ab";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn41() throws Exception {
        File inputFile = createInputFile9();
        String args[] = {"-l", "abab", "<repl>", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "ab<repl>";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn42() throws Exception {
        File inputFile = createInputFile9();
        String args[] = {"-f", "-l", "abab", "<repl>", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "<repl>ab";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn43() throws Exception {
        File inputFile = createInputFile9();
        String args[] = {"-f", "-f", "-f", "-l", "-l", "-l", "abab", "<repl>", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "<repl>ab";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn44() throws Exception {
        File inputFile = createInputFile9();
        String args[] = {"-l", "-f", "--", "abab", "<repl>", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "<repl>ab";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }
    
    @Test
    public void mainTestAddOn45() throws Exception {
        File inputFile = createInputFile4();
        String args[] = {"Howdy", "Hello", "replace", "REPLACE", "sure it", "sure that it", "--", inputFile.getPath()};
        Main.main(args);
        String expected = "Hello Bill," + System.lineSeparator() +
        "This is a test file for the REPLACE utility" + System.lineSeparator() +
        "Let's make sure that it has at least a few lines" + System.lineSeparator() +
        "so that we can create some interesting test cases..." + System.lineSeparator() +
        "And let's say \"howdy bill\" again!";
        String actual = getFileContent(inputFile.getPath());
        assertEquals("The files differ!", expected, actual);
        assertFalse(Files.exists(Paths.get(inputFile.getPath() + ".bck")));
        assertEquals("", errStream.toString().trim());
    }


}


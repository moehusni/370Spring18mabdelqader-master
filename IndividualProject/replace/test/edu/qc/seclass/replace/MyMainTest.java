package edu.qc.seclass.replace;

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
	    @Test
	    public void mainTest2() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	        String args[] = {"", "fdssf", "--", inputFile1.getPath()};
	        Main.main(args);

	        String expected1 = "fdssf";
	        

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
	    @Test
	    public void mainTest4() throws Exception {
	    	File inputFile1 = createEmptyFile();
	        File inputFile2 = createEmptyFile();
	        File inputFile3 = createEmptyFile();
		     

	        String args[] = {"", "fdssf", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);

	        String expected1 = "fdssf";
	        String expected2 = "fdssf";
	        String expected3 = "fdssf";
	        

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
	    @Test
	    public void mainTest13() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-b", "", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	        String expected1 = "aaaaa";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", actual1, expected1);
	        assertTrue(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior when the option is -f on an empty file with to string as non empty
	     * Implementation of test frame #14
	     * @throws Exception
	     */
	    @Test
	    public void mainTest14() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-f", "", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	        String expected1 = "aaaaa";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", actual1, expected1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior when the option is -l on an empty file with to string as non empty
	     * Implementation of test frame #15
	     * @throws Exception
	     */
	    @Test
	    public void mainTest15() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-l", "", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	        String expected1 = "aaaaa";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", actual1, expected1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    
	    /**
	     * Purpose: Test of behavior when the option is -i on an empty file with to string as non empty
	     * Implementation of test frame #16
	     * @throws Exception
	     */
	    @Test
	    public void mainTest16() throws Exception {
	    	File inputFile1 = createEmptyFile();
		     

	    	String args[] = {"-i", "", "aaaaa", "--", inputFile1.getPath()};
	        Main.main(args);
	        String expected1 = "aaaaa";
	       
	        String actual1 = getFileContent(inputFile1.getPath());
	        assertEquals("The files differ!", actual1, expected1);
	        assertFalse(Files.exists(Paths.get(inputFile1.getPath() + ".bck")));
	 
	    }
	    
	    /**
	     * Purpose: Test of behavior when the option is -b on many files where to and from is emtpy
	     * Implementation of test frame #17
	     * @throws Exception
	     */
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
	    @Test
	    public void mainTest21() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-b", "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	       String expected = "aaaaa";
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
	    @Test
	    public void mainTest22() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-f", "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	        String expected = "aaaaa";
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
	    @Test
	    public void mainTest23() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-l", "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	        String expected = "aaaaa";
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
	    @Test
	    public void mainTest24() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-i", "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	        String expected = "aaaaa";
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
	    @Test
	    public void mainTest28() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-i", "-l" , "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	        String expected = "aaaaa";
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
	    @Test
	    public void mainTest29() throws Exception {
	    	File inputFile1 = createEmptyFile();
	    	File inputFile2 = createEmptyFile();
	    	File inputFile3 = createEmptyFile();
		     

	    	String args[] = {"-i", "-l" , "", "aaaaa", "--", inputFile1.getPath(), inputFile2.getPath(), inputFile3.getPath()};
	        Main.main(args);
	  
	        String expected = "aaaaa";
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

}

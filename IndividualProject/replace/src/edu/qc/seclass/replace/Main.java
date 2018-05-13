package edu.qc.seclass.replace;

import java.io.BufferedReader;
import java.util.regex.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {

 boolean bFlag, fFlag, lFlag, iFlag;
 String from, to;
 ArrayList < String > fromAry;
 ArrayList < String > toAry;
 Scanner sc;
 List < TextFile > files;
 int parseArgumentStatus;

 public Main(String[] args) {
  files = new LinkedList < > ();
  fromAry = new ArrayList < > ();
  toAry = new ArrayList < > ();
  parseArgumentStatus = parseArguments(args);
 }

 private void performReplacement() {
  if (parseArgumentStatus < 0)
   return;
  if (bFlag) {
   for (TextFile tf: files) {
    String pathname = tf.getPathname();
    String newPathname = pathname + ".bck";
    File f = new File(newPathname);
    if (f.exists()) {
     int indexOfSlash = pathname.lastIndexOf('/');
     String nameOfFile = pathname.substring(indexOfSlash + 1, pathname.length());
     System.err.println("Not performing replace for " + nameOfFile + ": Backup file already exists");
     continue;
    }

    try {
     PrintWriter pw = new PrintWriter(new File(newPathname));
     pw.print(tf.getContents());
     pw.close();
    } catch (IOException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
    }

   }
  }
  	if (!fFlag && !lFlag && !iFlag) 
    replace(iFlag);
   else if (!fFlag && !lFlag && iFlag) 
    replace(iFlag);
   else if (!fFlag && lFlag && !iFlag) 
    replaceLastOccurence(iFlag);
   else if (!fFlag && lFlag && iFlag) 
    replaceLastOccurence(iFlag);
   else if (fFlag && !lFlag && !iFlag) 
    replaceFirstOccurence(iFlag);
   else if (fFlag && !lFlag && iFlag) 
    replaceFirstOccurence(iFlag);
   else if (fFlag && lFlag && !iFlag) {
    replaceFirstOccurence(iFlag);
    replaceLastOccurence(iFlag);
   } 
   else if (fFlag && lFlag && iFlag) {
    replaceFirstOccurence(iFlag);
    replaceLastOccurence(iFlag);
   }
   writeToFiles();
  }

  private void writeToFiles() {
   for (TextFile tf: files) {
    tf.write();
   }

  }

  private void replaceLastOccurence(boolean isCaseInsensitve) {

   int length = fromAry.size();
   for (int i = 0; i < length; i++) {
    String from = fromAry.get(i);
    String to = toAry.get(i);
    if (isCaseInsensitve) {
     for (TextFile tf: files) {
      String s = tf.getContents();
      if (s.isEmpty() && from.isEmpty()) {
       tf.setContents(to);
      } else if (!s.isEmpty() && from.isEmpty()) {
       usage();
      } else {
       s = s.replaceFirst("(?i)" + from, to);
       tf.setContents(s);
      }
     }
    } else {
     for (TextFile tf: files) {
      String s = tf.getContents();
      if (s.isEmpty() && from.isEmpty()) {
       tf.setContents(to);
      } else if (!s.isEmpty() && from.isEmpty()) {
       usage();
      } else {
       if (!s.contains(from))
        return;
       int index = s.lastIndexOf(from);
       s = s.substring(0, index) + to + s.substring(index + from.length());
       tf.setContents(s);
      }
     }
    }
   }
  }

  private void replaceFirstOccurence(boolean isCaseInsensitve) {
   int length = fromAry.size();
   for (int i = 0; i < length; i++) {
    String from = fromAry.get(i);
    String to = toAry.get(i);
    if (isCaseInsensitve) {
     for (TextFile tf: files) {
      String s = tf.getContents();
      if (s.isEmpty() && from.isEmpty()) {
       tf.setContents(to);
      } else if (!s.isEmpty() && from.isEmpty()) {
       usage();
      } else {
       s = s.replaceFirst("(?i)" + from, to);
       tf.setContents(s);
      }
     }
    } else {
     for (TextFile tf: files) {
      String s = tf.getContents();
      if (s.isEmpty() && from.isEmpty()) {
       tf.setContents(to);
      } else if (!s.isEmpty() && from.isEmpty()) {
       usage();
      } else {
       s = s.replaceFirst(Pattern.quote(from), Matcher.quoteReplacement(to));
       tf.setContents(s);
      }
     }
    }
   }
  }

  private void replace(boolean isCaseInsensitve) {
   int length = fromAry.size();
   for (int i = 0; i < length; i++) {
    String from = fromAry.get(i);
    String to = toAry.get(i);
    if (isCaseInsensitve) {
     for (TextFile tf: files) {
      String s = tf.getContents();
      if (s.isEmpty() && from.isEmpty()) {
       tf.setContents(to);
      } else if (!s.isEmpty() && from.isEmpty()) {
       usage();
      } else {
       s = s.replaceAll("(?i)" + from, to);
       tf.setContents(s);
      }
     }
    } else {
     for (TextFile tf: files) {
      String s = tf.getContents();
      if (s.isEmpty() && from.isEmpty()) {
       tf.setContents(to);
      } else if (!s.isEmpty() && from.isEmpty()) {
       usage();
      } else {
       s = s.replace(from, to);
       tf.setContents(s);
      }
     }
    }
   }
  }

  private int parseArguments(String[] args) {
   // TODO Auto-generated method stub
   int index = 0;
   boolean doubleSlashOption = false;
   while (!args[index].isEmpty() && args[index].charAt(0) == '-' && index < args.length) {
    String currentString = args[index];
    int option = parseOptions(currentString.substring(1, currentString.length()));
    index++;
    if (option == 1) {
     doubleSlashOption = true;
     break;
    }
    if (option == -1)
     return -1;

   }
   if (index >= args.length) {
    usage();
    return -1;
   }
   while (index + 1 < args.length) {
    if (!doubleSlashOption) {

     if (args[index + 1].equals("--")) {
      usage();
      return -1;
     }
     if (args[index].equals("--")) {
      break;
     }
    }
    if (isValidPath(args[index + 1])) {
     break;
    }

    fromAry.add(args[index]);
    toAry.add(args[index + 1]);
    index += 2;
   }
   if (index + 1 >= args.length) {
    usage();
    return -1;
   }
   if (!args[index].equals("--")) {
    usage();
    return -1;
   } else {
    index++;
   }
   for (; index < args.length; index++) {
    parseFiles(args[index]);
   }
   return 0;
  }
  public static boolean isValidPath(String path) {
   if (path.toLowerCase().endsWith(".tmp")) {
    return true;
   }
   return false;
  }

  private void parseFiles(String pathname) {
   try {
    int counter = 0;
    BufferedReader reader = new BufferedReader(
     new InputStreamReader(new FileInputStream(new File(pathname)), Charset.forName("UTF-8")));
    int c;
    while ((c = reader.read()) != -1) {
     char character = (char) c;
     if (!String.valueOf(character).matches(".")) {
      counter++;
     }
    }
    int newCounter = 0;
    sc = new Scanner(new BufferedReader(new FileReader(new File(pathname))));
    String s = "";
    while (sc.hasNextLine()) {
     String nextLine = sc.nextLine();
     s += nextLine;
     if (sc.hasNextLine()) {
      s += System.getProperty("line.separator");
      newCounter++;
     }
    }
    if (newCounter != counter) {
     s += System.getProperty("line.separator");
    }
    files.add(new TextFile(pathname, s));
   } catch (FileNotFoundException e) {
    int index = pathname.lastIndexOf('/');
    String filename = pathname.substring(index + 1);
    System.err.println("File " + filename + " not found");

   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
  }

  private boolean newLineExists(File file) throws IOException {
   RandomAccessFile fileHandler = new RandomAccessFile(file, "r");
   long fileLength = fileHandler.length() - 1;
   if (fileLength < 0) {
    fileHandler.close();
    return true;
   }
   fileHandler.seek(fileLength);
   byte readByte = fileHandler.readByte();
   fileHandler.close();

   if (readByte == 0xA || readByte == 0xD) {
    return true;
   }
   return false;
  }

  private void parseFromTo(String string, int fromToCounter) {

   if (fromToCounter == 0) {
    from = string;
   } else if (fromToCounter == 1) {
    to = string;
   } else {
    throw new IllegalArgumentException("fromToCounter isn't 0 nor 1");
   }
  }

  private int parseOptions(String substring) {
   if (substring.length() != 1) {
    usage();
    return -1;
   } else if (substring.equals("-")) {
    return 1;
   } else if (substring.equals("b")) {
    bFlag = true;
   } else if (substring.equals("f")) {
    fFlag = true;
   } else if (substring.equals("i")) {
    iFlag = true;
   } else if (substring.equals("l")) {
    lFlag = true;
   } else {
    usage();
    return -1;
   }

   return 0;

  }

  public static void main(String[] args) {
   Main replace = new Main(args);
   replace.performReplacement();
   replace.rest();
  }

  private void rest() {


  }

  private static void usage() {
   System.err.println("Usage: Replace [-b] [-f] [-l] [-i] <from> <to> -- " + "<filename> [<filename>]*");
  }
  public class TextFile
  {private String pathname,contents;private PrintWriter pw;public TextFile(String pathname,String contents)
  {this.pathname=pathname;this.contents=contents;try
  {pw=new PrintWriter(new File(pathname));}
  catch(IOException e)
  {e.printStackTrace();}}
  String getContents()
  {return contents;}
  void setContents(String contents)
  {this.contents=contents;}
  public String getPathname()
  {return pathname;}
  public void setPathname(String pathname)
  {this.pathname=pathname;}
  public void write()
  {pw.print(contents);pw.flush();}}
 }

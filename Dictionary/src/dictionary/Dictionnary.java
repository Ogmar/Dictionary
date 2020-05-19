
package dictionary;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Dictionnary {
	
	/**
	 * Program that generates a sorted dictionnary of words from an article
	 * @param args
	 */
	public static void main(String[] args) {
	    ArrayList <String> array= new ArrayList<String>();
		PrintWriter pw= null;
		Scanner sc=null;
		
		sc=new Scanner(System.in);
		System.out.print("Enter file name to be opened: ");
		String file_name=sc.nextLine();
		String word;
		int first=0;
		boolean valid=true;
		
		try {
			sc=new Scanner(new FileInputStream(file_name));
			while(sc.hasNext()) {
				first++;
				valid=true;
				//Write all words into a file
				word=sc.next();
				
				//Words have to a follow a set of conditions, trim down all words to an acceptable state
				
				if(!(word.indexOf('?') == -1 && word.indexOf(':') == -1 && word.indexOf(',') == -1
						&& word.indexOf(';') == -1 && word.indexOf('!') == -1 && word.indexOf('.') == -1)) {
					
					word= word.substring(0,word.length()-1);
					
				}
				
				if(word.indexOf('’')!=-1) {
					word= word.substring(0,word.indexOf('’'));
				}
				
				if(word.matches(".*\\d.*") || word.equals("=")) {
					valid=false;
				}
				
				if(word.length() == 1) {
					if(word.equalsIgnoreCase("a") || word.equalsIgnoreCase("i")) {
						
					}
					else {
						valid=false;
					}
				}
				
				//If word respects all set conditions set to uppercase and add it to arraylist
				if(valid) {
					word=word.toUpperCase();
					if(first==1) {
						array.add(word);
					}
					if(!(array.contains(word))){
						array.add(word);
					}
					
				}

			}
			sc.close();
			
		}catch(FileNotFoundException e){
			System.out.println(e);
		}
		
		String temp;

		//Sort array alphabetically
		for(int i=0;i<array.size();i++) {
			for(int j=i+1;j<array.size();j++) {
				if(array.get(i).compareTo(array.get(j))>0) {
					temp=array.set(i, array.get(j));
					array.set(j, temp);
					
				}
			}
		}
		
		//Write words in file and separate them from first letter basis
		try {
			pw = new PrintWriter(new FileOutputStream("SubDictionnary.txt",true));
			pw.println("The document produced this sub-dictionary, which includes "+array.size() +" entries.\n");
			
			for(int i=0;i<array.size();i++) {
				if(i==0) {
					pw.println(array.get(i).charAt(0)+"\n==");
				}
				if(i==array.size()-1) {
					pw.print(array.get(i));
					break;
				}
				if(array.get(i).charAt(0) != array.get(i+1).charAt(0)) {
					pw.println(array.get(i));
					pw.println();
					pw.println(array.get(i+1).charAt(0)+"\n==");
				}else {
					pw.println(array.get(i));
				}
	
			}
			pw.close();
		}catch(FileNotFoundException e) {
			System.out.println(e);
		}
		System.out.println("File Created, End of Program");

	}

}

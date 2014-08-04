package cluedo.load;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Load {
	private int width;
	private int height;
	private int size;
	
	public Load(String file){
		try {
			Scanner scan = new Scanner(new File(file));
			while(scan.hasNext()){
				String line = scan.nextLine();
				if(line.charAt(0) == '<'){
					String[] data = line.split("<");
					
					//System.out.println(line);
				}else{
					String[] data = line.split(",");
					for(int i = 0; i < data.length; i++){
						//System.out.printf(data[i]+" ");
					}
					//System.out.println();
				}				
			}
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		}
	}
}

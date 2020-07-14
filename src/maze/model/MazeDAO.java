package maze.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import org.json.JSONObject;
import org.json.JSONTokener;


public class MazeDAO {
	
	public void saveMaze(String fileName, TransferObject o) throws FileNotFoundException {
		fileName += ".json";
		fileName = "Saves/" + fileName;
		
		OutputStream out;
		
		File fileCheck = new File("Saves/");
		
		if(!fileCheck.exists()) { //creates que Save directory if its not created
			fileCheck.mkdir();
		}
	
		out = new FileOutputStream(new File(fileName));
		PrintStream ps = new PrintStream(out);	
		
		ps.println(o.getObjectInfo().toString(3));
		ps.close();
		
		System.out.println("File saved at " + fileName);
	}
	
	public TransferObject loadMaze(File file) throws FileNotFoundException {
		TransferObject tObj = new TransferObject();
		
		InputStream input = new FileInputStream(file);
		JSONObject objectInfo = new JSONObject(new JSONTokener(input));
		
		tObj.setObjectInfo(objectInfo);
		
		return tObj;
	}

}
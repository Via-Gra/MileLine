package mileline.diskmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import mileline.views.MileLineCzActivity;
import android.content.Context;

public class HardFile {
	private String fileName;

	public HardFile(String fileName) {
		this.fileName = fileName;
	}

	public void saveDataArray(ArrayList data) {
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = MileLineCzActivity.getSelf().openFileOutput(fileName,
					Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList loadDataArray() {
		boolean exists= false;
		String[] existingFiles = MileLineCzActivity.getSelf().fileList();
		for (String file : existingFiles)
			if (file.equals(fileName)){
				exists = true;
				break;
			}
		if (!exists) return null;
		FileInputStream fis;
		ArrayList result = null;
		ObjectInputStream ois;
		try {
			fis = MileLineCzActivity.getSelf().openFileInput(fileName);
			ois = new ObjectInputStream(fis);
			result = (ArrayList) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public void saveData(Object data) {
		ObjectOutputStream oos;
		FileOutputStream fos;
		try {
			fos = MileLineCzActivity.getSelf().openFileOutput(fileName,
					Context.MODE_PRIVATE);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(data);
			oos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Object loadData() {
		boolean exists= false;
		String[] existingFiles = MileLineCzActivity.getSelf().fileList();
		for (String file : existingFiles)
			if (file.equals(fileName)){
				exists = true;
				break;
			}
		if (!exists) return null;
		FileInputStream fis;
		Object result = null;
		ObjectInputStream ois;
		try {
			fis = MileLineCzActivity.getSelf().openFileInput(fileName);
			ois = new ObjectInputStream(fis);
			result = ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}

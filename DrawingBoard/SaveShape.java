import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 이름 : SaveShape
 * 역할 : 현재까지의 작업물을 저장하는 기능을 담당하는 클래스이다.
 *     : DrawPanel의 Object 정보를 담는 ArrayList를 Serialize하여 파일에 저장한다.
 */

public class SaveShape {

	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	public static void makeFile() {
		try {
			output = new ObjectOutputStream(new FileOutputStream(EditorFrame.currentPath));
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "파일 생성 중 오류가 발생했습니다.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void openFile() {

		try {
			input = new ObjectInputStream(new FileInputStream(EditorFrame.currentPath));
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "파일을 읽어오는 중 오류가 발생했습니다.", "IOException", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void addRecords() {

		try {
			output.writeObject(DrawPanel.shapes);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "파일을 기록하는 중 오류가 발생했습니다.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
		try {
			output.writeObject(DrawPanel.tempShapes);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "파일을 기록하는 중 오류가 발생했습니다.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
		try {
			output.writeObject(DrawPanel.copyShapes);
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "파일을 기록하는 중 오류가 발생했습니다.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
		
		AxisPanel.setText("저장 성공!");
		
	}

	public static void readRecords() {

		try {

			DrawPanel.shapes.clear();
			DrawPanel.tempShapes.clear();
			DrawPanel.copyShapes.clear();
			
			DrawPanel.shapes = (ArrayList<Shape>) input.readObject();
			DrawPanel.tempShapes = (ArrayList<Shape>) input.readObject();
			DrawPanel.copyShapes = (ArrayList<Shape>) input.readObject();
			
			AxisPanel.setText("읽어오기 성공!");
			
		} catch (EOFException endOfFileException) {
			JOptionPane.showMessageDialog(null, "파일을 읽어오는 중 오류가 발생했습니다.", "EOFException", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException classNotFoundException) {
			JOptionPane.showMessageDialog(null, "오브젝트를 생성할 수 없습니다.", "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "파일을 읽어오는 중 오류가 발생했습니다.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void closeCreateFile() {
		try {
			if (output != null)
				output.close();
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "파일을 닫는 중 오류가 발생했습니다.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void closeReadFile() {
		try {
			if (input != null)
				input.close();
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "파일을 닫는 중 오류가 발생했습니다.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
	}

}
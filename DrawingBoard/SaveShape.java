import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * �̸� : SaveShape
 * ���� : ��������� �۾����� �����ϴ� ����� ����ϴ� Ŭ�����̴�.
 *     : DrawPanel�� Object ������ ��� ArrayList�� Serialize�Ͽ� ���Ͽ� �����Ѵ�.
 */

public class SaveShape {

	private static ObjectOutputStream output;
	private static ObjectInputStream input;

	public static void makeFile() {
		try {
			output = new ObjectOutputStream(new FileOutputStream(EditorFrame.currentPath));
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "���� ���� �� ������ �߻��߽��ϴ�.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void openFile() {

		try {
			input = new ObjectInputStream(new FileInputStream(EditorFrame.currentPath));
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "������ �о���� �� ������ �߻��߽��ϴ�.", "IOException", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void addRecords() {

		try {
			output.writeObject(DrawPanel.shapes);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "������ ����ϴ� �� ������ �߻��߽��ϴ�.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
		try {
			output.writeObject(DrawPanel.tempShapes);
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "������ ����ϴ� �� ������ �߻��߽��ϴ�.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
		try {
			output.writeObject(DrawPanel.copyShapes);
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "������ ����ϴ� �� ������ �߻��߽��ϴ�.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
		
		AxisPanel.setText("���� ����!");
		
	}

	public static void readRecords() {

		try {

			DrawPanel.shapes.clear();
			DrawPanel.tempShapes.clear();
			DrawPanel.copyShapes.clear();
			
			DrawPanel.shapes = (ArrayList<Shape>) input.readObject();
			DrawPanel.tempShapes = (ArrayList<Shape>) input.readObject();
			DrawPanel.copyShapes = (ArrayList<Shape>) input.readObject();
			
			AxisPanel.setText("�о���� ����!");
			
		} catch (EOFException endOfFileException) {
			JOptionPane.showMessageDialog(null, "������ �о���� �� ������ �߻��߽��ϴ�.", "EOFException", JOptionPane.ERROR_MESSAGE);
		} catch (ClassNotFoundException classNotFoundException) {
			JOptionPane.showMessageDialog(null, "������Ʈ�� ������ �� �����ϴ�.", "ClassNotFoundException", JOptionPane.ERROR_MESSAGE);
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "������ �о���� �� ������ �߻��߽��ϴ�.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void closeCreateFile() {
		try {
			if (output != null)
				output.close();
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "������ �ݴ� �� ������ �߻��߽��ϴ�.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void closeReadFile() {
		try {
			if (input != null)
				input.close();
		} catch (IOException ioException) {
			JOptionPane.showMessageDialog(null, "������ �ݴ� �� ������ �߻��߽��ϴ�.", "IOException", JOptionPane.ERROR_MESSAGE);
		}
	}

}
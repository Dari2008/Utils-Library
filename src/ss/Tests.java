package ss;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import javad.utils.ki.neuronalNetwork.Network;
import javad.utils.ki.neuronalNetwork.Util;

public class Tests extends JFrame{
	private JTable table;
	public Tests() {
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 586, 465);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "Copy"
			}
		));
		scrollPane.setViewportView(table);
		
		String s = null;
		
		try {
			Scanner sc = new Scanner(new URL("").openStream());
			while(sc.hasNextLine()) {
				s = s + sc.nextLine() + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String[] lines = s.split("\n");
		
		Network<Boolean> ss = new Network<>(new int[] {12, 100, 30, 1},  0.3d, Util::derivativeSigmoid, Util::sigmoid);
		List<double[]> inputs = new ArrayList<>();
		List<double[]> expected = new ArrayList<>();

		inputs.add(getDoubleArrayFromCode("LA9C3RHPPHQH"));expected.add(new double[] {1});
		inputs.add(getDoubleArrayFromCode("SSRCJ8HSV7UM"));expected.add(new double[] {1});
		inputs.add(getDoubleArrayFromCode("SWCQSSDDD"));expected.add(new double[] {1});
		inputs.add(getDoubleArrayFromCode("HalloWieGehtE"));expected.add(new double[] {0});
		inputs.add(getDoubleArrayFromCode("LALALALALALAL"));expected.add(new double[] {0});
		inputs.add(getDoubleArrayFromCode("WEWEWEWEWEWEWQE"));expected.add(new double[] {0});
		
		for(int i = 0; i < 1000; i++) {
			ss.train(inputs, expected);
		}
		
	}
	
	public double[] getDoubleArrayFromCode(String s) {
		if(s.length() != 12)return new double[] {};
		double[] d = new double[12];
		for(int i = 0; i < 12; i++) {
			d[i] = 0;
		}
		for(int i = 0; i < 12; i++) {
			d[i] = getDoubleForChar(s.charAt(i));
		}
		return d;
	}
	
	public double getDoubleForChar(char c) {
		double d = 2.7d;
		switch(Character.toUpperCase(c)) {
		case 'A':
			return 0;
		case 'B':
			return 0+d*1;
		case 'C':
			return 0+d*2;
		case 'D':
			return 0+d*3;
		case 'E':
			return 0+d*4;
		case 'F':
			return 0+d*5;
		case 'G':
			return 0+d*6;
		case 'H':
			return 0+d*7;
		case 'I':
			return 0+d*8;
		case 'J':
			return 0+d*9;
		case 'K':
			return 0+d*10;
		case 'L':
			return 0+d*11;
		case 'M':
			return 0+d*12;
		case 'N':
			return 0+d*13;
		case 'O':
			return 0+d*14;
		case 'P':
			return 0+d*15;
		case 'Q':
			return 0+d*16;
		case 'R':
			return 0+d*17;
		case 'S':
			return 0+d*18;
		case 'T':
			return 0+d*19;
		case 'U':
			return 0+d*20;
		case 'V':
			return 0+d*21;
		case 'W':
			return 0+d*22;
		case 'X':
			return 0+d*23;
		case 'Y':
			return 0+d*24;
		case 'Z':
			return 0+d*25;
		case '1':
			return 0+d*26;
		case '2':
			return 0+d*27;
		case '3':
			return 0+d*28;
		case '4':
			return 0+d*29;
		case '5':
			return 0+d*30;
		case '6':
			return 0+d*31;
		case '7':
			return 0+d*32;
		case '8':
			return 0+d*33;
		case '9':
			return 0+d*34;
		case '0':
			return 0+d*35;
			default:return 0;
		}
	}
	
}

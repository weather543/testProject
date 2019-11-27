package menu;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import menu.MenuDAO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuSave extends JFrame {

	private JPanel contentPane;
	private JTextField tfNum;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tfPersomNo;
	
	private MenuList frm;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MenuSave frame = new MenuSave();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MenuSave(MenuList frm) {
		this();
		this.frm = frm;
	}
	
	
	public MenuSave() {
		//창닫기(현재창만 닫기)
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 252, 352);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("번호");
		lblNewLabel.setBounds(14, 12, 62, 18);
		contentPane.add(lblNewLabel);
		
		tfNum = new JTextField();
		tfNum.setBounds(90, 9, 116, 24);
		contentPane.add(tfNum);
		tfNum.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("메뉴이름");
		lblNewLabel_1.setBounds(14, 55, 62, 18);
		contentPane.add(lblNewLabel_1);
		
		tfName = new JTextField();
		tfName.setBounds(90, 52, 116, 24);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("가격");
		lblNewLabel_2.setBounds(14, 103, 62, 18);
		contentPane.add(lblNewLabel_2);
		
		tfPrice= new JTextField();
		tfPrice.setBounds(90, 100, 116, 24);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("몇인분");
		lblNewLabel_3.setBounds(14, 154, 62, 18);
		contentPane.add(lblNewLabel_3);
		
		tfPersomNo = new JTextField();
		tfPersomNo.setBounds(90, 151, 116, 24);
		contentPane.add(tfPersomNo);
		tfPersomNo.setColumns(10);
		
		
		JButton btnSave = new JButton("저장");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//사용자가 입력한 값
				String num=tfNum.getText();
				String name=tfName.getText();
				int price=Integer.parseInt(tfPrice.getText());
				String personNo=tfPersomNo.getText();
				MenuDTO dto=new MenuDTO(num, name, price, personNo);
				MenuDAO dao=new MenuDAO();
				int result=dao.insertMenu(dto);
				if(result==1) {//insert가 성공하면
					JOptionPane.showMessageDialog(
							MenuSave.this, "저장되었습니다.");
					//현재 프레임을 닫기 전에 ScoreList의 refreshTable()호출
					frm.refreshTable();
					//현재 프레임을 닫음
					dispose();
				}
			}
		});
		btnSave.setBounds(68, 228, 105, 27);
		contentPane.add(btnSave);
	}		
}

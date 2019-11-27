package menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuEdit extends JFrame {

	private JPanel contentPane;
	private JTextField tfNum;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tfPersonNo;
	//변수 추가
	private MenuList parent;
	//화면 갱신용
	private MenuDAO dao;
	private MenuDTO dto;
	private String num;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MenuEdit frame = new MenuEdit(null, num);
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
	public MenuEdit(MenuList parent, String num) {
		this.parent = parent;
		this.num = num;
		System.out.println(parent);
		System.out.println(dto);
		
		setTitle("메뉴 편집");
		//현재 창만 닫는 옵션으로 변경
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 271, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("학       번");
		lblNewLabel.setBounds(14, 12, 62, 18);
		contentPane.add(lblNewLabel);
		
		tfNum = new JTextField();
		tfNum.setBounds(90, 9, 116, 24);
		contentPane.add(tfNum);
		tfNum.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("메뉴 이름");
		lblNewLabel_1.setBounds(14, 55, 62, 18);
		contentPane.add(lblNewLabel_1);
		
		tfName = new JTextField();
		tfName.setBounds(90, 52, 116, 24);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("가        격");
		lblNewLabel_2.setBounds(14, 106, 62, 18);
		contentPane.add(lblNewLabel_2);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(90, 103, 116, 24);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("몇     인분");
		lblNewLabel_3.setBounds(14, 159, 62, 18);
		contentPane.add(lblNewLabel_3);
		
		tfPersonNo = new JTextField();
		tfPersonNo.setBounds(90, 156, 116, 24);
		contentPane.add(tfPersonNo);
		tfPersonNo.setColumns(10);
		
		//dto값을 텍스트필드에 출력
	    dao=new MenuDAO();
		dto=dao.viewMenu(num);
		tfNum.setText(dto.getNum());
		tfName.setText(dto.getName());
		tfPrice.setText(dto.getPrice()+"");//int 점수를 ""로 String화함
		tfPersonNo.setText(dto.getPersonNo());
		
	    JButton btnUpdate = new JButton("수정");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num=tfNum.getText();
				String name=tfName.getText();
				int price=Integer.parseInt(tfPrice.getText());
				String personNo=tfPersonNo.getText();
				MenuDTO dto= new MenuDTO(num, name, price, personNo);
				int result=dao.updateMenu(dto);
				if(result==1) {
					JOptionPane.showMessageDialog(
							MenuEdit.this, "수정되었습니다.");
					parent.refreshTable();//ScoreList 갱신
					dispose();//현재 프레임을 닫는 Window 메소드
				}
			}
		});
		btnUpdate.setBounds(70, 233, 105, 27);
		contentPane.add(btnUpdate);
	}
}

//	public MenuEdit1(MenuList parent2, String num2) {
		// TODO Auto-generated constructor stub

		
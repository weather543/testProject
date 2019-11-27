package menu;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import menu.MenuList;

public class MenuList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField tfNum;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tfPersonNo;
	private JTextField tfSearch;
	private JButton btnSave;
	private JButton btnUpdate;
	private JButton btnDelete;
	private JButton btnSearch;	
	
	private MenuDAO dao;
	private MenuDTO dto;
	private Vector<String> col;
	private DefaultTableModel model;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuList frame = new MenuList();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public MenuList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 503, 572);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 232, 454, 251);
		contentPane.add(scrollPane);
		
	
		dao=new MenuDAO();//dao 인스턴스 생성
		//제목열 구성
		col=new Vector<String>();
		col.add("번호");
		col.add("메뉴이름");
		col.add("가격");
		col.add("몇인분");
//		JTable에 데이터를 출력시키기 위한 테이블 모델 생성
		list();
		
		table = new JTable(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//현재 클릭한 행의 인덱스 값
				int idx=table.getSelectedRow();
				tfNum.setEditable(false);
				tfNum.setText(table.getValueAt(idx, 0)+"");
				tfName.setText(table.getValueAt(idx, 1)+"");
				tfPrice.setText(table.getValueAt(idx, 2)+"");
				tfPersonNo.setText(table.getValueAt(idx, 3)+"");
			}
		});
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("번       호");
		lblNewLabel.setBounds(14, 39, 52, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("메뉴  이름");
		lblNewLabel_1.setBounds(14, 78, 76, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("가        격");
		lblNewLabel_2.setBounds(14, 114, 56, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("몇     인분");
		lblNewLabel_3.setBounds(14, 151, 56, 15);
		contentPane.add(lblNewLabel_3);
		
		
		JLabel lblNewLabel_5 = new JLabel("번호를 입력하세요");
		lblNewLabel_5.setBounds(14, 196, 140, 15);
		contentPane.add(lblNewLabel_5);
		
		tfNum = new JTextField();
		tfNum.setBounds(141, 36, 116, 21);
		contentPane.add(tfNum);
		tfNum.setColumns(10);
		
		tfName = new JTextField();
		tfName.setBounds(141, 75, 116, 21);
		contentPane.add(tfName);
		tfName.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(141, 111, 116, 21);
		contentPane.add(tfPrice);
		tfPrice.setColumns(10);
		
		tfPersonNo = new JTextField();
		tfPersonNo.setBounds(141, 148, 116, 21);
		contentPane.add(tfPersonNo);
		tfPersonNo.setColumns(10);
		
		JButton btnList = new JButton("목록보기");
		btnList.setFont(new Font("굴림", Font.PLAIN, 15));
		btnList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				list();
				table.setModel(model);
				clear();
			}
		});
		btnList.setBounds(177, 500, 132, 24);
		contentPane.add(btnList);
		
		tfSearch = new JTextField();
		tfSearch.addKeyListener(new KeyAdapter() {
		 @Override
		 public void keyReleased(KeyEvent e) {
			 search();
		 }
		});
		tfSearch.setBounds(141, 193, 116, 21);
		contentPane.add(tfSearch);
		tfSearch.setColumns(10);
	
	    JPanel panel = new JPanel();
	    panel.setBounds(14, 491, 454, 33);
	    contentPane.add(panel);
		
		JButton btnEdit = new JButton("삭제");
		btnEdit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String num=tfNum.getText();
			int result=0;
			int response=JOptionPane.showConfirmDialog(
					MenuList.this, "삭제하시겠습니까?");
		//JTable에서 선택한 행의 인덱스 값
			if(response==JOptionPane.YES_OPTION) {
				result=dao.deleteMenu(num);//삭제처리
			}
			if(result==1) {//삭제된 레코드의 갯수가 1이면
				JOptionPane.showMessageDialog(
						MenuList.this, "삭제되었습니다.");
				list();//테이블 갱신
				table.setModel(model);
				clear();//텍스트필드 초기화
			}
		  }
		});
		btnEdit.setBounds(379, 190, 89, 21);
		contentPane.add(btnEdit);					
		
		JButton btnAdd = new JButton("추가");
		btnAdd.setBounds(379, 106, 89, 23);
		contentPane.add(btnAdd);
		
	    JButton btnNewButton = new JButton("수정");
	    btnNewButton.setBounds(379, 147, 89, 23);
	    contentPane.add(btnNewButton);
	    
	    JButton btnPrint = new JButton("출력");
	    btnPrint.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		MenuPrint frm=new MenuPrint(MenuList.this);
				frm.setVisible(true);
				frm.setLocation(200, 200);
	    	}
	    });
	    btnPrint.setFont(new Font("굴림", Font.PLAIN, 15));
	    btnPrint.setBounds(392, 10, 76, 33);
	    contentPane.add(btnPrint);
	    
	    
	    
	    btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//JTable에서 선택한 행의 인덱스 값
				int idx=table.getSelectedRow();//0번째 행~
				System.out.println(idx);
				if(idx == -1) {//선택한 행이 없으면
					JOptionPane.showMessageDialog(
							MenuList.this, "편집할 자료를 선택하세요.");
					return; //메소드 종료
				}else {//선택한 행이 있으면
					String num=
							String.valueOf(table.getValueAt(idx, 0));
					MenuEdit frm=new MenuEdit(MenuList.this,num);
					frm.setVisible(true);
					frm.setLocation(550, 100);
				}
			}
		});
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuSave frm=new MenuSave(MenuList.this);
				frm.setVisible(true);
				frm.setLocation(200, 200);
			}
		});
	}
	
	public void search() {
		String num=tfSearch.getText();
		model=new DefaultTableModel(dao.searchMenu(num),col) {
			public boolean isCellEditable(int ro, int column) {
				return false;
			}
		};
		table.setModel(model);
	}//search()

	public void list() {
		model=new DefaultTableModel(dao.listMenu(), col) {
		@Override
		public boolean isCellEditable(int ro, int column) {
			return false;
		  }
	    };//list()
     }

	public void refreshTable() {
	DefaultTableModel model=
			new DefaultTableModel(dao.listMenu(), col);
	//테이블에 데이터 채워짐
	table.setModel(model);
	}//refreshTable()

	public void input() {
		String num = tfNum.getText();
		String name = tfName.getText();
		int price = Integer.parseInt(tfPrice.getText());
		String personNo = tfPersonNo.getText();
	}
	public void clear() {
		tfNum.setText("");
		tfName.setText("");
		tfPrice.setText("");
		tfPersonNo.setText("");
		tfNum.requestFocus();//커서를 옮김
		tfNum.setEditable(true);
		//수정이 끝나면 학번 입력을 활성화시킴
	}
}//class


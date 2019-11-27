package menu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import menu.MenuDAO;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class MenuPrint extends JFrame {

	private JTextField tfNum;
	private JTextField tfName;
	private JTextField tfPrice;
	private JTextField tfPersonNo;
	private JPanel contentPane;
	private JTable table;
	
	private MenuList frm;
	private MenuDAO dao;
	private MenuDTO dto;
	private Vector<String> col;
	private DefaultTableModel model;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MenuPrint frame = new MenuPrint();
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
	public MenuPrint(MenuList frm) {
		this();//ScoreSave의 화면을 보이기 위해 처리
		//ScoreSave() 기본생성자 호출
		this.frm = frm;
	}
	
	public MenuPrint() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 364);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
        contentPane.setLayout(null);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 459, 283);
		contentPane.add(scrollPane);
		
		dao=new MenuDAO();//dao 인스턴스 생성
		//제목열 구성
		col=new Vector<String>();
		col.add("번호");
		col.add("메뉴이름");
		col.add("가격");
		col.add("몇인분");
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
        }

     public void list() {
	    model=new DefaultTableModel(dao.listMenu(), col) {
	    @Override
	    public boolean isCellEditable(int ro, int column) {
		    return false;
	       }
         };//list()
      }
      public void clear() {
	    tfNum.setText("");
	    tfName.setText("");
	    tfPrice.setText("");
	    tfPersonNo.setText("");
	    tfNum.requestFocus();//커서를 옮김
	    tfNum.setEditable(true);
      }
}



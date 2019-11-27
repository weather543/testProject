package menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import menu.DB;

public class MenuDAO {
	public Vector listMenu() {
	Vector ve = new Vector();
	Connection co=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	try {
		co=DB.dbConn();
		StringBuilder sb = new StringBuilder();
		sb.append("select num,name,price,personNo from menu order by num");
		ps = co.prepareStatement(sb.toString());
		rs=ps.executeQuery();
		while (rs.next()) {
			Vector ro =new Vector();
			String num =rs.getString("num");
			String name=rs.getString("name");
			int price=rs.getInt("price");
			String personNo=rs.getString("personNo");
			ro.add(num);
			ro.add(name);
			ro.add(price);
			ro.add(personNo);
			ve.add(ro);	
			}
		}catch(Exception e) {
			e.printStackTrace();	
	    }finally {
	    	try {
	    		if(rs!=null)rs.close();
	    	}catch(Exception e2) {
	    		e2.printStackTrace();
	    	}
	    	try {
				if(ps!=null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	    	try {
				if(co!=null) co.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
	    }
	    return ve;
	}//listMenu()

public int insertMenu(MenuDTO dto) {
	int result=0;
	Connection co=null;
	PreparedStatement ps=null;
	try {
		co=DB.dbConn();
		StringBuilder sb = new StringBuilder();
		sb.append("insert into menu (num,name,price,personNo) values (?,?,?,?)");
		ps=co.prepareStatement(sb.toString());
		ps.setString(1, dto.getNum());
		ps.setString(2, dto.getName());
		ps.setInt(3, dto.getPrice());
		ps.setString(4, dto.getPersonNo());
		result = ps.executeUpdate();
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(ps!=null) ps.close();
		}catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			if(co!=null) co.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
		return result;
}//insert()
	
public int updateMenu(MenuDTO dto) {
		int result=0;
		Connection co=null;
		PreparedStatement ps=null;
		try {
			co=DB.dbConn();
			StringBuilder sb=new StringBuilder();
			sb.append("update menu set name=?,price=?,personNo=? where num=?");
			ps = co.prepareStatement(sb.toString());
			ps.setString(1, dto.getName());
			ps.setInt(2, dto.getPrice());
			ps.setString(3, dto.getPersonNo());
			ps.setString(4, dto.getNum());
			result = ps.executeUpdate();	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps!=null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(co!=null) co.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
			return result;
}//update()
	
public int deleteMenu(String num) {
		int result=0;
		Connection co=null;
		PreparedStatement ps=null;
		try {
			co=DB.dbConn();
			StringBuilder sb=new StringBuilder();
			sb.append("delete from menu where num=?");
			ps=co.prepareStatement(sb.toString());
			ps.setString(1, num);
			result=ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(ps!=null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(co!=null) co.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return result;
	}//deleteScore()	
	
public Vector searchMenu(String num) {
		Vector ve=new Vector();
		Connection co=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			co=DB.dbConn();
			StringBuilder sb=new StringBuilder();
			sb.append("select num,name,price,personNo "
					+ " from menu where num like ?  order by num");
			System.out.println(sb.toString());
			ps = co.prepareStatement(sb.toString());	
			ps.setString(1, "%"+num+"%");
			rs = ps.executeQuery();
			while(rs.next()) {//다음 레코드가 있으면 true
				Vector row=new Vector();
				row.add(rs.getString("num"));
				row.add(rs.getString("name"));
				row.add(rs.getInt("price"));
				row.add(rs.getString("personNo"));
				ve.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(ps!=null) ps.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(co!=null) co.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
		return ve;//백터 리턴
	}//searchScore()	

public MenuDTO viewMenu(String num) {
	MenuDTO dto=null;
	Connection co=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	try {
		co=DB.dbConn();
		StringBuilder sb=new StringBuilder();
		sb.append("select num,name,price,personNo ");
		sb.append(" from menu where num like ?  order by num");			
		ps=co.prepareStatement(sb.toString());
		ps.setString(1, num);
		rs = ps.executeQuery();
		if(rs.next()) {
			String name = rs.getString("name");
			int price=rs.getInt("price");
			String personNo = rs.getString("personNo");
			dto=new MenuDTO(num, name, price, personNo);
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if(rs!=null) rs.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			if(ps!=null) ps.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		try {
			if(co!=null) co.close();
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}//finally
	return dto;
}//viewScore()
}


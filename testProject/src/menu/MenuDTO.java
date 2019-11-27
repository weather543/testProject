package menu;

public class MenuDTO {
	String num, name;
	int  price;
	String personNo;
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPersonNo() {
		return personNo;
	}
	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}
	@Override
	public String toString() {
		return "MenuDTO [num=" + num + ", name=" + name + ", price=" + price + ", personNo=" + personNo + "]";
	}
	public MenuDTO(String num, String name, int price, String personNo) {
		this.num = num;
		this.name = name;
		this.price = price;
		this.personNo = personNo;
	}
	
	public MenuDTO() {
	}	
}//class	

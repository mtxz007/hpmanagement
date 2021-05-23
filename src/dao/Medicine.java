package dao;

public class Medicine {
	private int id;					//药品号
	private String name;			//药品名称
	private String menufacturer;	//厂家
	private String pd;				//生产日期
	private String exp;				//过期日期
	private String batch_number;	//批次号
	private int number;				//数量
	private double price_stock;		//进价
	private double price_sale;		//销售价
	private String kind;			//药品种类
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMenufacturer() {
		return menufacturer;
	}
	public void setMenufacturer(String menufacturer) {
		this.menufacturer = menufacturer;
	}
	public String getPd() {
		return pd;
	}
	public void setPd(String pd) {
		this.pd = pd;
	}
	public String getExp() {
		return exp;
	}
	public void setExp(String exp) {
		this.exp = exp;
	}
	public String getBatch_number() {
		return batch_number;
	}
	public void setBatch_number(String batch_number) {
		this.batch_number = batch_number;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public double getPrice_stock() {
		return price_stock;
	}
	public void setPrice_stock(double price_stock) {
		this.price_stock = price_stock;
	}
	public double getPrice_sale() {
		return price_sale;
	}
	public void setPrice_sale(double price_sale) {
		this.price_sale = price_sale;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	
}

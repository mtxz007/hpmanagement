package utils;

import java.util.ArrayList;
import java.util.HashMap;

public class GetAll {
	static DbUtils db = new DbUtils();
	public static Page get(int currentPage,String kinds, String update){
		if(kinds == null || kinds.equals("")){
			kinds = "u.username";
		}
		if(update == null || update.equals("")){
			update = "%%";
		}else{
			update = "%"+update+"%";
		}
		if(kinds.equals("用户名")){
			kinds = "u.username";
		}
		if(kinds.equals("姓名")){
			kinds = "u.name";
		}
		if(kinds.equals("性别")){
			kinds = "u.sexual";
		}
		if(kinds.equals("科室")){
			kinds = "d.name";
		}
		if(kinds.equals("权限")){
			kinds = "r.name";
		}
		if(kinds.equals("状态")){
			kinds = "s.name";
		}
		int count = Integer.parseInt(db.queryForList("select count(*) as sum from user").get(0).get("sum").toString());
		int pageCount = count/19 + (count%19 == 0 ? 0 : 1);
		ArrayList<HashMap<String,Object>> list = db.queryForList("SELECT u.`username`,u.`name`,u.`sexual`,u.`age`,u.`mailbox`,u.`phone_number`,u.`address`,d.`name` AS department_name,r.`name` AS root_name,s.`name` AS state_name "
				+"FROM USER u, department d, root r, state s WHERE u.`department` = d.`id` AND u.`root` = r.`id` AND u.`state` = s.`id` AND "+kinds+" like '"+update+"'"
				+"ORDER BY u.name LIMIT "+(currentPage-1)*19+",19");
		Page table = new Page();
		table.setCount(count);
		table.setPageCount(pageCount);
		table.setList(list);
		return table;
	}
	
	public static Page getDepartment(String str){
		Page page = new Page();
		ArrayList<HashMap<String, Object>> department = db.queryForList("select * from department where name like '%"+str+"%'");
		page.setList(department);
		return page;
	}
	
	public static Page getRegister(int currentPage, String name){
		if(name==null || name.equals("")){
			name = "%%";
		}else{
			name = "%"+name+"%";
		}
		int count = Integer.parseInt(db.queryForList("select count(*) as sum from register").get(0).get("sum").toString());
		int pageCount = count/10 + (count%10 == 0 ? 0 : 1);
		ArrayList<HashMap<String, Object>> register = db.queryForList("SELECT r.patient_id, r.name, k.name kinds_name, r.price, r.time, d.name department_name, r.operator, s.name state_name, r.result " +
																		"FROM register r, kinds k, register_state s, department d "
																		+ "WHERE r.`kinds`=k.`id` AND r.`state` = s.`id` AND r.department = d.id AND r.name LIKE '"+name+"' ORDER BY r.`patient_id` LIMIT "+(currentPage-1)*10+",10");
		Page page = new Page();
		page.setCount(count);
		page.setPageCount(pageCount);
		page.setList(register);
		page.setCurrentPage(currentPage);
		page.setPageSize(10);
		return page;
	}
	
	public static Page getMedicine(int currentPage, String name ,String kinds, String query, String orderName, boolean orderValue){
		String order;
		if(name==null||name.equals("")){
			name = "%%";
		}else{
			name = "%"+name+"%";
		}
		if(kinds==null||kinds.equals("")){
			kinds = "st.name";
		}else if(kinds.equals("名称")){
			kinds = "st.name";
		}else if(kinds.equals("批次号")){
			kinds = "st.batch_number";
		}else if(kinds.equals("制药厂")){
			kinds = "m.name";
		}else if(kinds.equals("药品种类")){
			kinds = "k.name";
		}
		if(orderName==null||orderName.equals("")){
			orderName = "id";
		}
		if(query==null||query.equals("")){
			query = "%%";
		}
		if(orderValue){
			order = "ASC";
		}else{
			order = "DESC";
		}
		int count = Integer.parseInt(db.queryForList("select count(*) as sum from medicine_stock").get(0).get("sum").toString());
		int pageCount = count/19 + (count%19 == 0 ? 0 : 1);
		ArrayList<HashMap<String,Object>> list = db.queryForList("SELECT distinct st.`id`,st.`name`, m.`name` AS manufacturer, st.`batch_number`,st.`pd`, st.`exp`, st.`number`, st.`price_stock`,st.`price_sale`, k.`NAME` AS kinds_name , sm.`name` AS kinds_state_name"
				+" FROM manufacturer m, medicine_stock st, medicine_state s, medicine_kinds k, medicine_state sm WHERE st.`kind` = k.`id` AND k.`state`=s.`id` AND m.`id` = st.`manufacturer`"
				+" AND "+kinds+" LIKE '"+query+"' "
				+" GROUP BY id"
				+" ORDER BY "+orderName+" "+order+" LIMIT "+(currentPage-1)*19+",19");
		Page table = new Page();
		table.setCount(count);
		table.setPageCount(pageCount);
		table.setList(list);
		return table;
	}
	
	public static Page getManufacturer(int currentPage, String name, boolean orderValue){
		String order;
		if(orderValue){
			order = "ASC";
		}else{
			order = "DESC";
		}
		if(name==null){
			name = "";
		}
		int count = Integer.parseInt(db.queryForList("select count(*) as sum from manufacturer").get(0).get("sum").toString());
		int pageCount = count/10 + (count%10 == 0 ? 0 : 1);
		ArrayList<HashMap<String, Object>> register = db.queryForList("SELECT * FROM manufacturer WHERE NAME LIKE '%"+name+"%' "
																	+" GROUP BY id "
																	+" ORDER BY id "+order+" LIMIT "+(currentPage-1)*10+",10");
		Page page = new Page();
		page.setCount(count);
		page.setPageCount(pageCount);
		page.setList(register);
		page.setCurrentPage(currentPage);
		page.setPageSize(10);
		return page;
	}
}

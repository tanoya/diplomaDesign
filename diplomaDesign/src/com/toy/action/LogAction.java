package com.toy.action;


import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.toy.service.LogService;
import com.opensymphony.xwork2.ActionSupport;

public class LogAction extends ActionSupport{
	private String name;
	private String password;
	
	// getter && setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String execute() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest(); //�õ�http�е�request�е���Ϣ
		String radio = request.getParameter("radio"); //��ȡѡ��������
		System.out.println(radio);
		
		if("admin".equals(radio)){ //���ع���Ա�ĵĵ�¼�ַ���
			return adminLog();
		}else if("emp".equals(radio)){ //������ͨԱ���ĵ�¼�ַ���
			return userLog();			
		}else if("deptManager".equals(radio)){ //���ز��ž����� ��¼�ַ���
			return deptManagerLog();
		}else if("manager".equals(radio)){
			return managerLog();
		}
		return "error";
				
	}
	
	//�û���¼�Ĵ�������
	public String userLog(){
		LogService ls = new LogService();
		if(ls.userLogService(name, password)){
			return "employee"; //��ת����ͨԱ������
		}
		return "error";
	}
	
	//����Ա��¼�Ĵ�������
	public String adminLog(){
		LogService ls = new LogService();
//		System.out.println(name + " " + password);
		if(ls.adminLogService(name, password)){
			return "admin";	//��ת������Ա�Ľ���
		}
		return "error";
	}
	
	//���ž�����¼�Ĵ�������
	public String deptManagerLog(){
		LogService ls = new LogService();
		if(ls.deptManagerLogService(name, password)){
			return "deptManager"; //��ת�����ž����Ľ���
		}
		return "error";
	}
	
	//�ܾ�����¼�Ĵ�������
	public String managerLog(){
		LogService ls = new LogService();
		if(ls.managerLogService(name, password)){
			return "manager"; //��ת���ܾ����ĵ�¼����	
		}
		return "error";
	}
}
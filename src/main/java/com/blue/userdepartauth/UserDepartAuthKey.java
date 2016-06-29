package com.blue.userdepartauth;

public class UserDepartAuthKey {
	private String departId;
	private String userId;
	public UserDepartAuthKey(String departId,String userId) {
		this.departId = departId;
		this.userId = userId;
	}
	
	public UserDepartAuthKey(UserDepartAuth auth){
		this.departId = auth.getDepartId();
		this.userId = auth.getUserId();
	}
	public String getDepartId() {
		return departId;
	}
	public String getUserId() {
		return userId;
	}
	public String getKey(){
		return new StringBuffer(departId+"-"+userId).toString();
	}
	public UserDepartAuthKey toKey(String paramId){
		String[] t = paramId.split("-");
		return new UserDepartAuthKey(t[0], t[1]);
	}
}

package com.blue.roledepartauth;

public class RoleDepartAuthKey {
	private String departId;
	private String roleId;
	public RoleDepartAuthKey(String departId,String roleId) {
		this.departId = departId;
		this.roleId = roleId;
	}
	
	public RoleDepartAuthKey(RoleDepartAuth auth){
		this.departId = auth.getDepartId();
		this.roleId = auth.getRoleId();
	}
	public String getDepartId() {
		return departId;
	}
	public String getRoleId() {
		return roleId;
	}
	public String getKey(){
		return new StringBuffer(departId+"-"+roleId).toString();
	}
	public RoleDepartAuthKey toKey(String paramId){
		String[] t = paramId.split("-");
		return new RoleDepartAuthKey(t[0], t[1]);
	}
}

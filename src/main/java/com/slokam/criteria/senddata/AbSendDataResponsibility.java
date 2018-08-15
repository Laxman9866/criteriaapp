package com.slokam.criteria.senddata;

public abstract class AbSendDataResponsibility {

	private AbSendDataResponsibility abSendDataResponsibility;
	
	public abstract void prepairResponsibilities(SendDataResponsibilityPojo pojo);	
	public void nextResponsibility(SendDataResponsibilityPojo pojo){
		if(this.abSendDataResponsibility!=null){
			abSendDataResponsibility.prepairResponsibilities(pojo);
		}
	}

	public void setAbSendDataResponsibility(AbSendDataResponsibility abSendDataResponsibility) {
		this.abSendDataResponsibility = abSendDataResponsibility;
	}
	

}

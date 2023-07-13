package test.spring.component.park;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BeachDTO {
	@JsonProperty("������")
    private String openDate;
	@JsonProperty("�õ�1")
    private String sido1;
	@JsonProperty("�õ�2")
	private String sido2;
	@JsonProperty("�ּ�")
    private String address;
	@JsonProperty("������")
    private String closingDate;
	@JsonProperty("�ؼ������")
    private String beachName;
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getSido1() {
		return sido1;
	}
	public void setSido1(String sido1) {
		this.sido1 = sido1;
	}
	public String getSido2() {
		return sido2;
	}
	public void setSido2(String sido2) {
		this.sido2 = sido2;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(String closingDate) {
		this.closingDate = closingDate;
	}
	public String getBeachName() {
		return beachName;
	}
	public void setBeachName(String beachName) {
		this.beachName = beachName;
	}
    
}


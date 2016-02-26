package ttc.bean;

public class PolicyBean implements Bean{
    private String policyId;
    private String policyDate;
    private String policyBody;

	/**
	* Returns value of policyId
	* @return
	*/
	public String getPolicyId() {
		return policyId;
	}

	/**
	* Sets new value of policyId
	* @param
	*/
	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	/**
	* Returns value of policyDate
	* @return
	*/
	public String getPolicyDate() {
		return policyDate;
	}

	/**
	* Sets new value of policyDate
	* @param
	*/
	public void setPolicyDate(String policyDate) {
		this.policyDate = policyDate;
	}

	/**
	* Returns value of policyBody
	* @return
	*/
	public String getPolicyBody() {
		return policyBody;
	}

	/**
	* Sets new value of policyBody
	* @param
	*/
	public void setPolicyBody(String policyBody) {
		this.policyBody = policyBody;
	}
}

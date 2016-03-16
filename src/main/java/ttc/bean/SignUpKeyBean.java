package ttc.bean;

public class SignUpKeyBean implements Bean{
    private String signUpKey;
    private String keyValidDate;


    /**
    * Returns value of signUpKey
    * @return
    */
    public String getSignUpKey() {
    	return signUpKey;
    }

    /**
    * Sets new value of signUpKey
    * @param
    */
    public void setSignUpKey(String signUpKey) {
    	this.signUpKey = signUpKey;
    }

	/**
	 * @return the keyValidDate
	 */
	public String getKeyValidDate() {
		return keyValidDate;
	}

	/**
	 * @param keyValidDate the keyValidDate to set
	 */
	public void setKeyValidDate(String keyValidDate) {
		this.keyValidDate = keyValidDate;
	}

    
}

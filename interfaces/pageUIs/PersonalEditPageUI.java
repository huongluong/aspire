package pageUIs;

public class PersonalEditPageUI {
	public static final String BIRTHDAY_TEXTBOX             = "//div[contains(text(),'Date of Birth')]/parent::div/following-sibling::label//input";
	//public static final String NATIONALITY_LABEL            = "//div[contains(text(),'Nationality')]/parent::div/following-sibling::label";
	public static final String NATIONALITY_LABEL            = "//div[@placeholder='Select any of the following']";
	public static final String NATIONALITY_DROPDOWN_ICON    =  "//div[contains(text(),'Nationality')]/parent::div/following-sibling::label//i[@role='presentation']";
	//public static final String NATIONALITY_LISTITEM         = "//div[@class='q-virtual-scroll__content']//div/div";
	public static final String NATIONALITY_LISTITEM         = "//div[@class='q-menu q-position-engine scroll q-menu--square']//div/div[@class='q-item__label']";
	//public static final String NATIONALITY_ITEM             = "//div[@class='q-virtual-scroll__content']//div//div[text()='%s']"; //%s= Viet Nam or New Caledonia 
	//public static final String GENDER                      = "//div[contains(text(),'Gender')]/parent::div/following-sibling::label";
	public static final String GENDER_DROPDOWN_ICON    =  "//div[contains(text(),'Gender')]/parent::div/following-sibling::label//i[@role='presentation']";
	
	public static final String GENDER_LISTITEM              = "//div[@class='q-virtual-scroll__content']//div/div[@class='q-item__label']";
	//public static final String GENDER_ITEM                  = "//div[@class='q-virtual-scroll__content']//div//div[text()='%s']"; //%s= Male or Female 
	
    public static final String INTERESTED_IN_DROPDOWN_ICON    =  "//div[contains(text(),'Which')]/parent::div/following-sibling::label//i[@role='presentation']";
	
	public static final String INTERESTED_IN_LISTITEM              = "//div[@class='q-virtual-scroll__content']//div/div[@class='q-item__label']";
	public static final String SUBMIT_BUTTON = "//span[text()='Submit']";
			
	
}

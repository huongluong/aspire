package pageUIs;

public class RegisterPageUI {
     public static final String FULL_NAME_TEXTBOX             = "//input[@name='full_name']";
     public static final String EMAIL_ADDRESS_TEXTBOX         = "//input[@name='email']";
     public static final String PHONE_TEXTBOX                 = "//input[@name='phone']";
     public static final String NON_DIRECTOR_ROLE_RADIOBUTTON = "//div[@class='q-radio__label q-anchor--skip' and contains(text(),'Non-director')]";
     public static final String DIRECTOR_ROLE_RADIOBUTTON     = "//div[@class='q-radio__label q-anchor--skip' and contains(text(),'Appointed director')]";
     public static final String SEARCH_TYPE_DROPDOWN          = "//input[@type='search']";
     public static final String SEARCH_TYPE_DROPDOWN_LISTITEM = "//div[@class='q-item__label']";
     public static final String SEARCH_TYPE_DROPDOWN_ITEM     = "//div[@class='q-item__label' and contains(text(),'%s')]";//%s = 'Referral' or 'Brochure' ...
     public static final String PROMO_CODE_TEXTBOX            = "//div[contains(text(),'Referral/promo code')]//parent::div//following::label//input";
     public static final String AGREE_CHECKBOX                = "//div[contains(text(),'I have read and agree with the')]//preceding-sibling::div";
     public static final String CONTINUE_BUTTON               = "//span[@class='block' and text()='Continue']";
}

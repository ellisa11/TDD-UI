package com.taobao.mobile.dipei.test;

import junit.framework.Assert;

import org.athrun.android.framework.AthrunTestCase;
import org.athrun.android.framework.Test;
import org.athrun.android.framework.viewelement.AbsListViewElement;
import org.athrun.android.framework.viewelement.TextViewElement;
import org.athrun.android.framework.viewelement.ViewElement;

import android.widget.EditText;

/**
 * @author kejie
 * 
 * �������������б�
 */

public class WaimaiStoreListTest extends AthrunTestCase {
	
	public static final String USERNAME = "c�����˺�36";
	public static final String PASSWD = "taobao1234";

	public WaimaiStoreListTest() throws Exception {
		super("con.taobao.mobile.dipei",
				"com.taobao.ecoupon.activity.MainActivity");
		AthrunTestCase.setMaxTimeToFindView(10000);
	}
	
	/**
	 * �����������б�û���Ͳ͵�ַ
	 * @throws Exception
	 */
	
	@Test
	public void testOpenWaimaiStoreList_NoDefaultAddress() throws Exception {
		Assert.assertEquals(true,
				getDevice().waitForActivity("MainActivity", 5000));
		findElementById("root_tab_waimai").doClick();
		Assert.assertNotNull(findElementById("waimai_start_button"));
		Assert.assertNotNull(findElementByText("�Ա�����"));
	}
	
	/**
	 * ��������Ͳ͵�ַ�������ַ�б�
	 * @throws Exception 
	 */
	
	@Test
	public void testOpenAddressList() throws Exception{
		findElementById("root_tab_waimai").doClick();
		findElementById("waimai_start_button").doClick();
		loginUtil(USERNAME,PASSWD);
		Assert.assertEquals(true, getDevice().waitForActivity("DeliveryAddressManageActivity",5000));
	}
	
	/**
	 * �����ջ���ַ��ɾ���ջ���ַ
	 * @throws Exception
	 */
	
	@Test
	public void testAddDeliveryAddress() throws Exception{
		findElementById("root_tab_waimai").doClick();
		findElementById("waimai_start_button").doClick();
		loginUtil(USERNAME,PASSWD);
		findElementByText("������ַ").doClick();
		Assert.assertEquals(true, getDevice().waitForActivity("ModifyUserAddressActivity",5000));
		findElementById("delivery_address_name", TextViewElement.class).setText("Сī");
		findElementById("delivery_address_phone", TextViewElement.class).setText("18800000000");
		findElementById("my_main_button_medium_text_size", TextViewElement.class).setText("����·99��");
		findElementByText("����").doClick();
		Assert.assertTrue(findElementById("delivery_address_list", AbsListViewElement.class).getAdapter().getCount() == 2);
		findElementById("delivery_address_manage").doClick();
		findElementById("delivery_address_remove", 0).doClick();
		findElementByText("ɾ��").doClick();
		Assert.assertTrue(findElementById("delivery_address_list", AbsListViewElement.class).getAdapter().getCount() == 1);
	}
	
	
	public void loginUtil(String userName,String pwd) throws Exception{
		findElementById("login_username_clear").doClick();
		findElementById("login_username",TextViewElement.class).setText(userName);
		findElementById("login_password",TextViewElement.class).setText(pwd);
		findElementById("login_button").doClick();
	}

}

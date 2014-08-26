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
 * 测试外卖店铺列表
 */

public class WaimaiStoreListTest extends AthrunTestCase {
	
	public static final String USERNAME = "c测试账号36";
	public static final String PASSWD = "taobao1234";

	public WaimaiStoreListTest() throws Exception {
		super("con.taobao.mobile.dipei",
				"com.taobao.ecoupon.activity.MainActivity");
		AthrunTestCase.setMaxTimeToFindView(10000);
	}
	
	/**
	 * 打开外卖店铺列表，没有送餐地址
	 * @throws Exception
	 */
	
	@Test
	public void testOpenWaimaiStoreList_NoDefaultAddress() throws Exception {
		Assert.assertEquals(true,
				getDevice().waitForActivity("MainActivity", 5000));
		findElementById("root_tab_waimai").doClick();
		Assert.assertNotNull(findElementById("waimai_start_button"));
		Assert.assertNotNull(findElementByText("淘宝外卖"));
	}
	
	/**
	 * 点击设置送餐地址，进入地址列表
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
	 * 新增收货地址，删除收货地址
	 * @throws Exception
	 */
	
	@Test
	public void testAddDeliveryAddress() throws Exception{
		findElementById("root_tab_waimai").doClick();
		findElementById("waimai_start_button").doClick();
		loginUtil(USERNAME,PASSWD);
		findElementByText("新增地址").doClick();
		Assert.assertEquals(true, getDevice().waitForActivity("ModifyUserAddressActivity",5000));
		findElementById("delivery_address_name", TextViewElement.class).setText("小墨");
		findElementById("delivery_address_phone", TextViewElement.class).setText("18800000000");
		findElementById("my_main_button_medium_text_size", TextViewElement.class).setText("华星路99号");
		findElementByText("保存").doClick();
		Assert.assertTrue(findElementById("delivery_address_list", AbsListViewElement.class).getAdapter().getCount() == 2);
		findElementById("delivery_address_manage").doClick();
		findElementById("delivery_address_remove", 0).doClick();
		findElementByText("删除").doClick();
		Assert.assertTrue(findElementById("delivery_address_list", AbsListViewElement.class).getAdapter().getCount() == 1);
	}
	
	
	public void loginUtil(String userName,String pwd) throws Exception{
		findElementById("login_username_clear").doClick();
		findElementById("login_username",TextViewElement.class).setText(userName);
		findElementById("login_password",TextViewElement.class).setText(pwd);
		findElementById("login_button").doClick();
	}

}

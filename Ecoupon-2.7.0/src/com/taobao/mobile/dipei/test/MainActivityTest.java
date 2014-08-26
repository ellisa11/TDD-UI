package com.taobao.mobile.dipei.test;

import org.athrun.android.framework.AthrunTestCase;
import org.athrun.android.framework.Test;
import org.athrun.android.framework.viewelement.CheckableElement;
import org.athrun.android.framework.viewelement.TextViewElement;
import org.athrun.android.framework.viewelement.ViewElement;
import org.athrun.android.framework.viewelement.ViewGroupElement;

public class MainActivityTest extends AthrunTestCase {

	public MainActivityTest() throws Exception {

		super("no_use", "com.taobao.ecoupon.activity.SplashActivity");
		AthrunTestCase.setMaxTimeToFindView(10000);
	}

	// 检验首页，下面有三个tab
	@Test
	public void test3tabs() throws Exception {
		getDevice().waitForActivity("com.taobao.ecoupon.activity.MainActivity");
        
		int c = findElementById("bottom_tabhost",ViewGroupElement.class).getChildCount();
        assertEquals(3, c);
        
		assertNotNull(findElementById("root_tab_diancai"));
		assertNotNull(findElementById("root_tab_waimai"));
		assertNotNull(findElementById("root_tab_wode"));
	}

	// 点击 “点菜”，检验当前定位城市为"杭州"
	@Test
	public void testCityName() throws Exception {

		getDevice().waitForActivity("com.taobao.ecoupon.activity.MainActivity");
		ViewElement diancai = findElementById("root_tab_diancai");

		diancai.doClick();
		// findElementById("change_city_button");
		assertNotNull(findElementByText("杭州"));
	}

	// 检验点菜首页-二维码扫描存在
	public void testEQ() throws Exception {
		getDevice().waitForActivity("com.taobao.ecoupon.activity.MainActivity");

		assertNotNull(findElementById("diancai_qbar_button"));
	}

	// 检验点菜首页-banner存在
	public void testBanner() throws Exception {
		getDevice().waitForActivity("com.taobao.ecoupon.activity.MainActivity");
        //todo
	}

	// 点菜首页-店铺列表
	@Test
	public void testShoplist() throws Exception {
		getDevice().waitForActivity("com.taobao.ecoupon.activity.MainActivity");
        ViewElement v = findElementById("cb_distance");
		assertNotNull(v);
		// 默认选中距离
		assertTrue(v.isSelected());

		assertNotNull(findElementById("cb_reputation"));
		assertNotNull(findElementById("cb_price"));
	}

	// 点菜首页-店铺列表-检验好评可点击
	@Test
	public void testShoplistreputation() throws Exception {
		getDevice().waitForActivity("com.taobao.ecoupon.activity.MainActivity");
        ViewElement v = findElementById("cb_reputation");
		v.doClick();
		assertTrue(v.isSelected());
	}

	// 点菜首页-店铺列表-检验人均可点击
	@Test
	public void testShoplistprice() throws Exception {
		getDevice().waitForActivity("com.taobao.ecoupon.activity.MainActivity");
        ViewElement v = findElementById("cb_price");
		v.doClick();
		assertTrue(v.isSelected());
	}
   // 点菜首页-店铺列表-切换大图小图按钮可点击
	@Test
	public void testShoplistpicture() throws Exception{
		
		getDevice().waitForActivity("com.taobao.ecoupon.activity.MainActivity");
		CheckableElement v=findElementById("diancai_view_mode_switch",CheckableElement.class);
		assertFalse(v.isChecked());
		
	    v.doClick();
	    assertTrue(v.isChecked());
	}
	
	@Test
	public void testlogin() throws Exception {

		// new ShareMethods().Login("c测试账号36", "taobao1234");
		getDevice().waitForActivity("com.taobao.ecoupon.activity.MainActivity");

		findElementById("root_tab_wode").doClick();

		findElementById("profile_login_button").doClick();

		findElementById("login_username_clear").doClick();

		TextViewElement v = findElementById("login_username",
				TextViewElement.class);
		v.setText("c测试账号36");
		findElementById("login_password", TextViewElement.class).setText(
				"taobao1234");

		findElementById("login_button").doClick();

	}

}

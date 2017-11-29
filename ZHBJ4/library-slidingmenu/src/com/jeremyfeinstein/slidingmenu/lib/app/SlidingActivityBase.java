package com.jeremyfeinstein.slidingmenu.lib.app;

import android.view.View;
import android.view.ViewGroup.LayoutParams;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public interface SlidingActivityBase {
	
	/**
	 * Set the behind com.itheima.shop.zhbj4.bean.view content to an explicit com.itheima.shop.zhbj4.bean.view. This com.itheima.shop.zhbj4.bean.view is placed directly into the behind com.itheima.shop.zhbj4.bean.view 's com.itheima.shop.zhbj4.bean.view hierarchy.
	 * It can itself be a complex com.itheima.shop.zhbj4.bean.view hierarchy.
	 *
	 * @param view The desired content to display.
	 * @param layoutParams Layout parameters for the com.itheima.shop.zhbj4.bean.view.
	 */
	public void setBehindContentView(View view, LayoutParams layoutParams);

	/**
	 * Set the behind com.itheima.shop.zhbj4.bean.view content to an explicit com.itheima.shop.zhbj4.bean.view. This com.itheima.shop.zhbj4.bean.view is placed directly into the behind com.itheima.shop.zhbj4.bean.view 's com.itheima.shop.zhbj4.bean.view hierarchy.
	 * It can itself be a complex com.itheima.shop.zhbj4.bean.view hierarchy. When calling this method, the layout parameters of the specified
	 * com.itheima.shop.zhbj4.bean.view are ignored. Both the width and the height of the com.itheima.shop.zhbj4.bean.view are set by default to MATCH_PARENT. To use your
	 * own layout parameters, invoke setContentView(android.com.itheima.shop.zhbj4.bean.view.View, android.com.itheima.shop.zhbj4.bean.view.ViewGroup.LayoutParams) instead.
	 *
	 * @param view The desired content to display.
	 */
	public void setBehindContentView(View view);

	/**
	 * Set the behind com.itheima.shop.zhbj4.bean.view content from a layout resource. The resource will be inflated, adding all top-level views
	 * to the behind com.itheima.shop.zhbj4.bean.view.
	 *
	 * @param layoutResID Resource ID to be inflated.
	 */
	public void setBehindContentView(int layoutResID);

	/**
	 * Gets the SlidingMenu associated with this com.itheima.shop.zhbj4.bean.activity.
	 *
	 * @return the SlidingMenu associated with this com.itheima.shop.zhbj4.bean.activity.
	 */
	public SlidingMenu getSlidingMenu();
		
	/**
	 * Toggle the SlidingMenu. If it is open, it will be closed, and vice versa.
	 */
	public void toggle();
	
	/**
	 * Close the SlidingMenu and show the content com.itheima.shop.zhbj4.bean.view.
	 */
	public void showContent();
	
	/**
	 * Open the SlidingMenu and show the menu com.itheima.shop.zhbj4.bean.view.
	 */
	public void showMenu();

	/**
	 * Open the SlidingMenu and show the secondary (right) menu com.itheima.shop.zhbj4.bean.view. Will default to the regular menu
	 * if there is only one.
	 */
	public void showSecondaryMenu();
	
	/**
	 * Controls whether the ActionBar slides along with the above com.itheima.shop.zhbj4.bean.view when the menu is opened,
	 * or if it stays in place.
	 *
	 * @param slidingActionBarEnabled True if you want the ActionBar to slide along with the SlidingMenu,
	 * false if you want the ActionBar to stay in place
	 */
	public void setSlidingActionBarEnabled(boolean slidingActionBarEnabled);
	
}

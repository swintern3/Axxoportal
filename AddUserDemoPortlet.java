package com.adduserdemo.portlet;

import com.adduserdemo.constants.AddUserDemoPortletKeys;
import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;
import java.util.Date;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author axxonet
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.addUserDemo",
		"com.liferay.portlet.header-portlet-css=/css/main.css",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=AddUserDemo",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AddUserDemoPortletKeys.ADDUSERDEMO,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AddUserDemoPortlet extends MVCPortlet {
	
	@Override
	public void doView(RenderRequest renderRequest, RenderResponse renderResponse)
			throws IOException, PortletException {
		System.out.println("<<<<<<------doView Working------>>>>>>");
		super.doView(renderRequest, renderResponse);
	}
	
	@Override
	public void serveResource(ResourceRequest resourceRequest,ResourceResponse resourceResponse)throws  IOException, PortletException {
	
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		
		String screenname;
		String firstname;
		String lastname;
		String email;
		String age;
		String pass;
		Date createdate;
		try {
			System.out.println("-----Started Add User----->>>>>");
			screenname = ParamUtil.getString(resourceRequest, "screenname");
			firstname = ParamUtil.getString(resourceRequest, "fname");
			lastname = ParamUtil.getString(resourceRequest, "lname");
			email = ParamUtil.getString(resourceRequest, "email");
			createdate = new Date();
			pass = ParamUtil.getString(resourceRequest, "pass");
			
			User user = UserLocalServiceUtil.createUser(CounterLocalServiceUtil.increment());

			user.setScreenName(screenname);
			user.setFirstName(firstname);
			user.setLastName(lastname);
			user.setEmailAddress(email);
			user.setPassword(pass);
			user.setCreateDate(createdate);
			user.setCompanyId(themeDisplay.getCompanyId());
			

			System.out.println(screenname + "  " + firstname + "  " + lastname + "  " + email+
					"  "+pass+"  "+createdate);
			System.out.println(user);
			User newuser = UserLocalServiceUtil.addUser(user);
			System.out.println(newuser);
			System.out.println("------Ended User Add ---->>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
package com.bgs.web;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bgs.bean.Product;
import com.bgs.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService ser;
	@RequestMapping("/tologin")
	public String loginjsp() {
		return "login";
	}
	@RequestMapping("/toadd")
	public String addjsp() {
		return "addPro";
	}
	@RequestMapping("/loginAdmin")
	public String login(String username, String password, Model mo) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			subject.login(token);
			subject.getSession().setAttribute("user", username);
		} catch (UnknownAccountException e) {
			mo.addAttribute("user", username);
			mo.addAttribute("errorMsg", "用户名或密码错误！");
			return "login";
		} catch (IncorrectCredentialsException e) {
			mo.addAttribute("user", username);
			mo.addAttribute("errorMsg", "用户名或密码错误！");
			return "login";
		} catch (ExcessiveAttemptsException e) {
			mo.addAttribute("user", username);
			mo.addAttribute("errorMsg", "登录失败多次，账户锁定1分钟");
			return "login";
		} catch (Exception e) {
			mo.addAttribute("user", username);
			mo.addAttribute("errorMsg", "其他错误");
			return "login";
		}
		return "home";
	}

	@RequestMapping("/loginOut")
	public String loginOut() {
		SecurityUtils.getSubject().logout();
		return "login";
	}

	@RequestMapping("/findProList")
	public String findProList(Model m) {
		List<Product> list = ser.findProList();
		m.addAttribute("pro", list);
		return "proList";
	}

	@RequestMapping("/addProduct")
	public String addProduct(Product p) {
		ser.addProduct(p);
		return "redirect:findProList";
	}

	@RequestMapping("/delProduct")
	public String delProduct(int id) {
		ser.delProduct(id);
		return "redirect:findProList";
	}
}

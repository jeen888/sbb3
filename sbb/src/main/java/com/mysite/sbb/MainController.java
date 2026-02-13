package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@GetMapping("/sbb")
	@ResponseBody
	public String index() {
		return "안녕하세요 sbb에 오신것을 환영합니다.";
	}

	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
	}

	// @GetMapping("/success")
	// public String loginok() {
	// 	return "redirect:/question/list?loginSuccess";	// 로그인 성공 후 세션에 남아있는 username 제거 목적
	// }
}

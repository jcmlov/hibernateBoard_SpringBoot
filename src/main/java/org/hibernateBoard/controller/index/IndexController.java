package org.hibernateBoard.controller.index;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernateBoard.security.social.kakao.KakaoRestApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.JsonNode;

@Controller
@RequestMapping(value="/")
public class IndexController {
	
	@GetMapping(value="")
	public String index() {
		return "/index";
	}
	
	@GetMapping(value="main")
	public String indexMain(Model model, Principal principal) {
		return "/index/main";
	}
	
	@RequestMapping(value = "oauth", produces = "application/json", method = { RequestMethod.GET, RequestMethod.POST })
    public String loginKakao(@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response) {
        System.out.println(code);
        
        JsonNode jsonToken = KakaoRestApi.getAccessToken(code);
        System.out.println("JSON 반환 : " + jsonToken.get("access_token"));

        return "/index/main";
    }


}

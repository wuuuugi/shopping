package com.example.sample1.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.sample1.model.User;
import com.example.sample1.service.UserService;
import com.google.gson.Gson;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	// 로그인
	@RequestMapping("/login.do") 
    public String login(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
        
		session.invalidate(); // 전체 세션 삭제
		return "/user/login";
    }
	//  회원가입
	@RequestMapping("/join.do") 
    public String join(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
       

		return "/user/join";
    }
	
	
	@RequestMapping("/header1.do")
    public String header(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
        return "/header/header1";
    }
	
	@RequestMapping("/header2.do") 
    public String header2(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
        return "/header/header2";
    }
	
	@RequestMapping("/faq.do") 
    public String faq(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
        return "/faq";
    }
	
	@RequestMapping("/footer.do") 
    public String footer(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
        return "/header/footer";
    }
	@RequestMapping("/insertcontents.do") 
    public String insertcontents(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
       

		return "/user/insertcontents";
    }
	@RequestMapping("/insertcontents2.do") 
    public String insertcontents2(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
       

		return "/user/insertcontents2";
    }
	@RequestMapping("/standard.do") 
    public String standard(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
       

		return "/user/standard";
    }
	
	@RequestMapping("/idsearch.do") 
    public String idsearch(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
       

		return "/user/idsearch";
    }
	
	@RequestMapping("/pwdsearch.do") 
    public String pwdsearch(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
       

		return "/user/pwdsearch";
    }
	@RequestMapping("/idresult.do") 
    public String idresult(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
       

		return "/user/idresult";
    }
	@RequestMapping("/pwdresult.do") 
    public String pwdresult(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
       

		return "/user/pwdresult";
    }
	@RequestMapping("/onetoone.do") 
    public String onetoone(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
       

		return "/user/onetoone";
    }

	
	//회원가입 
	@RequestMapping(value = "/user/insert.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String userAdd(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		userService.insertUser(map);
		resultMap.put("success", "가입완료");
		return new Gson().toJson(resultMap);
	}
	
	// select where id 
	@RequestMapping(value = "/user/selectId.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String check(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		User user = userService.searchUserLoginIdCheck(map);
		resultMap.put("info", user);
		return new Gson().toJson(resultMap);
	}
	
	// 로그인 
	@RequestMapping(value = "/login.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = userService.searchUserLoginAllCheck(map);
		if(resultMap.containsKey("user")) {// 키값이 있다면 true 리턴 없다면 false 리턴
			User user = (User) resultMap.get("user"); // 다운캐스팅
			session.setAttribute("sessionId", user.getUserId()); // 세션 에다가 user 안에 getId 넣기
			session.setAttribute("sessionName", user.getUserName());
			session.setAttribute("sessionStatus", user.getUserState()); 
			session.setAttribute("sessionEmail", user.getUserEmail()); 
			session.setAttribute("sessionNickname", user.getUserNickname());
		}
		return new Gson().toJson(resultMap);
	}
	// 이메일, 비번, 전화번호 마스킹
	@RequestMapping(value = "/user/selectMasked.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String selectMasked(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		HashMap<String, Object> maskedinfo = userService.searchMaskedinfo(map);
		resultMap.put("maskedinfo", maskedinfo);
		return new Gson().toJson(resultMap);
	}
	
	// 로그인정보 수정
	@RequestMapping(value = "/user/editInfo.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String editInfo(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		userService.editUserLoginInfo(map);
		
		return new Gson().toJson(resultMap);
	}
	
	// 유저 힌트 확인
	@RequestMapping(value = "/user/searchHint.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String searchHint(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		userService.searchPwdHintAnswer(map);
		
		return new Gson().toJson(resultMap);
	}
	
	// 이메일 아이디 비번 확인
	@RequestMapping(value = "/user/emailIdPwd.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String emailIdPwd(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int num = userService.searchEmailIdPwd(map);
		resultMap.put("num", num);
		return new Gson().toJson(resultMap);
	}
		
	// 회원탈퇴
	@RequestMapping(value = "/user/infoRemove.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String infoRemove(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		userService.removeUserInfo(map);
					
		return new Gson().toJson(resultMap);
	}
}

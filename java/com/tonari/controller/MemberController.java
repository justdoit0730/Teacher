package com.tonari.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
/*import org.springframework.security.crypto.password.PasswordEncoder;*/
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tonari.domain.MemberVO;

import com.tonari.service.ChatService;

import com.tonari.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/join/*")
@AllArgsConstructor
@Log4j
public class MemberController {
	
	@Setter(onMethod_ = @Autowired)
	private MemberService service;

//	@Setter(onMethod_ = @Autowired)
//	private PasswordEncoder pwencoder;
	
	private ChatService chatService;
	
	@PostMapping("/login")
	 public String login(HttpServletRequest request, MemberVO mvo) throws IOException {
		
		//0) DB검색
		MemberVO member = service.loginCheck(mvo); // nick
		
		// 1이면 로그인 성공 , 0이면 실패	
		if (member != null) {
			
			//1) 세션 가져오기
			HttpSession session = request.getSession();
			
			//2) 표출되는 권한 명칭 설정
			/*
			 * String authName = "게시판 관리자"; if ( member.getAuth().equals("ADMINISTRATOR") )
			 * { authName = "전체관리자"; }
			 */
			
			//3) 세션 유지시간 설정
			session.setMaxInactiveInterval(1800); // 1800 = 60s*30 (30분)
			        
			//4) 회원정보 설정
			session.setAttribute("nowUser", member);
			/* session.setAttribute(AUTH, member.getAuth()); */
			/* session.setAttribute(AUTH_NAME, authName); */
			chatService.setQueue(member.getBno());
		} else {  
			return "redirect:/join/loginerror"; }
		
		return "redirect:/";
	}
	
	@GetMapping("/loginerror")
	public String loginerror() {
		return "/join/loginerror";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		request.getSession(true);
		return "redirect:/";
	}
	
	@GetMapping("/member.do")
	public void memberView() {

	}
	
	/*
	 * @GetMapping("/checkUserid.do") public @ResponseBody int
	 * checkUserId(@RequestParam("userid") String email) { int result =
	 * service.emailCheck(email); log.info(result); return result; }
	 */

	//회원가입
	  @PostMapping("/memberinsertpro.do") 
	  public String register(MemberVO member,RedirectAttributes rttr) {
	
	  service.register(member);
	 
	return"redirect:/";
	
	  }
	//내가 추가함



    // 아이디 체크
    @PostMapping("/emailCheck")
    @ResponseBody
    public String idCheck(@RequestParam("email") String email){
        log.info("userIdCheck 진입");
        log.info("전달받은 id:"+ email);
        String result = "" + service.emailCheck(email);
        log.info("확인 결과:" + result);
        return result;
	
        
       
	
      //내가 추가함

}
    //닉네임 체크(회원가입)
    @PostMapping("/nickCheck")
    @ResponseBody
    public String nickCheck(@RequestParam("nick") String nick){
        log.info("userIdCheck 진입");
        log.info("전달받은 id:"+ nick);
        String result = "" + service.nickCheck(nick);
        log.info("확인 결과:" + result);
        return result;
        
    }
    
    //닉네임 체크(개인정보 수정)
    @PostMapping("/nickCheck2")
    @ResponseBody
    public String nickCheck(HttpServletRequest request, @RequestParam("nick") String nick){
        log.info("userIdCheck 진입");
        log.info("전달받은 id:"+ nick);
        HttpSession session = request.getSession();
        String nowNick = ((MemberVO)session.getAttribute("nowUser")).getNick();
        if(nowNick.equals(nick)) {
        	return"2";
        }
        
        String result = "" + service.nickCheck(nick);
        log.info("확인 결과:" + result);
        return result;
        
    }
    
    
    
    @GetMapping("/studentinfologintest")
	public String studentinfologintest(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO) session.getAttribute("nowUser");
		if (mvo == null) {
			return "redirect: /login";
		}
		return "redirect:/mypage/studentinfo";
	}
    
    @PostMapping("/studentinfoModify")
    public String studentinfoModify(HttpServletRequest request, MemberVO vo){
		/* log.info("============== VO : " + vo); */
    	service.studentinfoModify(vo);
    	HttpSession session = request.getSession();
    	MemberVO member = (MemberVO) session.getAttribute("nowUser");
    	member.setNick(vo.getNick());
    	session.setAttribute("nowUser", member);
    	
        return "redirect:/mypage/studentinfo";
    }
    

    
}

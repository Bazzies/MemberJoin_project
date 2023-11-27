package spring.joinmember.Controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.joinmember.DTO.MemberDTO;
import spring.joinmember.Service.MemberService;

import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    //생성자 주입
    private final MemberService memberService;

    @GetMapping("member/new")
    public String GetCreateMember()
    {
        return "new";
    }

    @PostMapping("member/new")
    public String PostCreateMember(@ModelAttribute MemberDTO memberDTO)
    {
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "redirect:/";
    }

    @GetMapping("member/login")
    public String GetLogin(){
        return "login";
    }

    @PostMapping("member/login")
    public String Postlogin(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null){
            //로그인 성공
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "main";
        }
        else {
            //로그인 실패
            return "login";
        }
    }

    @GetMapping("member/list")
    public String ListMember(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        model.addAttribute("memberList",memberDTOList);

        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model){
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member",memberDTO);
        return "detail";
    }
    @GetMapping("/member/update")
    public String Getupdate(HttpSession session, Model model){
        String myEmail = (String)session.getAttribute("loginEmail"); //session반환값은 object이다
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember",memberDTO);
        return "update";
    }

    @PostMapping("/member/update")
    public String Postupdate(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/member/" + memberDTO.getMemberId();
    }

    @GetMapping("/member/delete/{id}")
    public String deletById(@PathVariable Long id){
        memberService.deleteBymemberId(id);
        return "redirect:/member/list";
    }

    @GetMapping("member/logout")
    public String logout(HttpSession session)
    {
        session.invalidate(); //세션 무효화
        return "Home";
    }
}

package test.spring.controller.park;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import test.spring.component.park.FaqBoardDTO;
import test.spring.component.park.QnaDTO;
import test.spring.component.park.QnaPage;
import test.spring.service.park.FaqService;
import test.spring.service.park.QnaService;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	@Autowired
	private FaqService faqService;
	@Autowired 
	private QnaService qnaservice;
	@Autowired 
	private QnaPage page;
	
	//faq
	@RequestMapping("/faqWriteForm")
	public String faqWriteForm() {
		return "/park/faq/faqWriteForm";
	}
	@RequestMapping("/faqWritePro")
	public String insert(FaqBoardDTO dto, Model model) {
		int isTrue = faqService.insert(dto);
		faqService.insert(dto);
		model.addAttribute("isTrue",isTrue);
		return "/park/faq/faqList";
	}
	@RequestMapping("/faqList")
	public String faqList(FaqBoardDTO dto, Model model) {
	    List<FaqBoardDTO> faqList = faqService.faqList(dto);
	    model.addAttribute("faqList", faqList);
	    return "/park/faq/faqList";
	}
	//qna 
	@RequestMapping("/qnaWriteForm")
	public String qnaWriteForm() {
		return "/park/qna/qnaWriteForm";
	}
	@RequestMapping("/qnaInsert")
	public String qnaInsert(QnaDTO dto) {
		qnaservice.qnaInsert(dto);
		return "/park/qna/qnaWriteForm";
	}
	@RequestMapping("/qnaList")
	public String list(Model model, HttpSession session, @RequestParam(defaultValue = "1") int curPage, String search, String keyword) {
//		//QNA 클릭 하면 admin으로 자동 로그인
//		HashMap<String, String> map = new HashMap<String, String>();
//		//HashMap : 데이터를 담을 자료 구조
//		map.put("id", "admin");
//		map.put("pw", "1234");
//		session.setAttribute("login_info", member.member_login(map));
//		session.setAttribute("category", "qna");
		
		//DB에서 글 목록 조회해와 화면에 출력
		page.setCurPage(curPage);
		page.setSearch(search);
		page.setKeyword(keyword);
		model.addAttribute("page", qnaservice.qnaList(page));
		return "/park/qna/qnaList";
	}
	//QNA 글 상세 화면 요청
		@RequestMapping("/qnaDetail")
		public String detail(int num, Model model) {
			//선택한 QNA 글에 대한 조회수 증가 처리
			qnaservice.qnaDetail(num);
			
			//선택한 QNA 글 정보를 DB에 조회해와 상세 화면에 출력
			model.addAttribute("dto", qnaservice.qnaDetail(num));
			model.addAttribute("crlf", "\r\n");
			model.addAttribute("page", page);
			
			return "/park/qna/qnaDetail";
		} 
}

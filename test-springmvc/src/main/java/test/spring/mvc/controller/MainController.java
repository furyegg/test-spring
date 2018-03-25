package test.spring.mvc.controller;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
public class MainController {
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/")
	public String root() {
		return "redirect:/welcome";
	}
	
	@RequestMapping("/welcome")
	public ModelAndView helloWorld() {
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		return new ModelAndView("welcome", "message", message);
	}
	
	@RequestMapping("/login")
	public String index() {
		return "login";
	}
	
	@RequestMapping("/about")
	public String about() {
		return "about";
	}
	
	@PostMapping("/addComment")
	public String addComment(@RequestBody MultiValueMap<String, String> params) {
		List<String> comments = (List<String>) session.getAttribute("comments");
		if (comments == null) {
			comments = Lists.newArrayList();
		}
		
		comments.add(params.getFirst("comment").toString());
		session.setAttribute("comments", comments);
		return "comments";
	}
}
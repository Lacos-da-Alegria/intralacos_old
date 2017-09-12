package com.lacosdaalegria.intra.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lacosdaalegria.intra.hibernate.dao.FeedbackDAO;
import com.lacosdaalegria.intra.hibernate.dao.FeedbackNovatoDAO;
import com.lacosdaalegria.intra.hibernate.dao.VoluntarioDAO;
import com.lacosdaalegria.intra.hibernate.model.Feedback;
import com.lacosdaalegria.intra.hibernate.model.FeedbackNovato;
import com.lacosdaalegria.intra.hibernate.model.Voluntario;
import com.lacosdaalegria.intra.model.UserDetail;



@Controller
public class FeedbackController {

	@RequestMapping("feedback")
	public String feedback(@RequestParam("feedback") String texto) {
		
		FeedbackDAO dao = new FeedbackDAO();
		Feedback feed = new Feedback();
		
		feed.setMensagem(texto);
		
		dao.addFeedback(feed);
		
		return "redirect:Ouvidoria";
		
	}
	
	@RequestMapping("feedbackNovato")
	public String feedbackNovato(FeedbackNovato feedback, HttpSession session){
		
		FeedbackNovatoDAO feedDao = new FeedbackNovatoDAO();
		VoluntarioDAO volDao = new VoluntarioDAO();
		UserDetail userDetail = new UserDetail();
		
		Voluntario novato = volDao.VoluntarioPelaId(userDetail.user(session).getUser());
		
		novato.setFeedbackNovato(2);
		
		feedDao.addFeedbackNovato(feedback);
		volDao.updateVoluntario(novato);
		
		return "redirect:"+(String)session.getAttribute("currentpage");
	}
	
}

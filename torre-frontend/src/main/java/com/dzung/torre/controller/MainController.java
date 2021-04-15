package com.dzung.torre.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.dzung.torre.properties.AppProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class MainController {
	@Autowired
	private AppProperties appProperties;
	
	@GetMapping("/")
	public String pageIndex(Model model) {
		model.addAttribute("appName", appProperties.getAppName());
		
		return "index";
	}
}

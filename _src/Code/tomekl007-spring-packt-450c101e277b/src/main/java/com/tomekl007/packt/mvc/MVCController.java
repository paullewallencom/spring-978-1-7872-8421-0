package com.tomekl007.packt.mvc;

import com.tomekl007.packt.controllers.TravelDto;
import com.tomekl007.packt.model.Travel;
import com.tomekl007.packt.repository.TravelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MVCController {

    @Autowired
    private TravelRepository travelRepository;

    @RequestMapping("/mvc/all-travels")
    public String indexView(Model model) {
        model.addAttribute("list", travelRepository.findAll());
        return "allTravels";
    }

    @PostMapping("/mvc/travel")
    public String travelSubmit(@ModelAttribute TravelDto travelDto, Model model) {
        travelRepository.save(new Travel(travelDto.getUserId(), travelDto.getSource(), travelDto.getDestination()));
        model.addAttribute("list", travelRepository.findAll());
        return "allTravels";
    }

    @GetMapping("/mvc/createTravel")
    public String travelForm(Model model) {
        model.addAttribute("travelDto", new TravelDto());
        return "create";
    }
}

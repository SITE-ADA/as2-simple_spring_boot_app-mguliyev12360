package com.hw.wm2.controller;

import com.hw.wm2.entity.Team;
import com.hw.wm2.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Autowired
    private TeamService service;

    @GetMapping("/")
    public String all(Model model) {
        var all = service.getAll();
        model.addAttribute("teams", all);
        return "teams";
    }

    @GetMapping("/{id}")
    public String player(@PathVariable Long id, Model model) {
        var team = service.getById(id);
        model.addAttribute("team", team);
        return "team";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("team", new Team());
        return "new_team";
    }

    @PostMapping("/create")
    public String create(Model model, Team team) {
        service.save(team);
        var teams = service.getAll();
        model.addAttribute("teams", teams);
        return "teams";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        var team = service.getById(id);
        model.addAttribute("team", team);
        return "update_team";
    }

    @PostMapping("/update_save")
    public String updateSave(Model model, Team team) {
        service.saveUpdates(team);
        var teams = service.getAll();
        model.addAttribute("teams", teams);
        return "teams";
    }

    @GetMapping("/remove/{id}")
    public String remove(Model model, @PathVariable Long id) {
        service.removeById(id);
        var teams = service.getAll();
        model.addAttribute("teams", teams);
        return "teams";
    }

    @GetMapping("/remove_player_from_team/{team_id}/{player_id}")
    public String removeFromTeam(Model model, @PathVariable(value = "team_id") Long teamId, @PathVariable(value = "player_id") Long playerId) {
        service.removeFromTeam(teamId, playerId);
        var teams = service.getAll();
        model.addAttribute("teams", teams);
        return "teams";
    }

}

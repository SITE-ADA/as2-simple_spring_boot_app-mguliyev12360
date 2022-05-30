package com.hw.wm2.controller;

import com.hw.wm2.entity.Player;
import com.hw.wm2.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService service;

    @GetMapping("/")
    public String all(Model model) {
        var players = service.getAll();
        model.addAttribute("players", players);
        return "players";
    }

    @GetMapping("/{id}")
    public String player(@PathVariable Long id, Model model) {
        var player = service.getById(id);
        model.addAttribute("player", player);
        return "player";
    }

    @GetMapping("/new")
    public String showNew(Model model) {
        model.addAttribute("player", new Player());
        return "new_player";
    }

    @PostMapping("/create")
    public String create(Model model, Player player) {
        service.save(player);
        var players = service.getAll();
        model.addAttribute("players", players);
        return "players";
    }

    @GetMapping("/update/{id}")
    public String update(Model model, @PathVariable Long id) {
        var player = service.getById(id);
        model.addAttribute("player", player);
        return "update_player";
    }

    @PostMapping("/update_save")
    public String updateSave(Model model, Player player) {
        service.saveUpdates(player);
        var players = service.getAll();
        model.addAttribute("players", players);
        return "players";
    }

    @GetMapping("/remove/{id}")
    public String remove(Model model, @PathVariable Long id) {
        service.removeById(id);
        var players = service.getAll();
        model.addAttribute("players", players);
        return "players";
    }

    @GetMapping("/remove_team_from_player/{id}")
    public String removeTeamFromPlayer(Model model, @PathVariable Long id) {
        service.removeTeamFromPlayer(id);
        var players = service.getAll();
        model.addAttribute("players", players);
        return "players";
    }
}

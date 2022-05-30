package com.hw.wm2.service;

import com.hw.wm2.entity.Player;
import com.hw.wm2.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerService {
    @Autowired
    private PlayerRepository repository;

    public List<Player> getAll() {
        var all = repository.findAll();
        return (List<Player>) all;
    }

    public Player getById(Long id) {
        var byId = repository.findById(id).orElseThrow();
        return byId;
    }

    public Player save(Player player) {
        var saved = repository.save(player);
        return saved;
    }

    public void saveUpdates(Player player) {
        var first = player.getFirstName();
        var last = player.getLastName();
        var id = player.getId();
        repository.update(first, last, id);
    }

    public void removeById(Long id) {
        repository.removeTeamFromPlayer(id);
        repository.remove(id);
    }

    public void removeTeamFromPlayer(Long id) {
        repository.removeTeamFromPlayer(id);
    }
}

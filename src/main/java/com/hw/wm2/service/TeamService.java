package com.hw.wm2.service;

import com.hw.wm2.entity.Team;
import com.hw.wm2.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TeamService {
    @Autowired
    TeamRepository repository;

    public List<Team> getAll() {
        var all = repository.findAll();
        return (List<Team>) all;
    }

    public Team getById(Long id) {
        var t = repository.findById(id).orElseThrow();
        return t;
    }

    public Team save(Team team) {
        var t = repository.save(team);
        return t;
    }

    public void saveUpdates(Team team) {
        var name = team.getName();
        var league = team.getLeague();
        var id = team.getId();
        repository.update(name, league, id);
    }

    public void removeById(Long id) {
        repository.removeAllPlayersFromTeam(id);
        repository.remove(id);
    }

    public void removeFromTeam(Long teamId, Long playerId) {
        repository.removeFromTeam(teamId, playerId);
    }
}

package com.hw.wm2.repository;

import com.hw.wm2.entity.Team;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface TeamRepository extends CrudRepository<Team, Long> {

    @Query(value = "update team set name = ?1, league = ?2 where team_id = ?3", nativeQuery = true)
    @Modifying
    void update(String name, String league, Long id);

    @Modifying
    @Query(value = "delete from player_team where team_id = ?1", nativeQuery = true)
    void removeAllPlayersFromTeam(Long teamId);

    @Modifying
    @Query(value = "delete from player_team where team_id = :teamId and player_id = :playerId", nativeQuery = true)
    void removeFromTeam(@Param("teamId") Long id, @Param("playerId") Long playerId);

    @Query("delete from Team t where t.id = :teamId")
    @Modifying
    void remove(@Param("teamId") Long id);
}

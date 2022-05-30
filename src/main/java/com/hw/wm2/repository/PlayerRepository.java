package com.hw.wm2.repository;

import com.hw.wm2.entity.Player;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends CrudRepository<Player, Long> {
    @Query(value = "update player set first_name = ?1, last_name = ?2 where player_id = ?3", nativeQuery = true)
    @Modifying
    void update(String first, String last, Long id);

    @Query(value = "delete from player_team where player_id = :playerId", nativeQuery = true)
    @Modifying
    void removeTeamFromPlayer(@Param("playerId") Long id);

    @Query("delete from Player p where p.id = :id")
    @Modifying
    void remove(@Param("id") Long id);
}

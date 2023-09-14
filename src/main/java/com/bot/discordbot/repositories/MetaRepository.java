package com.bot.discordbot.repositories;

import com.bot.discordbot.entity.Meta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Integer> {
    Meta getMetaById(int id);

}

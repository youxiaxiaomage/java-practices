package com.yxxmg.mapstruct;

import com.yxxmg.mapstruct.convert.PlayerMapper;
import junit.framework.TestCase;

import java.util.Collections;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/5/17
 */
public class PlayerTest extends TestCase {

    public void test() {
        Game game1 = new Game().setId("1").setName("LOL").setOpenTime(new Date()).setDesc("英雄联盟");
        Game game2 = new Game().setId("2").setName("DOTA").setOpenTime(new Date()).setDesc("dota");
        Player player1 = new Player().setId(1L).setName("Uzi").setGames(Collections.singletonList(game1));
        Player player2 =
                new Player().setId(2L).setName("lige").setGames(Stream.of(game1, game2).collect(Collectors.toList()));

        PlayerDTO playerDTO = PlayerMapper.MAPPER.to(player1);
        System.out.println(playerDTO);
    }
}

package com.yxxmg.mapstruct.convert;

import com.yxxmg.mapstruct.Player;
import com.yxxmg.mapstruct.PlayerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/28
 */
@Mapper
public interface PlayerMapper {
    PlayerMapper MAPPER = Mappers.getMapper(PlayerMapper.class);

    PlayerDTO to(Player player);

    // void to(Player player1, Player player2);
}
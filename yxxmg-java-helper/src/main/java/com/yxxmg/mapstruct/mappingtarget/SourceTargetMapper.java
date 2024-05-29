package com.yxxmg.mapstruct.mappingtarget;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2024/5/29
 */
@Mapper
public interface SourceTargetMapper {
    SourceTargetMapper MAPPER = Mappers.getMapper(SourceTargetMapper.class);

    void toEntity(ParentDto s, @MappingTarget ParentEntity t);

    @Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface LenientMapper {

        void toEntity(ChildDto s, @MappingTarget ChildEntity t);
    }
}

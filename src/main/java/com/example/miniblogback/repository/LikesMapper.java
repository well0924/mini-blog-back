package com.example.miniblogback.repository;

import com.example.miniblogback.vo.dto.LikeDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface LikesMapper {
    //좋아요 중복처리
    Optional<LikeDto.LikeResponse> duplicatedLikes(@Param("boardId") Long boardId , @Param("memberId") Long memberId);
    //좋아요 +1
    Long plusLike(LikeDto.LikeRequest request);
    //좋아요 -1
    Long minusLike(LikeDto.LikeRequest request);
}

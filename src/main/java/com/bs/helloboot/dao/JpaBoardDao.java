package com.bs.helloboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bs.helloboot.dto.BoardEntity;

@Repository
public interface JpaBoardDao extends JpaRepository<BoardEntity, Long>{

}

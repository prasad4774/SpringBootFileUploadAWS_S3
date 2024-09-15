package com.aws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aws.entity.Img;

@Repository
public interface ImgRepository extends JpaRepository<Img, Integer> {
	

}

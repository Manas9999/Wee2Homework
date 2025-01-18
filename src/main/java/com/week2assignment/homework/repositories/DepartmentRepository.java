package com.week2assignment.homework.repositories;

import com.week2assignment.homework.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long>{
}

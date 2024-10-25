package com.emse.spring.automacorp.dao;
import java.util.List;
import com.emse.spring.automacorp.model.WindowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.emse.spring.automacorp.model.SensorEntity;

public interface WindowDao extends JpaRepository<WindowEntity, Long>, WindowDaoCustom{

    @Query("select c from WindowEntity c where c.name=:name")
    WindowEntity findByName(@Param("name") String name);

    @Query("select w from WindowEntity w where w.id=:id")
    WindowEntity findById(@Param("id") String id);


}


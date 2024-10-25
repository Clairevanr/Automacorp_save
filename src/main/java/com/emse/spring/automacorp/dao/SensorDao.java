package com.emse.spring.automacorp.dao ;
import java.util.List;
import com.emse.spring.automacorp.model.SensorEntity;
import com.emse.spring.automacorp.model.WindowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SensorDao extends JpaRepository<SensorEntity, Long> {

    @Query("select c from SensorEntity c where c.id=:id")
    SensorEntity findById(@Param("id") String id);



}





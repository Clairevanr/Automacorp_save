package com.emse.spring.automacorp.dao;
import java.util.List;
import com.emse.spring.automacorp.model.WindowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WindowDaoCustom {
    List<WindowEntity> findRoomsWithOpenWindows(Long id);
    List<WindowEntity> findWindowsByRoomName(String roomName);
    void deleteAllWindowsInRoom(Long roomId);
    void updateWindowStatusInRoom(Long roomId, String status);

    void deleteWindowsByRoomId(Long id);

    void updateWindowStatusByRoomId(Long id, double v);

    void updateWindowStatusInRoom(Long roomId, boolean isOpen);
}

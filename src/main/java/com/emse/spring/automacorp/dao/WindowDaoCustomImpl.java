package com.emse.spring.automacorp.dao;



import com.emse.spring.automacorp.model.WindowEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class WindowDaoCustomImpl implements WindowDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<WindowEntity> findRoomsWithOpenWindows(Long id) {
        String jpql = "select w from WindowEntity w inner join w.windowStatus s " +
                "where w.room.id = :id and s.value > 0.0 order by w.name";
        return em.createQuery(jpql, WindowEntity.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<WindowEntity> findWindowsByRoomName(String name){
        String jpql = "select w from WindowEntity w inner join w.windowStatus s " +
                "where w.room.name = :name order by w.name";
        return em.createQuery(jpql, WindowEntity.class)
                .setParameter("name", name)
                .getResultList();
    }



    @Override
    public void deleteAllWindowsInRoom(Long roomId) {
        String jpql = "DELETE FROM WindowEntity w WHERE w.room.id = :roomId";
        em.createQuery(jpql)
                .setParameter("roomId", roomId)
                .executeUpdate();
    }

    @Override
    public void updateWindowStatusInRoom(Long roomId, String status) {

    }

    @Override
    public void deleteWindowsByRoomId(Long id) {

    }

    @Override
    public void updateWindowStatusByRoomId(Long id, double v) {

    }


    @Override
    public void updateWindowStatusInRoom(Long roomId, boolean isOpen) {
        String jpql = "UPDATE WindowEntity w SET w.windowStatus.value = :status WHERE w.room.id = :roomId";
        em.createQuery(jpql)
                .setParameter("roomId", roomId)
                .setParameter("status", isOpen ? 1.0 : 0.0)
                .executeUpdate();
    }
}

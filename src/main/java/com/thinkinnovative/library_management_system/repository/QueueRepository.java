package com.thinkinnovative.library_management_system.repository;

import com.thinkinnovative.library_management_system.dto.QueueDTO;
import com.thinkinnovative.library_management_system.entity.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueueRepository extends JpaRepository <Queue, Integer> {

//    @Query("SELECT new com.thinkinnovative.library_management_system.dto.QueueDTO(" +
//                    "q.queue_id, q.member_id, q.position)" +
//                 "FROM Queue q WHERE q.libraryInformation.bookID = :bookID")
//    List<QueueDTO> findByQueueIdOrderByAsec(@Param("bookID") Integer bookID);

    @Query("SELECT new com.thinkinnovative.library_management_system.dto.QueueDTO(" +
            "q.queueId, q.memberId, m.memberName, q.position) " +
            "FROM Queue q " +
            "JOIN MemberTable m ON q.memberId = m.memberID " +
            "WHERE q.libraryInformation.bookID = :bookID " +
            "ORDER BY q.position ASC")
    List<QueueDTO> findQueueWithMemberNameByBookID(@Param("bookID") Integer bookID);



    //List<QueueDTO> findByQueueIdOrderByAsec(@Param("bookID") Integer bookID);



}

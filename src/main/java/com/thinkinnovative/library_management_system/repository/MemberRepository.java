package com.thinkinnovative.library_management_system.repository;

import com.thinkinnovative.library_management_system.dto.MemberListDTO;
import com.thinkinnovative.library_management_system.entity.MemberTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberTable, Integer>{
    @Query("SELECT new com.thinkinnovative.library_management_system.dto.MemberListDTO(" +
            "m.memberID, m.memberName, m.membershipDate) " +
            "FROM MemberTable m")
    List<MemberListDTO> findAllMembers();

    @Query("SELECT new com.thinkinnovative.library_management_system.dto.MemberListDTO(" +
            "m.memberID, m.memberName, m.membershipDate) " +
            "FROM MemberTable m WHERE m.memberID = :memberID")
    MemberListDTO findByMemberID(@Param("memberID") Integer memberId);

    @Query("SELECT m FROM MemberTable m WHERE m.memberID = :memberID")
    MemberTable findMemberEntityByID(@Param("memberID") Integer memberId);

    @Query("SELECT m FROM MemberTable m WHERE m.email = :email")
    MemberTable findMemberEntityByEmail(@Param("email") String email);







}


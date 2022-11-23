package repository;

import domain.Member;

import java.util.List;

public interface MemberRepository {

    Long save(Member member);

    Member findMemberById(Long id);

    Member updateMember(Member member);

    void deleteMember(Long id);

    List<Member> findAll();
}

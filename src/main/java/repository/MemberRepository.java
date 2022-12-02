package repository;

import domain.Member;

import java.util.List;

public interface MemberRepository {
    List<domain.Member> findAll();

    Long save(Member member);

    Member findMemberById(Long id);
}

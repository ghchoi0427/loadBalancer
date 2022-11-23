package repository;

import domain.Member;

public interface MemberRepository {

    Long save(Member member);

    Member findMemberById(Long id);

    Member updateMember(Member member);

    void deleteMember(Long id);
}

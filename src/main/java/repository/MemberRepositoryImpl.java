package repository;

import domain.Member;

import java.util.HashMap;
import java.util.Objects;

public class MemberRepositoryImpl implements MemberRepository{
    private static MemberRepositoryImpl memberRepositoryImpl;

    public static MemberRepositoryImpl getInstance() {
        return Objects.requireNonNullElseGet(memberRepositoryImpl, MemberRepositoryImpl::new);
    }

    private MemberRepositoryImpl() {
    }

    private final HashMap<Long, Member> map = new HashMap<>();
    private long increment = 0;

    @Override
    public Long save(Member member) {
        map.put(increment++, member);
        return increment;
    }

    @Override
    public Member findMemberById(Long id) {
        return map.get(id);
    }

    @Override
    public Member updateMember(Member member) {
        Member saved = map.get(member.getId());
        saved.setName(member.getName());
        saved.setProfile(member.getProfile());
        map.put(saved.getId(), saved);
        return saved;
    }

    @Override
    public void deleteMember(Long id) {
        map.remove(id);
    }
}

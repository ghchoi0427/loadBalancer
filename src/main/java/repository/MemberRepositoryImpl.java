package repository;

import domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MemberRepositoryImpl implements MemberRepository {

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

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(map.values());
    }
}

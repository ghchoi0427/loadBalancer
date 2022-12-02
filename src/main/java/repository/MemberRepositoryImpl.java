package repository;

import domain.Member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemberRepositoryImpl implements MemberRepository {
    private Map<Long, domain.Member> map = new HashMap<>();
    private long increment = 0;

    @Override

    public List<domain.Member> findAll() {
        return new ArrayList<>(map.values());
    }

    @Override
    public Long save(Member member) {
        member.setId(increment++);
        map.put(member.getId(), member);
        return member.getId();
    }

    @Override
    public Member findMemberById(Long id) {
        return map.get(id);
    }
}

package fashionmanager.kim.develop.repository;

import fashionmanager.kim.develop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Integer> {

}

package fashionmanager.lee.develop.repository;


import com.example.community.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Integer> {
}
package spring.joinmember.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.joinmember.Entiry.MemberEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity,Long> {

    //이메일로 회원 정보 조회
    Optional<MemberEntity> findByEmail(String MemberEmail); //null방지를 해주는 것
}

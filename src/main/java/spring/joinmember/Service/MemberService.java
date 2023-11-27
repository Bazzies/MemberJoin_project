package spring.joinmember.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.joinmember.DTO.MemberDTO;
import spring.joinmember.Entiry.MemberEntity;
import spring.joinmember.Repository.MemberRepository;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO) {
        //1. DTO를 entity로 변환하는 작업
        //2. repository의 save메서드 호출하는 작업
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);

        memberRepository.save(memberEntity);
        //repository의 save메서드 호출(JPA 조건, Entity객체를 넘겨줘야함)
    }


    public MemberDTO login(MemberDTO memberDTO) {
        //1. 회원이 입력한 이메일로 DB에서 조회를 함
        //2. DB에서 조회한 비밀번호화 사용자가 입력한 비밀번호가 일치하는지 판단
        Optional<MemberEntity> byMemberEmail = memberRepository.findByEmail(memberDTO.getMemberEmail());
        if (byMemberEmail.isPresent()){
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getPassword().equals(memberDTO.getMemberPassword())){
                //비밀번호 일치
                //entity -> dto변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }else{
                //비밀번호 불일치(로그인 실패)
                return null;
            }
        }else {
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity: memberEntityList)
        {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));

//            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
//            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent())
        {
//            MemberEntity memberEntity = findById.get();
//            MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
//            return dto;
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }else{
            return null;
        }
    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByEmail(myEmail);
        if (optionalMemberEntity.isPresent())
        {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }else{
            return null;
        }

    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toupdateMemberEntity(memberDTO)); //save는 id값이 없으면 insert쿼리를 날려주지만 있다면 update쿼리를 날려준다.
    }

    public void deleteBymemberId(Long id) {
        memberRepository.deleteById(id);
    }

    //public List<MemberDTO>
}

package spring.joinmember.DTO;

import lombok.*;
import spring.joinmember.Entiry.MemberEntity;

import java.lang.reflect.Member;

@Getter
@Setter
@NoArgsConstructor //기본 생성자를 생성해줌
@AllArgsConstructor //필드 생성자를 만들어줌
@ToString
public class MemberDTO {
    private Long memberId;
    private String memberName;
    private String memberPassword;
    private String memberEmail;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(memberEntity.getId());
        memberDTO.setMemberEmail(memberEntity.getEmail());
        memberDTO.setMemberPassword(memberEntity.getPassword());
        memberDTO.setMemberName(memberEntity.getName());
        return memberDTO;
    }
}

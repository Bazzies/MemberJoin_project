package spring.joinmember.Entiry;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import spring.joinmember.DTO.MemberDTO;

import java.lang.reflect.Member;
import java.util.Calendar;

@Entity
@Getter
@Setter
@Table(name = "member") //테이블 생성할 때 사용되는 테이블 명
public class MemberEntity {

    private static long sequence = (long)(Math.random() * 9000) + 1000;

    @Id
    private Long id = Long.parseLong(String.valueOf(Calendar.getInstance().get(Calendar.YEAR)) + String.valueOf(sequence++));

    @Column
    private String name;

    @Column
    private String password;

    @Column(unique = true)
    private String email;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail(memberDTO.getMemberEmail());
        memberEntity.setName(memberDTO.getMemberName());
        memberEntity.setPassword(memberDTO.getMemberPassword());

        return memberEntity;
    }

    public static MemberEntity toupdateMemberEntity(MemberDTO memberDTO){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId((memberDTO.getMemberId()));
        memberEntity.setEmail(memberDTO.getMemberEmail());
        memberEntity.setName(memberDTO.getMemberName());
        memberEntity.setPassword(memberDTO.getMemberPassword());

        return memberEntity;
    }
}

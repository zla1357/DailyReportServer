package com.mycompany.dailyreport.domain.dto;

import com.mycompany.dailyreport.domain.Member;
import com.mycompany.dailyreport.domain.ModelMapperUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberDTO {

    private Long id;
    private String name;
    private String accountId;
    private String password;

    public static MemberDTO from(Member member) {
        return ModelMapperUtils.getModelMapper().map(member, MemberDTO.class);
    }
}

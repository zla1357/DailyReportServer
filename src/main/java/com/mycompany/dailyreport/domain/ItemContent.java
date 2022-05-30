package com.mycompany.dailyreport.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemContent {

    private String reqUrl;

    // 콤마로 구분된 파라미터 리스트
    private String reqParam;
    private String method;
}

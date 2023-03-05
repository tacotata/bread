package com.example.helloproject.data.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor // @Builder 를 이용하기 위해서 항상 같이 처리해야 컴파일 에러가 발생하지 않는다
@NoArgsConstructor // @Builder 를 이용하기 위해서 항상 같이 처리해야 컴파일 에러가 발생하지 않는다
public class Test {

    @Id // @Entity 가 붙은 클래스는 PK에 해당하는 특정필드를 @Id로 지정해야 한다
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // 해당 어노테이션은 사용자가 입력하는 값을 사용하는 경우가 아니면 자동으로 생성되는 번호를 사용하기 위해 사용한다
    private Long num;

    @Column(nullable =false)  // 추가적인 필드(컬럼)이 팔요한 경우 사용 * DB 테이블에는 컬럼으로 생성되지 않는 필드의 경우 @Transient를 사용한다
    private String numText;
}

package kr.ac.knu.gdsc.Eywa.common.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass // 상속할 경우 필드들도 칼럼으로 인식하도록 한다.
public abstract class BaseTimeEntity {
  @CreatedDate
  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime updateAt;
}

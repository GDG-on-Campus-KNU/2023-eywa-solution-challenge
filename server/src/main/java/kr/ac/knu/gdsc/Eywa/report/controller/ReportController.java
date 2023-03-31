package kr.ac.knu.gdsc.Eywa.report.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import kr.ac.knu.gdsc.Eywa.auth.PrincipalDetail;
import kr.ac.knu.gdsc.Eywa.dictionary.service.DictionaryService;
import kr.ac.knu.gdsc.Eywa.member.domain.Authorities;
import kr.ac.knu.gdsc.Eywa.member.domain.Member;
import kr.ac.knu.gdsc.Eywa.member.service.MemberService;
import kr.ac.knu.gdsc.Eywa.report.domain.Report;
import kr.ac.knu.gdsc.Eywa.report.dto.ReportRequestDto;
import kr.ac.knu.gdsc.Eywa.report.dto.ReportResponseDto;
import kr.ac.knu.gdsc.Eywa.report.service.ReportService;
import kr.ac.knu.gdsc.Eywa.utils.CloudStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/reports")
@RequiredArgsConstructor
public class ReportController {
  private final ReportService reportService;
  private final DictionaryService dictionaryService;
  private final CloudStorageService cloudStorageService;
  private final MemberService memberService;

  // 생태계교란종 신고 목록 조회
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<ReportResponseDto>> getReportList() {
    List<ReportResponseDto> reportResponseDtoList = new ArrayList<>();
    this.reportService
        .getReportList()
        .forEach(
            report -> {
              reportResponseDtoList.add(report.toDto());
            });
    return ResponseEntity.ok(reportResponseDtoList);
  }

  // 생태계교란종 본인 신고 목록 조회
  @RequestMapping(method = RequestMethod.GET, value = "/members/me")
  public ResponseEntity<List<ReportResponseDto>> getMyReportList(
      @AuthenticationPrincipal PrincipalDetail oAuth2User) {
    Member member = oAuth2User.getMember();
    List<Report> reportList = this.reportService.getReportListOfMember(member.getId());
    List<ReportResponseDto> reportResponseDtoList = new ArrayList<>();
    reportList.forEach(
        report -> {
          reportResponseDtoList.add(report.toDto());
        });
    return ResponseEntity.ok(reportResponseDtoList);
  }

  // 생태계교란종 신고 상세 조회
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<ReportResponseDto> getReport(@PathVariable Long id) {
    Report report = this.reportService.getReport(id);
    return ResponseEntity.ok(report.toDto());
  }

  // 생태계교란종 신고
  @Secured(Authorities.ROLES.USER)
  @RequestMapping(
      method = RequestMethod.POST,
      consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  public void saveReport(
      @AuthenticationPrincipal PrincipalDetail principalDetail,
      @RequestPart ReportRequestDto reportRequestDto,
      @RequestPart MultipartFile image) {
    Member member = principalDetail.getMember();
    BigDecimal longitude = reportRequestDto.getLongitude();
    BigDecimal latitude = reportRequestDto.getLatitude();
    Long dictionaryId = reportRequestDto.getDictionaryId();
    String picture = this.cloudStorageService.saveImage("registers", image);
    Report report =
        Report.builder()
            .longitude(longitude)
            .latitude(latitude)
            .picture(picture)
            .member(member)
            .dictionary(this.dictionaryService.getDictionary(dictionaryId))
            .build();
    this.memberService.updateExpOfMember(member, 30);
    this.reportService.saveReport(report);
  }
}

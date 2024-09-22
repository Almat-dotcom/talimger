package kz.talimger.controller;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.school.SchoolSearchDto;
import kz.talimger.dto.school.SchoolViewDto;
import kz.talimger.service.SchoolQrCodeService;
import kz.talimger.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;
    private final SchoolQrCodeService qrCodeService;

    @GetMapping
    public ResponseEntity<PageDto<SchoolViewDto>> getSchools(@ModelAttribute SchoolSearchDto searchDto, Pageable pageable) {
        PageDto<SchoolViewDto> schoolPage = schoolService.getPageView(searchDto, pageable);
        return ResponseEntity.ok(schoolPage);
    }

    @PostMapping("/migration")
    public ResponseEntity<Void> processSchools(@RequestBody List<InstitutionDTO> institutionDTOs) {
        schoolService.processInstitutions(institutionDTOs);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/qr-pdf/{id}")
    public ResponseEntity<byte[]> downloadQRCodePDF(@PathVariable UUID id) {
        byte[] pdfBytes = qrCodeService.generatePDFWithQRCode(id);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"qr-code-" + id + ".pdf\"")
                .body(pdfBytes);
    }

    @PostMapping("/generate-qr")
    public ResponseEntity<SchoolViewDto> generateQRCode(@RequestParam UUID id) {
        SchoolViewDto school = qrCodeService.generateAndSaveQRCode(id);
        return ResponseEntity.ok().body(school);
    }

    @DeleteMapping("/qr/{id}")
    public ResponseEntity<SchoolViewDto> deleteQrCode(@PathVariable UUID id) {
        SchoolViewDto school = qrCodeService.deleteQrCode(id);
        return ResponseEntity.ok().body(school);
    }
}

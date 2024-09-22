package kz.talimger.controller;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.kindergarten.KindergartenSearchDto;
import kz.talimger.dto.kindergarten.KindergartenViewDto;
import kz.talimger.service.KindergartenQrCodeService;
import kz.talimger.service.KindergartenService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/kindergartens")
@RequiredArgsConstructor
public class KindergartenController {

    private final KindergartenService kindergartenService;
    private final KindergartenQrCodeService qrCodeService;

    @GetMapping
    public ResponseEntity<PageDto<KindergartenViewDto>> getKindergartens(@ModelAttribute KindergartenSearchDto searchDto,
                                                                         @PageableDefault(sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        PageDto<KindergartenViewDto> kindergartenPage = kindergartenService.getPageView(searchDto, pageable);
        return ResponseEntity.ok(kindergartenPage);
    }

    @PostMapping("/migration")
    public ResponseEntity<Void> processKindergartens(@RequestBody List<InstitutionDTO> institutionDTOs) {
        kindergartenService.processInstitutions(institutionDTOs);
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
    public ResponseEntity<KindergartenViewDto> generateQRCode(@RequestParam UUID id) {
        KindergartenViewDto kindergarten = qrCodeService.generateAndSaveQRCode(id);
        return ResponseEntity.ok().body(kindergarten);
    }

    @DeleteMapping("/qr/{id}")
    public ResponseEntity<KindergartenViewDto> deleteQrCode(@PathVariable UUID id) {
        KindergartenViewDto kindergarten = qrCodeService.deleteQrCode(id);
        return ResponseEntity.ok().body(kindergarten);
    }
}

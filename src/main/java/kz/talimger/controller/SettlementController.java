package kz.talimger.controller;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.settlement.SettlementSearchDto;
import kz.talimger.dto.settlement.SettlementViewDto;
import kz.talimger.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/settlements")
@RequiredArgsConstructor
public class SettlementController {
    private final SettlementService settlementService;

    @GetMapping
    public ResponseEntity<PageDto<SettlementViewDto>> getSettlements(@ModelAttribute SettlementSearchDto searchDto, Pageable pageable) {
        PageDto<SettlementViewDto> settlementPage = settlementService.getPageView(searchDto, pageable);
        return ResponseEntity.ok(settlementPage);
    }
}

package kz.talimger.controller;

import kz.talimger.dto.districtArea.DistrictAreaSearchDto;
import kz.talimger.dto.districtArea.DistrictAreaViewDto;
import kz.talimger.service.DistrictAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/district-areas")
@RequiredArgsConstructor
public class DistrictAreaController {
    private final DistrictAreaService districtAreaService;

    @GetMapping
    public ResponseEntity<Page<DistrictAreaViewDto>> getDistrictAreas(@ModelAttribute DistrictAreaSearchDto searchDto, Pageable pageable) {
        Page<DistrictAreaViewDto> districtAreaPage = districtAreaService.getPageView(searchDto, pageable);
        return ResponseEntity.ok(districtAreaPage);
    }
}

package kz.talimger.service;

import kz.talimger.dto.school.SchoolViewDto;

import java.util.UUID;

public interface SchoolQrCodeService {
    SchoolViewDto generateAndSaveQRCode(UUID institutionId);

    byte[] generatePDFWithQRCode(UUID institutionId);

    SchoolViewDto deleteQrCode(UUID institutionId);
}

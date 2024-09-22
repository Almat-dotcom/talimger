package kz.talimger.service;

import kz.talimger.dto.kindergarten.KindergartenViewDto;

import java.util.UUID;

public interface KindergartenQrCodeService {
    KindergartenViewDto generateAndSaveQRCode(UUID institutionId);

    byte[] generatePDFWithQRCode(UUID institutionId);

    KindergartenViewDto deleteQrCode(UUID institutionId);
}

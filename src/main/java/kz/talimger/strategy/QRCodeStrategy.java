package kz.talimger.strategy;

import kz.talimger.model.Institution;

import java.util.UUID;

public interface QRCodeStrategy<T extends Institution> {

    T generateAndSaveQRCode(UUID institutionId);

    byte[] generatePDFWithQRCode(UUID institutionId);

    T deleteQrCode(UUID institutionId);
}

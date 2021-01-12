package com.project.cleancode.enums;

public enum ReturnStatus {

  RMA_REQUEST_APPROVAL("Menunggu persetujuan permohonan retur"),
  RMA_REQUEST_APPROVED("Permohonan retur disetujui"),
  RMA_REQUEST_CANCELLED("Permohonan retur ditolak"),
  RMA_PROCESS_STARTED("Sedang dalam proses retur"),
  RMA_PROCESS_FINISHED("Proses retur selesai"),
  RMA_PROCESS_CANCELLED("Proses retur ditolak"),
  RMA_FORCE_CLOSED("Pengajuan retur ditutup");

  private String message;

  private ReturnStatus(String message) {
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }
}

package com.project.cleancode.service.util;

import com.google.common.base.Preconditions;
import com.project.cleancode.enums.ApiErrorCode;
import com.project.cleancode.exception.ApiMetadataException;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public final class OrderUtil {

  /**
   * Validate filter order list date range.
   * Convert dateString to local time.
   * Validate that endDate is greater than startDate.
   * Validate that date range count is not exceeded maximum properties value.
   *
   * @param startDate
   * @param endDate
   * @param maxDateDiff
   */
  public static void validateFilterDateRange(Date startDate, Date endDate, int maxDateDiff) {
    if (startDate != null && endDate != null) {
      LocalDateTime startDateTime =
          LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
      LocalDateTime endDateTime =
          LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());

      try {
        Preconditions.checkArgument(endDateTime.isAfter(startDateTime),
            ApiErrorCode.ORDER_LIST_START_DATE_IS_GREATER.getDesc());
      } catch (Exception e) {
        throw new ApiMetadataException(ApiMetadataException.class.getSimpleName(),
            ApiErrorCode.ORDER_LIST_START_DATE_IS_GREATER.getDesc(),
            ApiErrorCode.ORDER_LIST_START_DATE_IS_GREATER);
      }

      try {
        Preconditions.checkArgument( ChronoUnit.DAYS.between(startDateTime, endDateTime) <= maxDateDiff,
            "Order date filter range is too long, it should less than equal " + maxDateDiff);
      } catch (Exception e) {
        throw new ApiMetadataException(ApiMetadataException.class.getSimpleName(),
            "Order date filter range is too long, it should less than equal " + maxDateDiff,
            ApiErrorCode.ORDER_LIST_DATE_RANGE_FILTER_IS_TOO_LONG);
      }
    }
  }
}

package com.project.LibraryManagementSystem.mappers;

import com.project.LibraryManagementSystem.dto.borrowrecord.BorrowRecordResponse;
import com.project.LibraryManagementSystem.model.entity.BorrowRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BorrowRecordMapper {
  
    @Mapping(target = "book", ignore = true)
    @Mapping(target = "borrower", ignore = true)
    BorrowRecord toModel(BorrowRecordResponse borrowRecordResponse);

    @Mapping(target = "book", source = "book")
    @Mapping(target = "borrower", source = "borrower")
    BorrowRecordResponse toResponse(BorrowRecord borrowRecord);

}

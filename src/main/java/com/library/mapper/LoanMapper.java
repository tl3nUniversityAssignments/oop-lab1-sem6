package com.library.mapper;

import com.library.dto.LoanDTO;
import com.library.model.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface LoanMapper {
    LoanMapper INSTANCE = Mappers.getMapper(LoanMapper.class);

    @Mapping(source = "bookTitle", target = "bookTitle")
    @Mapping(source = "authorNames", target = "authorNames")
    @Mapping(source = "readerName", target = "readerName")
    @Mapping(source = "loan.loanDate", target = "loanDate")
    @Mapping(source = "loan.dueDate", target = "dueDate")
    @Mapping(source = "loan.returnDate", target = "returnDate")
    @Mapping(source = "loanStatus", target = "loanStatus")
    LoanDTO toDTO(String bookTitle, List<String> authorNames, String readerName, Loan loan, String loanStatus);
}

package az.company.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentDto {
    private String id;
    private String name;
    private String email;
    private String password;
    private String role;
    private List<AddressDto> addressList;
}

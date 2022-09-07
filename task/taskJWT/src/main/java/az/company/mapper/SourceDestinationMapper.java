package az.company.mapper;

import az.company.dto.AddressDto;
import az.company.dto.StudentDto;
import az.company.entity.Address;
import az.company.entity.PhoneNumber;
import az.company.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;


@Mapper(componentModel = "spring")
public interface SourceDestinationMapper {
    @Mapping(target = "name",source = "studentName")
    @Mapping(target = "id",source = "studentId")
    @Mapping(target = "password",ignore = true)
    @Mapping(target = "addressList",source = "addressList",qualifiedByName = "ADDRESSEntity2Dto")
    StudentDto STUDENTEntity2Dto(Student student);

    @Mapping(target = "studentName",source = "name")
    @Mapping(target = "studentId",source = "id",defaultValue = "0")
    @Mapping(target = "addressList",source = "addressList",qualifiedByName = "ADDRESSDto2Entity")
    Student STUDENTDto2Entity(StudentDto studentDto);


    @Named("ADDRESSDto2Entity")
    default Address ADDRESSDto2Entity(AddressDto addressDto){
        Address address = new Address();
        address.setAddressDescription(addressDto.getAddressDescription());

        PhoneNumber phoneNumber = new PhoneNumber();
        phoneNumber.setPhoneNumber(addressDto.getPhoneNumber());

        address.setPhoneNumber(phoneNumber);
        return address;
    }

    @Named("ADDRESSEntity2Dto")
    default AddressDto ADDRESSEntity2Dto(Address address){
        return new AddressDto(address.getAddressDescription(),
                address.getPhoneNumber().getPhoneNumber());
    }

}

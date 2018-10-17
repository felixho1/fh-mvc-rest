package ho.felix.api.v1.mapper;

import ho.felix.api.v1.model.VendorDTO;
import ho.felix.domain.Vendor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE = Mappers.getMapper(VendorMapper.class);
    Vendor vendorDTOToVendor(VendorDTO vendorDTO);
    VendorDTO vendorToVendorDTO(Vendor vendor);
}

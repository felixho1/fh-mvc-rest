package ho.felix.services;

import ho.felix.api.v1.mapper.VendorMapper;
import ho.felix.api.v1.model.VendorDTO;
import ho.felix.api.v1.model.VendorListDTO;
import ho.felix.controllers.v1.VendorController;
import ho.felix.domain.Vendor;
import ho.felix.repositories.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class VendorServiceImpl implements VendorService {
    private final VendorMapper vendorMapper;
    private final VendorRepository vendorRepository;

    public VendorServiceImpl(VendorMapper vendorMapper, VendorRepository vendorRepository) {
        this.vendorMapper = vendorMapper;
        this.vendorRepository = vendorRepository;
    }

    private VendorDTO convertVendorToVendorDTO(Vendor vendor) {
        VendorDTO vendorDTO = vendorMapper.vendorToVendorDTO(vendor);
        vendorDTO.setVendorUrl(VendorController.BASE_URL + "/" + vendor.getId());
        return vendorDTO;
    }

    private VendorDTO saveAndReturnDTO(Vendor vendor) {
        Vendor savedVendor = vendorRepository.save(vendor);
        return convertVendorToVendorDTO(savedVendor);
    }


    @Override
    public VendorDTO getVendorById(Long id) {
        return vendorRepository.findById(id)
                .map(vendor -> convertVendorToVendorDTO(vendor))
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public VendorListDTO getAllVendors() {
        return new VendorListDTO(
                vendorRepository.findAll()
                .stream()
                .map(vendor -> convertVendorToVendorDTO(vendor))
                .collect(Collectors.toList())
        );
    }

    @Override
    public VendorDTO createNewVendor(VendorDTO vendorDTO) {
        return saveAndReturnDTO(vendorMapper.vendorDTOToVendor(vendorDTO));
    }

    @Override
    public VendorDTO saveVendorByDTO(Long id, VendorDTO vendorDTO) {
        Vendor vendorToSave = vendorMapper.vendorDTOToVendor(vendorDTO);
        vendorToSave.setId(id);
        return saveAndReturnDTO(vendorToSave);
    }

    @Override
    public VendorDTO patchVendor(Long id, VendorDTO vendorDTO) {
        return vendorRepository.findById(id)
                .map(vendor -> {
                    //todo if more properties, add more if statements

                    if(vendorDTO.getName() != null){
                        vendor.setName(vendorDTO.getName());
                    }

                    return saveAndReturnDTO(vendor);
                }).orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteVendorById(Long id) {
        vendorRepository.deleteById(id);
    }
}

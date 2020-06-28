package com.korayshop.myboutique.service;

import com.korayshop.myboutique.domain.Address;
import com.korayshop.myboutique.web.dto.AddressDto;

public class AddressService {
    public static AddressDto mapToDto(Address address) {
        if (address != null) {
            return new AddressDto(
                    address.getAddress1(),
                    address.getAddress2(),
                    address.getCity(),
                    address.getPostCode(),
                    address.getCountry()
            );
        }
        return null;
    }
}
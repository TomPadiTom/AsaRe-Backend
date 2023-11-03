package com.padillatom.asadoremotobackend.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.padillatom.asadoremotobackend.model.entity.BillingAddressEntity;
import com.padillatom.asadoremotobackend.model.entity.ShippingAddressEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserDetailsUpdateRequest {

    private String firstName;

    private String lastName;

    private String displayName;

    private String phone;

    @JsonFormat(pattern="yyyy/mm/dd")
    private LocalDate birthDate;

    private AddressRequest billingAddress;

    private AddressRequest shippingAddress;

    public static BillingAddressEntity toBillingAddress(BillingAddressEntity billingAddress, UserDetailsUpdateRequest request) {
       if (billingAddress == null) {
           var newBillingAddress = new BillingAddressEntity();
           newBillingAddress.setFirstName(request.getBillingAddress().getFirstName());
           newBillingAddress.setLastName(request.getBillingAddress().getLastName());
           newBillingAddress.setCompanyName(request.getBillingAddress().getCompanyName());
           newBillingAddress.setCountry(request.getBillingAddress().getCountry());
           newBillingAddress.setAddress(request.getBillingAddress().getAddress());
           newBillingAddress.setCity(request.getBillingAddress().getCity());
           newBillingAddress.setState(request.getBillingAddress().getState());
           newBillingAddress.setZipCode(request.getBillingAddress().getZipCode());
           newBillingAddress.setPhone(request.getBillingAddress().getPhone());
           newBillingAddress.setEmail(request.getBillingAddress().getEmail());
           return newBillingAddress;
       } else {
           billingAddress.setFirstName(request.getBillingAddress().getFirstName());
           billingAddress.setLastName(request.getBillingAddress().getLastName());
           billingAddress.setCompanyName(request.getBillingAddress().getCompanyName());
           billingAddress.setCountry(request.getBillingAddress().getCountry());
           billingAddress.setAddress(request.getBillingAddress().getAddress());
           billingAddress.setCity(request.getBillingAddress().getCity());
           billingAddress.setState(request.getBillingAddress().getState());
           billingAddress.setZipCode(request.getBillingAddress().getZipCode());
           billingAddress.setPhone(request.getBillingAddress().getPhone());
           billingAddress.setEmail(request.getBillingAddress().getEmail());
           return billingAddress;
       }
    }

    public static ShippingAddressEntity toShippingAddress(ShippingAddressEntity shippingAddress, UserDetailsUpdateRequest request){
        if (shippingAddress == null) {
            var newShippingAddress = new ShippingAddressEntity();
            newShippingAddress.setFirstName(request.getShippingAddress().getFirstName());
            newShippingAddress.setLastName(request.getShippingAddress().getLastName());
            newShippingAddress.setCompanyName(request.getShippingAddress().getCompanyName());
            newShippingAddress.setCountry(request.getShippingAddress().getCountry());
            newShippingAddress.setAddress(request.getShippingAddress().getAddress());
            newShippingAddress.setCity(request.getShippingAddress().getCity());
            newShippingAddress.setState(request.getShippingAddress().getState());
            newShippingAddress.setZipCode(request.getShippingAddress().getZipCode());
            newShippingAddress.setPhone(request.getShippingAddress().getPhone());
            newShippingAddress.setEmail(request.getShippingAddress().getEmail());
            return newShippingAddress;
        } else {
            shippingAddress.setFirstName(request.getShippingAddress().getFirstName());
            shippingAddress.setLastName(request.getShippingAddress().getLastName());
            shippingAddress.setCompanyName(request.getShippingAddress().getCompanyName());
            shippingAddress.setCountry(request.getShippingAddress().getCountry());
            shippingAddress.setAddress(request.getShippingAddress().getAddress());
            shippingAddress.setCity(request.getShippingAddress().getCity());
            shippingAddress.setState(request.getShippingAddress().getState());
            shippingAddress.setZipCode(request.getShippingAddress().getZipCode());
            shippingAddress.setPhone(request.getShippingAddress().getPhone());
            shippingAddress.setEmail(request.getShippingAddress().getEmail());
            return shippingAddress;
        }
    }
}

package pl.ackstudio.skatecloud.dataInput;

import lombok.Data;
import pl.ackstudio.skatecloud.domain.State;
import pl.ackstudio.skatecloud.domain.User;
import pl.ackstudio.skatecloud.domain.UserDetail;

import java.time.LocalDate;

@Data
public class UserDetailForm {

    private String    fullname;
    private String    address;
    private String    city;
    private State     state;
    private String    zip;
    private LocalDate birthDate;
    private String    phoneNumber;

    public UserDetail toUserDetail(User user) {
        return updateUserDetail(new UserDetail(user));
    }

    public UserDetail updateUserDetail(UserDetail userDetail) {
        if (fullname.isBlank()) userDetail.setFullname(null);
        else userDetail.setFullname(fullname);
        if (address.isBlank()) userDetail.setAddress(null);
        else userDetail.setAddress(address);
        if (city.isBlank()) userDetail.setCity(null);
        else userDetail.setCity(city);
        userDetail.setState(state);
        if (zip.isBlank()) userDetail.setZip(null);
        else userDetail.setZip(zip);
        userDetail.setBirthDate(birthDate);
        if (phoneNumber.isBlank()) userDetail.setPhoneNumber(null);
        else userDetail.setPhoneNumber(phoneNumber);
        return userDetail;
    }
}

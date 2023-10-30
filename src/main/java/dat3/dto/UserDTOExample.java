package dat3.dto;

import dat3.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@ToString
@NoArgsConstructor
public class UserDTOExample {

    private String username;

    public UserDTOExample(String username) {
        this.username = username;
    }

    public UserDTOExample(User user) {
        this.username = user.getUsername();
    }

    public static List<UserDTOExample> toUserDTOList(List<User> users) {
        List<UserDTOExample> userDTOList =  new ArrayList<>();
        for (User user : users) {
            userDTOList.add(new UserDTOExample(user.getUsername()));
        }
        return userDTOList;

    }

    public String getUsername() {
        return username;
    }

}
package ru.itis.trip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.trip.entities.dto.UserCommentDto;
import ru.itis.trip.entities.dto.UserDto;
import ru.itis.trip.forms.ProfileForm;
import ru.itis.trip.services.UserCommentService;
import ru.itis.trip.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    @Autowired
    UserService userService;
    @Autowired
    UserCommentService userCommentService;


    @GetMapping("/profile/{id}")
    public String profilePage(@PathVariable(value = "id") Long userId,
                              ModelMap modelMap,
                              HttpServletRequest request) throws Exception {

        if (userId.equals(userService.getCurrentUser(request).getId())) {
            return "redirect:/profile";
        } else {
            Optional<UserDto> user = userService.getUserById(userId);
            if(!user.isPresent()){
                throw new Exception("user with such id is not found");
            }
            List<UserCommentDto> comments = userCommentService.getCommentsByUserId(user.get().getId());
            comments.forEach(comment -> comment.setCommentatee(user.get()));
            modelMap.put("comments", comments);
            modelMap.put("profile", user.get());
            return "profileById";
        }
    }

    @GetMapping("/profile")
    public String profilePage(HttpServletRequest request, ModelMap modelMap) {
        UserDto user = UserDto.from(userService.getCurrentUser(request));
        List<UserCommentDto> comments = userCommentService.getCommentsByUserId(user.getId());
        modelMap.put("user", user);
        modelMap.put("comments", comments);
        return "MyProfile";
    }

    @PostMapping("/profile")
    public String updateProfile(ProfileForm profileForm, HttpServletRequest request){
        userService.updateUser(userService.getCurrentUser(request), profileForm);
        return "redirect:/profile";
    }
}

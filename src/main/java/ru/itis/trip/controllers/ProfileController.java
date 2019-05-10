package ru.itis.trip.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.UserCommentDto;
import ru.itis.trip.entities.dto.UserDto;
import ru.itis.trip.entities.forms.ProfileForm;
import ru.itis.trip.entities.forms.UserCommentForm;
import ru.itis.trip.services.UserCommentService;
import ru.itis.trip.services.UserService;

import javax.servlet.http.HttpServletRequest;
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
    public String profilePage(ModelMap modelMap, HttpServletRequest request) {
        User user = userService.getCurrentUser(request);
        List<UserCommentDto> comments = userCommentService.getCommentsByUserId(user.getId());
        modelMap.put("comments", comments);
        return "MyProfile";
    }

    @PostMapping("/profile")
    public String updateProfile(ProfileForm profileForm, HttpServletRequest request){
        userService.updateUser(userService.getCurrentUser(request), profileForm);
        return "redirect:/profile";
    }

    @PostMapping("/profile/{userId}/comments/{commentId}")
    @ResponseBody
    public ResponseEntity addUserComment(@PathVariable Long commentId){
        userCommentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/profile/{userId}/comments")
    @ResponseBody
    public ResponseEntity addUserComment(@PathVariable Long userId,
                                         UserCommentForm userCommentForm,
                                         HttpServletRequest request){
        User user = userService.getCurrentUser(request);
        userCommentForm.setCommentateeId(userId);
        userCommentForm.setCommentatorId(user.getId());
        UserCommentDto comment = userCommentService.saveComment(userCommentForm, user);
        return ResponseEntity.ok(comment);
    }
}

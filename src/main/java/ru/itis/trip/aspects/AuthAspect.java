package ru.itis.trip.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import ru.itis.trip.entities.User;
import ru.itis.trip.entities.dto.UserDto;
import ru.itis.trip.services.UserService;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AuthAspect {
    private final UserService userService;

    @Autowired
    public AuthAspect(UserService userService) {
        this.userService = userService;
    }

    @Around(value = "execution(* ru.itis.trip.controllers..*(..)) && args(.., modelMap, request)", argNames = "pjp,modelMap,request")
    public Object auth(ProceedingJoinPoint pjp, ModelMap modelMap, HttpServletRequest request) throws Throwable {
        User user = userService.getCurrentUser(request);
        if (user == null) {
            return "redirect:/auth";
        }

        modelMap.put("user", UserDto.from(user));
        return pjp.proceed(pjp.getArgs());
    }

    @Around("execution(*  ru.itis.trip.controllers..*(..)) && args(.., request) " +
            "&& (@annotation(org.springframework.web.bind.annotation.PostMapping))"
    )
    public Object authOnPost(ProceedingJoinPoint pjp, HttpServletRequest request) throws Throwable {
        User user = userService.getCurrentUser(request);
        if (user == null) {
            return "redirect:/auth";
        }
        return pjp.proceed(pjp.getArgs());
    }

}
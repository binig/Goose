package org.bin2.goose.action;

import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by benoitroger on 19/03/15.
 */
@Controller()
@RequestMapping("/user/")
public class UserDetailsAction {

    @RequestMapping(value="self", method= RequestMethod.GET)
    public @ResponseBody String userDetails() {
        LoggerFactory.getLogger(getClass()).info("userDetailsSelf");
        SecurityContext ctxt =SecurityContextHolder.getContext();
        if (ctxt!=null&& ctxt.getAuthentication()!=null) return ctxt.getAuthentication().getName();
        return "";
    }

    @RequestMapping(value="other/{userId}", method= RequestMethod.GET)
    public @ResponseBody String userDetails(@PathVariable Long userId) {
        LoggerFactory.getLogger(getClass()).info("userDetails {}", userId);
        return Long.toString(userId);
    }
}

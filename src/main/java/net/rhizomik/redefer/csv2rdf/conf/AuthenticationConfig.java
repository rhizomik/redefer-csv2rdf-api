package net.rhizomik.redefer.csv2rdf.conf;

import net.rhizomik.redefer.csv2rdf.models.security.User;
import net.rhizomik.redefer.csv2rdf.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;

@Configuration
public class AuthenticationConfig extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userService)
            .passwordEncoder(User.passwordEncoder);
    }
}

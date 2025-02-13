package com.shop.goodee.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.shop.goodee.member.MemberSecurityService;
import com.shop.goodee.member.MemberSocialService;
import com.shop.goodee.member.security.LoginFail;
import com.shop.goodee.member.security.LoginSuccess;
import com.shop.goodee.member.security.LogoutCustom;
import com.shop.goodee.member.security.LogoutSuccessCustom;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
   
   @Autowired
   private LoginSuccess loginSuccess;
   
   @Autowired
   private LoginFail loginFail;
   
   @Autowired
   private MemberSocialService memberSocialService;
   
   @Autowired
   private LogoutSuccessCustom logoutSuccessCustom;
   
   @Autowired
   private LogoutCustom logoutCustom;
   
   @Bean
   WebSecurityCustomizer wegSecurityConfig() {
      //Security에서 무시해야하는 URL 패턴 등록
      return web -> web
            .ignoring()
            .antMatchers("/images/**")
            .antMatchers("/file/**")
            .antMatchers("/css/**")
            .antMatchers("/js/**")
            .antMatchers("/favicon/**")
            .antMatchers("/resources/**")
            .antMatchers("/chat/*")
            .antMatchers("/ws/*")
            .antMatchers("/chating/*");
   }

   @Bean
   SecurityFilterChain fiterChain(HttpSecurity httpSecurity)throws Exception{
      httpSecurity
               .cors()
               .and()
               .csrf().disable()
               .headers().frameOptions().sameOrigin()
               .and()
            .authorizeRequests()
               .antMatchers("/").permitAll()
               .antMatchers("/tab/*").permitAll()
               .antMatchers("/seller/seller").permitAll()
               .antMatchers("/board/notice").permitAll()
               .antMatchers("/chat/*").permitAll()
               .antMatchers("/ws/*").permitAll()
               .antMatchers("/item/detail").permitAll()
               .antMatchers("/item/mission").permitAll()
               .antMatchers("/mission/*").permitAll()
               .antMatchers("/member/join").permitAll()
               .antMatchers("/membership/membership").permitAll()
               .antMatchers("/member/agree").permitAll()
               .antMatchers("/member/join_end").permitAll()
               .antMatchers("/member/find_id").permitAll()
               .antMatchers("/member/find_pw").permitAll()
               .antMatchers("/board/write").hasRole("ADMIN")
               .antMatchers("/member/mypage").hasRole("ADMIN")
               .antMatchers("/member/mypage").hasRole("SELLER")
               .antMatchers("/member/mypage").hasRole("VIP")
               .antMatchers("/member/mypage").hasRole("MEMBER")
               .antMatchers("/admin").hasRole("ADMIN")
               .antMatchers("/member/product").hasRole("SELLER")
               .anyRequest().authenticated()
               .and()
            .formLogin()
               .loginPage("/")
               .loginProcessingUrl("/member/login")
               .successHandler(loginSuccess)
               .passwordParameter("pw")
               .usernameParameter("id")
               .failureHandler(loginFail)
               .permitAll()
               .and()
            .logout()
               .logoutUrl("/member/logout")
               .logoutSuccessHandler(logoutSuccessCustom)
               .addLogoutHandler(logoutCustom)
               .invalidateHttpSession(true)
               .deleteCookies("JSESSIONID")
               .permitAll()
               .and()
            .oauth2Login()//social login
               .userInfoEndpoint()
               .userService(memberSocialService);
               
      
      return httpSecurity.build();
               
   }
   
   //평문(Clear Text)을 암호화 시켜주는 객체생성
   @Bean("en")
   public PasswordEncoder getEncoder() {
      return new BCryptPasswordEncoder();
   }

}
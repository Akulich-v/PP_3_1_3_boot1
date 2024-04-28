//package ru.kata.spring.boot_security.demo.security;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import ru.kata.spring.boot_security.demo.model.User;
//
//import java.util.Collection;
//import java.util.Collections;
//
//public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {
//    private final User user;
//    public UserDetails(User user) {
//        this.user = user;
//    }
//
//    //АВТОРИЗАЦИЯ через РОЛИ
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//    //Получить пароль
//    @Override
//    public String getPassword() {
//        return this.user.getPassword();
//    }
//    //Получить имя пользователя
//    @Override
//    public String getUsername() {
//        return this.user.getUsername();
//    }
//    //Проверка просрочности аккаунта
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//    //Проверка блокировки аккаунта
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//    //Проверка просрочности пароля
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//    //Проверка включен ли аккаунт
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//
//    public User getUser(){
//        return this.user;
//    }
//}

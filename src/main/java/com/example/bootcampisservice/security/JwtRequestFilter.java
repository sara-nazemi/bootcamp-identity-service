package com.example.bootcampisservice.security;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    @Lazy
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    @Lazy
    private JwtTokenUtil jwtTokenUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        // دریافت مقدار توکن از هدر
        final String requestTokenHeader = request.getHeader("Authorization");

        String username = null;
        String jwtToken = null;
        // در صورتی که توکن null نباشد
        if (requestTokenHeader != null) {
            // حذف bearer از محتوای توکن
            jwtToken = ExtractRequestHeader.getHeaderPureToken(request);
            try {
                // تلاش برای دریافت نام کاربری از محتوای توکن
                // token=header.payload.signature
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                //موقعی این خطا دریافت می شود که محتوای توکن اشتباه باشد
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                //موقعی این خطا دریافت می شود که محتوای توکن تاریخ اعتبارش گذشته باشد
                System.out.println("JWT Token has expired");
            }
        } else {
            // در صورتی که محتوای توکن null باشد log می کنیم
            logger.warn("JWT Token does not begin with Bearer String");
        }

        // اگر نام کاربری خالی نبود(یعنی از محتوای توکن، نام کاربری استخراج شده است)
        // و در spring-security کاربر authenticate نبود
        //در این if می رویم
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            //اطلاعات کاربر را با استفاده از کلاس jwtUserDetailsService لود می زنیم
            UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

            // اگر محتوای توکن به همراه اطلاعات user details که از خط بالا دریافت شده است معتبر بودند
            if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {

                // به صورت دستی کاربر را احراز هویت می کنیم
                //ابتدا یک کلاس با نامUsernamePasswordAuthenticationToken می سازیم
                //و در نهایت  شی درست شده ازاین کلاس را به کلاس SecurityContextHolder پاس می دهیم
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken
                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        //در صورتی که کاربر احراز هویت شده بود درخواست را به سمت کنترولر ارسال می کنیم
        chain.doFilter(request, response);
    }

}

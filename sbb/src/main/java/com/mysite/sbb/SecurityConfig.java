package com.mysite.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
		// 		.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
		// 		.csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
		// 		.headers((headers) -> headers.addHeaderWriter(
		// 				new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
		// 		.formLogin((formLogin) -> formLogin.loginPage("/user/login").defaultSuccessUrl("/"))
		// 		.logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
		// 				.logoutSuccessUrl("/").invalidateHttpSession(true));

		http
			.authorizeHttpRequests(auth -> auth
				// AntPathRequestMatcher 생성자 대신 문자열 패턴 직접 입력
				.requestMatchers("/**").permitAll()
			)
			.csrf(csrf -> csrf
				// 여기도 문자열 패턴만 입력하면 내부적으로 AntPathRequestMatcher가 자동 적용됩니다
				.ignoringRequestMatchers("/h2-console/**")
			)
			.headers(headers -> headers
				.frameOptions(frame -> frame.sameOrigin())
			)
			.formLogin(form -> form
				.loginPage("/user/login")
				.defaultSuccessUrl("/")
			)
			
			.logout(logout -> logout
				.logoutUrl("/user/logout") // 경로 지정
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
			);

			// .logout(logout -> logout
			// 	// 로그아웃 매처도 문자열로 단순화
			// 	.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout")) // (참고: 로그아웃은 커스텀 경로일 때만 필요)
			// 	.logoutSuccessUrl("/")
			// 	.invalidateHttpSession(true)
			// );

		// http
        //     // 1. HTTP 요청 권한 설정
        //     .authorizeHttpRequests(auth -> auth
        //         .requestMatchers(new AntPathRequestMatcher("/**")).permitAll()
        //     )
        //     // 2. CSRF 설정 (H2 콘솔 제외)
        //     .csrf(csrf -> csrf
        //         .ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**"))
        //     )
        //     // 3. 헤더 설정 (Frame Options 설정 단순화)
        //     .headers(headers -> headers
        //         .frameOptions(frame -> frame.sameOrigin())
        //     )
        //     // 4. 로그인 설정
        //     .formLogin(form -> form
        //         .loginPage("/user/login")
        //         .defaultSuccessUrl("/")
        //     )
        //     // 5. 로그아웃 설정
        //     .logout(logout -> logout
        //         .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
        //         .logoutSuccessUrl("/")
        //         .invalidateHttpSession(true)
        //     );

		return http.build();
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}

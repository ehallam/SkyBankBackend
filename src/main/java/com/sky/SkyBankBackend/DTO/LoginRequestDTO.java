package com.sky.SkyBankBackend.DTO;

public class LoginRequestDTO {

    private String loginEmail;
    private String loginPassword;

    public LoginRequestDTO() {

    }

    public LoginRequestDTO(String loginEmail, String loginPassword) {
        this.loginEmail = loginEmail;
        this.loginPassword = loginPassword;
    }

    public  LoginRequestDTO(LoginRequestDTO request) {
        this.loginEmail = request.getLoginEmail();
        this.loginPassword = request.getLoginPassword();
    }

    // Getters and setters
        public String getLoginEmail() {
            return loginEmail;
        }

        public void setLoginEmail(String loginEmail) {
            this.loginEmail = loginEmail;
        }

        public String getLoginPassword() {
            return loginPassword;
        }

        public void setLoginPassword(String loginPassword) {
            this.loginPassword = loginPassword;
        }
}

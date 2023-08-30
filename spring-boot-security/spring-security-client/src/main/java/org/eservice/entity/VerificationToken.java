package org.eservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class VerificationToken {

    private static final int EXPIRATION_VALUE = 10;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    private Date expirationTime;

    @OneToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            nullable = false,
            name = "user_id",
            foreignKey = @ForeignKey(name="FK_USER_VERIFY_TOKEN")
    )
    private User user;

    public VerificationToken(String token,User user){
        this.token = token;
        this.user = user;

        this.expirationTime = calculateExpirationDate(EXPIRATION_VALUE);

    }

    public Date calculateExpirationDate(int expirationValue){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE,expirationValue);
        return new Date(calendar.getTime().getTime());
    }









}

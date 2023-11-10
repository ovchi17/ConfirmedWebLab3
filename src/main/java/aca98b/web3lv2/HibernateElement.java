package aca98b.web3lv2;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;


// class represents database table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table
public class HibernateElement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float x;
    private float y;
    private float r;
    private String result;
    private String time;
    private String scriptTime;
    private String uid;

//    public String toJson() {
//        return String.format(Locale.US, "{\"x\": %.2f, \"y\": %.2f, \"r\": %.2f, \"result\": %b}", x, y, r, result);
//    }
}
